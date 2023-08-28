package com.example.orderapis.service.impl;


import org.springframework.core.env.Environment;
import com.example.orderapis.entity.Order;
import com.example.orderapis.entity.OrderItem;
import com.example.orderapis.mapper.OrderMapper;
import com.example.orderapis.model.bank.DepositRequest;
import com.example.orderapis.model.bank.WithdrawRequest;
import com.example.orderapis.model.coupon.CouponModelForRequest;
import com.example.orderapis.model.order.OrderModelForRequest;
import com.example.orderapis.model.order.OrderModelForResponse;
import com.example.orderapis.model.product.ProductModelForConsume;
import com.example.orderapis.model.shipping.ShippingModel;
import com.example.orderapis.model.store.StoreModelResponse;
import com.example.orderapis.repo.OrderRepo;
import com.example.orderapis.service.CouponService;
import com.example.orderapis.service.OrderItemService;
import com.example.orderapis.service.OrderService;
import com.example.orderapis.util.CodeGenerator;
import com.example.orderapis.util.RestApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestApiClient restApiClient;
    @Autowired
    private Environment environment;

    @Override
    public void create(OrderModelForRequest orderModel){
        // Step 0: Convert the order model to an entity and set initial order data
        Order order = orderMapper.toEntity(orderModel);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("CREATED");
        order.setCode(CodeGenerator.generateOrderCode());

        // Step 1: Create a list of order items and associate them with the order
        order.setOrderItems(orderItemService.createList(orderModel.getOrderItems()));

        // Step 2: Calculate the total price for the order
        order.setTotalPrice(calculateTotalPrice(order.getOrderItems()));

        // Step 3: Check if a coupon is available and apply it if applicable
        order = applyCouponIsExists(order, orderModel.getCoupon());

        // Step 4: Perform a transaction to charge the customer for the order
        this.withdrawFromBank(orderModel.getCvv(), orderModel.getCvv(), order.getTotalPrice());
        this.depositFromBank(order.getTotalPrice());

        // Step 5: Consume products from stores and get required shipment information
        List<StoreModelResponse> storesInfo = consumeProductsFromStore(order.getOrderItems());

        // Step 6: Initiate the shipping process for the order
        this.shippingOrder(order.getCode(), orderModel.getCustomerEmail(),orderModel.getLocation(),storesInfo);

        // Step 7: Save the order to the repository
        orderRepo.save(order);
    }
    BigDecimal calculateTotalPrice(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    Order applyCouponIsExists(Order order, CouponModelForRequest couponModel){
        if(couponModel != null) {
            order.setCoupon(couponService.createOrFindCoupon(couponModel));
            order.setTotalPrice(couponService.applyCouponDiscount(order.getCoupon(),order.getTotalPrice(),order.getCode()));
        }
        return order;
    }
    void shippingOrder(String orderCode,String customerEmail,String location, List<StoreModelResponse>storesInfo){
        restApiClient.postShippingOrder(new ShippingModel(orderCode,customerEmail,location,storesInfo));
    }
    void withdrawFromBank(String cardNumber, String cvv, BigDecimal amount) {
        restApiClient.postWithdraw(new WithdrawRequest(cardNumber,cvv,amount));
    }
    void depositFromBank(BigDecimal amount) {
        restApiClient.postDeposit(new DepositRequest(environment.getProperty("systemCardNum"),amount));
    }
    List<StoreModelResponse> consumeProductsFromStore(List<OrderItem> items) {
        List<ProductModelForConsume> products = items.stream()
                .map(orderItem -> new ProductModelForConsume(orderItem.getQuantity(),orderItem.getProductCode()))
                .collect(Collectors.toList());
       return restApiClient.consumeProductsFromStoresWithProductInfo(products);
    }

    @Override
    public List<OrderModelForResponse> findByCustomerAndRange(String customerEmail, LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepo.findByCustomerEmailAndOrderDateBetween(customerEmail, startDate, endDate)
                .stream()
                .map(orderMapper:: toModel)
                .collect(Collectors.toList());
    }
}
