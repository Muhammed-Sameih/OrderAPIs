package com.example.orderapis.repository;

import com.example.orderapis.entity.Coupon;
import com.example.orderapis.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepo extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByCode(String code);

}
