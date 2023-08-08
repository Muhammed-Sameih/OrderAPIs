package com.example.orderapis.mapper;


import com.example.orderapis.entity.OrderItem;
import com.example.orderapis.model.orderItem.OrderItemRequestDTO;
import com.example.orderapis.model.orderItem.OrderItemResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    public OrderItem toEntity(OrderItemRequestDTO orderItemRequestDTO);

    @Mapping(source = "product_code", target = "product_code")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "price", target = "price")
    public OrderItemResponseDTO toDTO(OrderItem orderItem);
}
