package com.example.orderapis.model.orderItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemModelForRequest {
    @NotBlank(message = "Product Code is required!")
    private String productCode;
    @NotNull
    @Min(value = 1, message = "Quantity must be a positive value greater than zero.")
    @Positive(message = "Quantity must be a positive value.")
    private Long quantity;
}
