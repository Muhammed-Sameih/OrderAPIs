package com.example.orderapis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "COUPON")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "coupon_code")
    private String code;

    @Column(name = "coupon_Type")
    private String type;

    @Column(name = "discount_value_or_percentage")
    private BigDecimal discount_value_or_percentage;
}
