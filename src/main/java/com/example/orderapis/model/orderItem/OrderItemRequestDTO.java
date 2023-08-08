package com.example.orderapis.model.orderItem;

import com.example.orderapis.model.shipmentItem.ShipmentItemDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequestDTO {

    @NotBlank(message = "Product Code is required!")
    private String product_code;

    @NotNull
    @Positive(message = "Quantity must be a positive value")
    private Long quantity;

}
