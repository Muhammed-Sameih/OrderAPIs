package com.example.orderapis.model.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreServiceResponse {
    private String code;
    private String location;
    private Long quantity;
}
