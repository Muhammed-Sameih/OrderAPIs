package com.example.orderapis.service.impl;

import com.example.orderapis.entity.*;
import com.example.orderapis.exception.RecordNotFoundException;
import com.example.orderapis.mapper.OrderMapper;
import com.example.orderapis.mapper.StoreMapper;
import com.example.orderapis.model.ResponseModel;
import com.example.orderapis.model.bank.DepositRequest;
import com.example.orderapis.model.bank.WithdrawRequest;

import com.example.orderapis.model.coupon.CouponServiceRequest;
import com.example.orderapis.model.customer.CustomerDTO;
import com.example.orderapis.model.order.OrderRequestDTO;
import com.example.orderapis.model.order.OrderResponseDTO;
import com.example.orderapis.model.orderItem.OrderItemRequestDTO;
import com.example.orderapis.model.store.StoreServiceResponse;
import com.example.orderapis.repository.*;
import com.example.orderapis.service.OrderService;
import com.example.orderapis.util.OrderCodeGenerator;
import com.example.orderapis.util.RestApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired private OrderRepo orderRepo;
    @Autowired private CustomerRepo customerRepo;
    @Autowired private OrderMapper orderMapper;
    @Autowired private StoreMapper storeMapper;
    @Autowired private ShipmentRepo shipmentRepo;
    @Autowired private OrderItemRepo orderItemRepo;
    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private CouponServiceImpl couponService;

    @Autowired
    private RestApiClient restApiClient;


    @Override
    public void create(OrderRequestDTO orderModel) {

        Order order = orderMapper.toEntity(orderModel);
        Customer customer = customerService.createOrFindCustomer(orderModel.getCustomerDetailedDTO());
        order.setCustomer(customer);

        Coupon coupon = couponService.createOrFindCoupon(orderModel.getCouponRequestDTO());
        order.setCoupon(coupon);


        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setCode(OrderCodeGenerator.generateOrderCode());

        Order savedOrder = orderRepo.save(order);

        Shipment shipment = createShipment(customer, savedOrder);

        for (OrderItemRequestDTO itemDTO : orderModel.getOrderItems()) {
            OrderItem orderItem = createOrderItem(itemDTO, savedOrder, shipment, orderModel);
            savedOrder.getOrderItems().add(orderItem);
        }


        BigDecimal totalBeforeDiscount = calculateTotalPrice(savedOrder.getOrderItems());
        BigDecimal totalAfterDiscount = applyCouponDiscount(savedOrder.getCoupon(), totalBeforeDiscount, savedOrder.getCode());
        order.setTotalPrice(totalAfterDiscount);

        // Withdraw the order total from the bank
        withdrawFromBank(orderModel.getCardNumber(), orderModel.getCvv(), savedOrder.getTotalPrice());

        // Deposit the discounted total back to the bank
        DepositFromBank(savedOrder.getTotalPrice());

        orderRepo.save(savedOrder);



    }


    // For Coupon
    private BigDecimal calculateTotalPrice(List<OrderItem> orderItems) {
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : orderItems) {
            BigDecimal itemPrice = item.getPrice();
            long itemQuantity = item.getQuantity();
            BigDecimal totalPriceForItem = itemPrice.multiply(BigDecimal.valueOf(itemQuantity));
            total = total.add(totalPriceForItem);
        }

        return total;
    }

    private BigDecimal applyCouponDiscount(Coupon coupon, BigDecimal totalBeforeDiscount,String orderCode) {
        BigDecimal totalPriceAfterDiscount = calculateTotalPriceAfterDiscount(coupon, totalBeforeDiscount);
        ResponseModel couponApplyResponse = restApiClient.postApplyCoupon(new CouponServiceRequest(orderCode,totalBeforeDiscount,totalPriceAfterDiscount));
        return totalPriceAfterDiscount;
    }

    private BigDecimal calculateTotalPriceAfterDiscount(Coupon coupon, BigDecimal totalPriceBeforeDiscount){
        return ("VALUE".equals(coupon.getType()))?totalPriceBeforeDiscount.subtract(coupon.getDiscount_value_or_percentage()):totalPriceBeforeDiscount.multiply(coupon.getDiscount_value_or_percentage()).divide(BigDecimal.valueOf(100));
    }


    //For Bank
    private void withdrawFromBank(String cardNumber, String cvv, BigDecimal amount) {
        restApiClient.postWithdraw(new WithdrawRequest(cardNumber,cvv,amount));
    }
    private void DepositFromBank(BigDecimal amount) {
        restApiClient.postDeposit(new DepositRequest("0000000000000000",amount));
    }


    // create order items and shipment
    private List<StoreServiceResponse> requestStores(List<OrderItemRequestDTO> orderItems) {
        List<StoreServiceResponse> storeResponses = new ArrayList<>();
        for (OrderItemRequestDTO itemDTO : orderItems) {
            storeResponses.addAll(restApiClient.getStoresByProductAndQuantity(itemDTO.getProduct_code(), itemDTO.getQuantity()));
        }
        return storeResponses;
    }

    private Shipment createShipment(Customer customer, Order order) {
        Shipment shipment = new Shipment();
        shipment.setShipmentDate(order.getOrderDate());
        shipment.setCustomer(customer);
        shipment.setStatus(order.getStatus());
        return shipmentRepo.save(shipment);
    }

    private OrderItem createOrderItem(OrderItemRequestDTO itemDTO, Order order, Shipment shipment, OrderRequestDTO orderModel) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct_code(itemDTO.getProduct_code());
        orderItem.setQuantity(itemDTO.getQuantity());
        orderItem.setPrice(restApiClient.getProductPriceByCode(orderItem.getProduct_code()).getPrice());
        orderItem.setOrder(order);

        List<ShipmentItem> shipmentItems = new ArrayList<>();
        List<StoreServiceResponse> storeResponses = requestStores(orderModel.getOrderItems());


        for (StoreServiceResponse storeResponse : storeResponses) {
            ShipmentItem shipmentItem = new ShipmentItem();
            shipmentItem.setShipment(shipment);
            shipmentItem.setOrderItem(orderItem);
            shipmentItem.setStore(storeMapper.toEntity(storeResponse));
            shipmentItem.setQuantity(storeResponse.getQuantity());
            shipmentItems.add(shipmentItem);
        }

        orderItem.setShipmentItems(shipmentItems);
        return orderItemRepo.save(orderItem);
    }


    //Search by customer email and date range
    @Override
    public List<OrderResponseDTO> findByCustomerAndRange(CustomerDTO customerModel, LocalDate startDate, LocalDate endDate) {
        return orderRepo.findByCustomerAndOrderDateBetween(customerRepo.findByEmail(customerModel.getEmail()).orElseThrow(()->new RecordNotFoundException("Invalid customer Email!")), startDate, endDate)
                .stream()
                .map(orderMapper:: toDTO)
                .collect(Collectors.toList());
    }
}
