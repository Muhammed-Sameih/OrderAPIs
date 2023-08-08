package com.example.orderapis.service;

import com.example.orderapis.entity.Coupon;
import com.example.orderapis.model.coupon.CouponRequestDTO;
import com.example.orderapis.model.coupon.CouponResponseDTO;

public interface CouponService {
    public Coupon createOrFindCoupon(CouponRequestDTO couponDTO);
}
