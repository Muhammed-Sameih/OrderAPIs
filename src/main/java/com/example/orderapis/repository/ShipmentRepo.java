package com.example.orderapis.repository;

import com.example.orderapis.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepo extends JpaRepository<Shipment, Long> {
}
