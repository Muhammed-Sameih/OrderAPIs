package com.example.orderapis.model.shipmentItem;

import com.example.orderapis.model.shipment.ShipmentDTO;
import com.example.orderapis.model.store.StoreResponseDTO;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentItemDTO {

        private ShipmentDTO shipment;

        private StoreResponseDTO store;

        @Positive(message = "Quantity must be a positive value")
        private Long quantity;
}
