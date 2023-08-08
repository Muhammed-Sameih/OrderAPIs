package com.example.orderapis.controller;

import com.example.orderapis.model.customer.CustomerDTO;
import com.example.orderapis.model.order.OrderRequestDTO;
import com.example.orderapis.model.order.OrderResponseDTO;
import com.example.orderapis.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("orders/")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public void create(@RequestBody @Valid OrderRequestDTO orderModel) {orderService.create(orderModel);}

    // Endpoint to get orders by customer email and date range
    @GetMapping("/by-customer-and-date-range")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByCustomerAndDateRange(
            @RequestParam("email") @NotBlank(message = "Customer email is required!") @Email(message = "Customer email is not valid!") String email,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setEmail(email);

        List<OrderResponseDTO> orders = orderService.findByCustomerAndRange(customerDTO, startDate, endDate);
        if (!orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}