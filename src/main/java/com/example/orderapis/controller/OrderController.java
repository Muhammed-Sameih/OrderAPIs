package com.example.orderapis.controller;

import com.example.orderapis.model.customer.CustomerDTO;
import com.example.orderapis.model.order.OrderRequestDTO;
import com.example.orderapis.model.order.OrderResponseDTO;
import com.example.orderapis.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("orders/")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public void create(@RequestBody @Valid OrderRequestDTO orderModel) {orderService.create(orderModel);}


    // Endpoint to get orders by customer email and date range
    @PostMapping("/by-customer-and-date-range")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByCustomerAndDateRange(
            @RequestBody CustomerDTO customerDTO,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<OrderResponseDTO> orders = orderService.findByCustomerAndRange(customerDTO, startDate, endDate);
        if (!orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
