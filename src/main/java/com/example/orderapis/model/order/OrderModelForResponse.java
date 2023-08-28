package com.example.orderapis.model.order;

import com.example.orderapis.model.coupon.CouponModelForResponse;
import com.example.orderapis.model.orderItem.OrderItemModelForResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModelForResponse {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    private String status;
    private CouponModelForResponse coupon;
    private String customerEmail;
    private List<OrderItemModelForResponse> orderItems;
}
