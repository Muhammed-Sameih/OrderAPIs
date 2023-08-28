package com.example.orderapis.service.impl;

import com.example.orderapis.entity.Coupon;
import com.example.orderapis.entity.Order;
import com.example.orderapis.entity.OrderItem;
import com.example.orderapis.mapper.OrderMapper;
import com.example.orderapis.model.ResponseModel;
import com.example.orderapis.model.coupon.CouponModelForRequest;
import com.example.orderapis.model.order.OrderModelForRequest;
import com.example.orderapis.model.order.OrderModelForResponse;
import com.example.orderapis.model.orderItem.OrderItemModelForRequest;
import com.example.orderapis.model.product.ProductModelForConsume;
import com.example.orderapis.model.shipping.ShippingModel;
import com.example.orderapis.model.store.StoreModelResponse;
import com.example.orderapis.repo.OrderRepo;
import com.example.orderapis.service.CouponService;
import com.example.orderapis.service.OrderItemService;
import com.example.orderapis.util.RestApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {
    @Mock
    private OrderRepo orderRepo;
    @Mock
    private OrderItemService orderItemService;
    @Mock
    private CouponService couponService;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private RestApiClient restApiClient;
    @Mock
    private Environment environment;
    @InjectMocks
    private OrderServiceImpl orderService;
    private Coupon coupon;
    private OrderModelForRequest orderModel;
    private OrderItemModelForRequest orderItemModel;
    private Order order;
    private OrderItem orderItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        orderModel = new OrderModelForRequest();
        orderModel.setCoupon(new CouponModelForRequest("COUPON123"));
        orderModel.setCustomerEmail("customer@example.com");
        orderModel.setLocation("Shipping Address");
        orderModel.setCardNumber("5507928493141707");
        orderModel.setCvv("123");

        orderItemModel = new OrderItemModelForRequest();
        orderItemModel.setProductCode("product123");
        orderItemModel.setQuantity(2L);
        orderModel.setOrderItems(Collections.singletonList(orderItemModel));

        orderItem = new OrderItem(1L,
                "ITEM123",
                "product123",
                2L,
                BigDecimal.valueOf(119.99),
                null);

        coupon = new Coupon(1L,
                "COUPON123",
                "VALUE",
                BigDecimal.valueOf(50));

        order = new Order(1L,
                coupon,
                LocalDateTime.now(),
                BigDecimal.valueOf(119.99),
                "customer@example.com",
                "cairo",
                "ORDER123",
                Collections.singletonList(orderItem));
    }

    @Test
    void shouldCreateOrderSuccessfully() {
        // Arrange
        List<ProductModelForConsume> products = Collections.singletonList(new ProductModelForConsume(2L, "product123"));
        List<StoreModelResponse> storesInfo = Collections.singletonList(new StoreModelResponse("STORE123","Cairo",products));
        ShippingModel shippingModel = new ShippingModel("ORDER123",orderModel.getCustomerEmail(),orderModel.getLocation(),storesInfo);

        when(orderItemService.createList(Collections.singletonList(orderItemModel))).thenReturn(Collections.singletonList(orderItem));
        when(couponService.createOrFindCoupon(any())).thenReturn(coupon);
        when(couponService.applyCouponDiscount(any(), any(), any())).thenReturn(BigDecimal.valueOf(69.99));
        when(environment.getProperty("systemCardNum")).thenReturn("5507928493141707");
        when(orderMapper.toEntity(orderModel)).thenReturn(order);
        when(orderItemService.createList(Collections.singletonList(orderItemModel))).thenReturn(Collections.singletonList(orderItem));
        when(restApiClient.postDeposit(any())).thenReturn(new ResponseModel(HttpStatus.OK,true,null,null));
        when(restApiClient.postWithdraw(any())).thenReturn(new ResponseModel(HttpStatus.OK,true,null,null));
        when(restApiClient.postShippingOrder(shippingModel)).thenReturn(new ResponseModel(HttpStatus.OK,true,null,null));


        // Act
        orderService.create(orderModel);

        // Assert
        verify(couponService).createOrFindCoupon(any());
        verify(couponService).applyCouponDiscount(eq(coupon), any(), any());
        verify(environment).getProperty("systemCardNum");
        verify(orderMapper).toEntity(eq(orderModel));
        verify(orderItemService).createList(any());
        verify(orderRepo).save(any());
    }

    @Test
    void shouldFindByCustomerAndRangeSuccessfully() {
        // Arrange
        String customerEmail = "test@example.com";
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        LocalDateTime endDate = LocalDateTime.now();
        when(orderRepo.findByCustomerEmailAndOrderDateBetween(
                customerEmail, startDate, endDate))
                .thenReturn(Collections.singletonList(new Order()));

        when(orderMapper.toModel(any())).thenReturn(new OrderModelForResponse());

        // Act
        List<OrderModelForResponse> orders = orderService.findByCustomerAndRange(
                customerEmail, startDate, endDate);

        // Assert
        verify(orderRepo).findByCustomerEmailAndOrderDateBetween(
                customerEmail, startDate, endDate);
        verify(orderMapper, times(orders.size())).toModel(any());
        assertThat(orders).hasSize(1);
    }

}

