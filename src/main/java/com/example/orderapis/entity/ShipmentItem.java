package com.example.orderapis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SHIPMENT_ITEM")
@Data
public class ShipmentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "quantity")
    private Long quantity;
}
