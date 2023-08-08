package com.example.orderapis.service;

import com.example.orderapis.entity.Customer;
import com.example.orderapis.model.customer.CustomerDTO;
import com.example.orderapis.model.order.OrderRequestDTO;
import com.example.orderapis.model.order.OrderResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    void create(OrderRequestDTO orderModel);
    List<OrderResponseDTO> findByCustomerAndRange(CustomerDTO customerModel, LocalDate startDate, LocalDate endDate);
}
