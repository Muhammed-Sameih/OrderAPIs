package com.example.orderapis.model.orderItem;

import com.example.orderapis.model.shipmentItem.ShipmentItemDTO;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDTO {

    private Long id;

    @NotBlank(message = "Product Code is required!")
    private String product_code;

    @Positive(message = "Quantity must be a positive value")
    private Long quantity;

    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    private BigDecimal price;

    private List<ShipmentItemDTO> shipmentItemDTOs;
}
