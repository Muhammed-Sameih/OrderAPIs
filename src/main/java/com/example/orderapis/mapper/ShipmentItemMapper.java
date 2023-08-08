package com.example.orderapis.mapper;

import com.example.orderapis.entity.ShipmentItem;
import com.example.orderapis.model.shipmentItem.ShipmentItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShipmentItemMapper {
    public ShipmentItem toEntity(ShipmentItemDTO shipmentItemDTO);

    public ShipmentItemDTO toDTO(ShipmentItem shipmentItem);
}
