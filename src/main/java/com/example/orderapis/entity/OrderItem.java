package com.example.orderapis.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDER_ITEM")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private Long Id;

    @Column(name = "is_shipped")
    private boolean is_shipped;

    @Column(name = "product_code")
    private String product_code;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(mappedBy = "orderItem")
    private List<ShipmentItem> shipmentItems = new ArrayList<>();
}
