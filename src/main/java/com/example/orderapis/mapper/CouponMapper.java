package com.example.orderapis.mapper;

import com.example.orderapis.entity.Coupon;
import com.example.orderapis.model.coupon.CouponModelForRequest;
import com.example.orderapis.model.coupon.CouponModelForResponse;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    Coupon toEntity(@Valid CouponModelForRequest couponModel);
    @Mapping(target = "discount_value_or_percentage",source = "value")
    Coupon toEntity(@Valid CouponModelForResponse couponModel);
    CouponModelForResponse toModel(Coupon coupon);
}
