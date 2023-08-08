package com.example.orderapis.mapper;


import com.example.orderapis.entity.Coupon;
import com.example.orderapis.model.coupon.CouponRequestDTO;
import com.example.orderapis.model.coupon.CouponResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    public Coupon toEntity(CouponRequestDTO orderRequestDTO);
    public Coupon toEntity(CouponResponseDTO orderRequestDTO);
    public CouponResponseDTO toDTO(Coupon coupon);

}
