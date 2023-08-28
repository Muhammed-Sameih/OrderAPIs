package com.example.orderapis.service;

import com.example.orderapis.entity.Coupon;
import com.example.orderapis.model.coupon.CouponModelForRequest;

import java.math.BigDecimal;

public interface CouponService {
    Coupon createOrFindCoupon(CouponModelForRequest couponModel);
    BigDecimal applyCouponDiscount(Coupon coupon, BigDecimal totalBeforeDiscount, String orderCode);
}
