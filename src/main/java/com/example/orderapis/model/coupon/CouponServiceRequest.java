package com.example.orderapis.model.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CouponServiceRequest {
    private String orderCode;
    private BigDecimal priceBefore;
    private BigDecimal priceAfter;
}
