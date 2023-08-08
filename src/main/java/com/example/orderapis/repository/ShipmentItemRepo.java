package com.example.orderapis.repository;

import com.example.orderapis.entity.ShipmentItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentItemRepo extends JpaRepository<ShipmentItem, Long> {
}
