package com.example.orderapis.mapper;

import com.example.orderapis.entity.Order;
import com.example.orderapis.model.order.OrderRequestDTO;
import com.example.orderapis.model.order.OrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {


    @Mapping(target = "customer", source = "orderRequestDTO.customerDetailedDTO") // Map customerDetailedDTO to customer
    @Mapping(target = "coupon", source = "orderRequestDTO.couponRequestDTO") // Map couponRequestDTO to coupon
    @Mapping(target = "orderDate", expression = "java(java.time.LocalDateTime.now())") // Set the orderDate to the current timestamp
    @Mapping(target = "status", constant = "PENDING") // Set the status to "PENDING"
    @Mapping(target = "id", ignore = true) // Ignore mapping the ID as it will be generated by the database
    public Order toEntity(OrderRequestDTO orderRequestDTO);


    @Mapping(target = "orderDate", source = "order.orderDate") // Map orderDate to shipmentDate
    @Mapping(target = "totalPrice", source = "order.totalPrice") // Map totalPrice
    @Mapping(target = "orderItems", source = "order.orderItems") // Map orderItems
    @Mapping(target = "coupon", source = "order.coupon") // Map coupon to couponRequestDTO
    @Mapping(target = "customer", source = "order.customer") // Map customer to customerDTO
    public OrderResponseDTO toDTO(Order order);
}
