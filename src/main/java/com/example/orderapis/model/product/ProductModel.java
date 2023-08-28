package com.example.orderapis.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
    private long id;
    private String name;
    private String productCode;
    private String description;
    private BigDecimal price;
    private String categoryName;
    private String brandName;
    private String imageUri;
    private boolean active;
    private int rating = 0;
}
