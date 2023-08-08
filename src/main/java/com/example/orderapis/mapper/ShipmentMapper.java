package com.example.orderapis.mapper;

import com.example.orderapis.entity.Shipment;
import com.example.orderapis.model.shipment.ShipmentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {

    public Shipment toEntity(ShipmentDTO shipmentDTO);

    public ShipmentDTO toDTO(Shipment shipment);
}
