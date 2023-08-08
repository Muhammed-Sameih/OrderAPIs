package com.example.orderapis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "SHIPMENT")
@Data
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "shipment_date")
    private LocalDateTime shipmentDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "status")
    private String status;


    @OneToMany(mappedBy = "shipment")
    private List<ShipmentItem> shipmentItems;
}
