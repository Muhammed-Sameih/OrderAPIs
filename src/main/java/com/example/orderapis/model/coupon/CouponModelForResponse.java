package com.example.orderapis.model.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponModelForResponse {
    private String code;
    private String type;
    private BigDecimal value;
}
