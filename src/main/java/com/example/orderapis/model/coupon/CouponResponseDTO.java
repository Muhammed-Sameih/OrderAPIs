package com.example.orderapis.model.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponResponseDTO {

    private String code;

    private String type;

    private BigDecimal discount_value_or_percentage;

}
