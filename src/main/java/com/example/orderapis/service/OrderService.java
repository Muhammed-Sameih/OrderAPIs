package com.example.orderapis.service;

import com.example.orderapis.model.order.OrderModelForRequest;
import com.example.orderapis.model.order.OrderModelForResponse;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    void create(OrderModelForRequest orderModel);
    List<OrderModelForResponse> findByCustomerAndRange(String customerEmail, LocalDateTime startDate, LocalDateTime endDate);
}
