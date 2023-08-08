package com.example.orderapis.mapper;

import com.example.orderapis.entity.ShipmentItem;
import com.example.orderapis.model.shipmentItem.ShipmentItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShipmentItemMapper {
    public ShipmentItem toEntity(ShipmentItemDTO shipmentItemDTO);

    @Mapping(source = "shipment", target = "shipment")
    @Mapping(source = "store", target = "store")
    public ShipmentItemDTO toDTO(ShipmentItem shipmentItem);
}
