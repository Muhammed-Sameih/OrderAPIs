package com.example.orderapis.model.orderItem;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemModelForResponse {
    private Long id;
    @NotBlank(message = "Product Code is required!")
    private String product_code;
    @Positive(message = "Quantity must be a positive value")
    private Long quantity;
    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    private BigDecimal price;
}
