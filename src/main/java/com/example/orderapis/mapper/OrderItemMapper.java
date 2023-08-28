package com.example.orderapis.mapper;

import com.example.orderapis.entity.OrderItem;
import com.example.orderapis.model.orderItem.OrderItemModelForRequest;
import com.example.orderapis.model.orderItem.OrderItemModelForResponse;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(target = "order", ignore = true)
    OrderItem toEntity(@Valid OrderItemModelForRequest orderItemModel);
    OrderItemModelForResponse toModel(OrderItem orderItem);
}

