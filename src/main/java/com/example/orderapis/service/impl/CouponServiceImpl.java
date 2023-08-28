package com.example.orderapis.service.impl;

import com.example.orderapis.entity.Coupon;
import com.example.orderapis.mapper.CouponMapper;
import com.example.orderapis.model.coupon.CouponModelForRequest;
import com.example.orderapis.model.coupon.CouponModelForUseCoupon;
import com.example.orderapis.repo.CouponRepo;
import com.example.orderapis.service.CouponService;
import com.example.orderapis.util.RestApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepo couponRepo;
    @Autowired
    private RestApiClient restApiClient;
    @Autowired
    private CouponMapper couponMapper;

    @Override
    public Coupon createOrFindCoupon(CouponModelForRequest couponModel) {
        Coupon coupon = couponMapper.toEntity(couponModel);
        coupon = getCouponData(coupon);
        Optional<Coupon> existingCoupon = couponRepo.findByCode(coupon.getCode());
        Coupon finalCoupon = coupon;
        return existingCoupon.orElseGet(() -> this.createCoupon(finalCoupon));
    }
    Coupon createCoupon(Coupon coupon) {
        return couponRepo.save(coupon);
    }
    Coupon getCouponData(Coupon coupon) {
        return couponMapper.toEntity(restApiClient.getCouponByCode(coupon.getCode()));
    }
    public BigDecimal applyCouponDiscount(Coupon coupon, BigDecimal totalBeforeDiscount, String orderCode) {
        BigDecimal totalPriceAfterDiscount = calculateTotalPriceAfterDiscount(coupon, totalBeforeDiscount);
        restApiClient.postApplyCoupon(new CouponModelForUseCoupon(orderCode,totalBeforeDiscount,totalPriceAfterDiscount), coupon.getCode());
        return totalPriceAfterDiscount;
    }
    BigDecimal calculateTotalPriceAfterDiscount(Coupon coupon, BigDecimal totalPriceBeforeDiscount) {
        if ("VALUE".equals(coupon.getType())) {
            return totalPriceBeforeDiscount.subtract(coupon.getDiscount_value_or_percentage()).max(BigDecimal.ZERO);
        } else {
            BigDecimal discountAmount = totalPriceBeforeDiscount.multiply(coupon.getDiscount_value_or_percentage())
                    .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
            return totalPriceBeforeDiscount.subtract(discountAmount);
        }
    }
}
