package com.example.orderapis.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModelForConsume {
    private Long quantity;
    private String product_code;
}
