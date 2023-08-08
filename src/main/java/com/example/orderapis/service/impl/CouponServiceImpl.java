package com.example.orderapis.service.impl;

import com.example.orderapis.entity.Coupon;
import com.example.orderapis.mapper.CouponMapper;
import com.example.orderapis.model.coupon.CouponRequestDTO;
import com.example.orderapis.repository.CouponRepo;
import com.example.orderapis.service.CouponService;
import com.example.orderapis.util.RestApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepo couponRepository;
    
    @Autowired
        private RestApiClient restApiClient;
    
    @Autowired
        private CouponMapper couponMapper;
    @Override
    public Coupon createOrFindCoupon(CouponRequestDTO couponDTO) {
        Coupon coupon = getCouponData(couponMapper.toEntity(couponDTO));
        Optional<Coupon> existingCoupon = couponRepository.findByCode(coupon.getCode());
        return existingCoupon.orElseGet(() -> createCoupon(couponDTO));
    }


    private Coupon createCoupon(CouponRequestDTO couponDTO) {
        Coupon coupon = new Coupon();
        coupon.setCode(couponDTO.getCode());
        // Set other coupon properties as needed
        return couponRepository.save(coupon);
    }

    private Coupon getCouponData(Coupon coupon) {
        return couponMapper.toEntity(restApiClient.getCouponByCode(coupon.getCode()));
    }
}