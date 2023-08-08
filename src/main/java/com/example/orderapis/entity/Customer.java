package com.example.orderapis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Arrays;

@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "location")
    private String location;

}
