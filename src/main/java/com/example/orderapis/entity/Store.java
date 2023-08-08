package com.example.orderapis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "STORE")
@Data
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "store_code")
    private String code;

    @Column(name = "location")
    private String location;

}
