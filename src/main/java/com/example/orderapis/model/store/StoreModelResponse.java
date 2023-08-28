package com.example.orderapis.model.store;

import com.example.orderapis.model.product.ProductModelForConsume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreModelResponse {
    private String store_code;
    private String location;
    private List<ProductModelForConsume> allocated_stock_infos;
}
