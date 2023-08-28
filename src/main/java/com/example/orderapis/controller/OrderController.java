package com.example.orderapis.controller;

import com.example.orderapis.model.order.OrderModelForRequest;
import com.example.orderapis.model.order.OrderModelForResponse;
import com.example.orderapis.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public void create(@RequestBody @Valid OrderModelForRequest orderModel) {orderService.create(orderModel);}

    @GetMapping("/by-customer-and-date-range")
    public List<OrderModelForResponse> getOrdersByCustomerAndDateRange(
            @RequestParam("email") @NotBlank(message = "Customer email is required!") @Email(message = "Customer email is not valid!") String email,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {return orderService.findByCustomerAndRange(email, startDate, endDate);}
}
