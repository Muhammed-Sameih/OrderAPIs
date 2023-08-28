package com.example.orderapis.model.shipping;

import com.example.orderapis.model.store.StoreModelResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingModel {
    private String orderCode;
    private String customerEmail;
    private String CustomerLocation;
    private List<StoreModelResponse> storesInfo;
}
