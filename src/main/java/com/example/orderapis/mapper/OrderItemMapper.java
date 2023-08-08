package com.example.orderapis.mapper;


import com.example.orderapis.entity.OrderItem;
import com.example.orderapis.model.orderItem.OrderItemRequestDTO;
import com.example.orderapis.model.orderItem.OrderItemResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    public OrderItem toEntity(OrderItemRequestDTO orderItemRequestDTO);

    public OrderItemResponseDTO toDTO(OrderItem orderItem);
}
