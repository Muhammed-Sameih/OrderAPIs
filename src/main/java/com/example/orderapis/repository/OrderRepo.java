package com.example.orderapis.repository;

import com.example.orderapis.entity.Customer;
import com.example.orderapis.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByCustomerAndOrderDateBetween(Customer customer, LocalDateTime startDate, LocalDateTime endDate);
}
