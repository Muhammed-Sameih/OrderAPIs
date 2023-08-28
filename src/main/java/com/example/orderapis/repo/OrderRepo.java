package com.example.orderapis.repo;

import com.example.orderapis.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByCustomerEmailAndOrderDateBetween(String customerEmail, LocalDateTime startDate, LocalDateTime endDate);
}
