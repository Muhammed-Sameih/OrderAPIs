package com.example.orderapis.mapper;

import com.example.orderapis.entity.Order;
import com.example.orderapis.model.order.OrderModelForRequest;
import com.example.orderapis.model.order.OrderModelForResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "coupon", source = "coupon")
    @Mapping(target = "orderItems", source = "orderItems")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "status", ignore = true)
    Order toEntity(OrderModelForRequest orderRequestDTO);
    OrderModelForResponse toModel(Order order);
}

