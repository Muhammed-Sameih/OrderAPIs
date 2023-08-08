package com.example.orderapis.model.coupon;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponRequestDTO {
    @NotBlank(message = "Coupon Code is required!")
    private String code;
}
