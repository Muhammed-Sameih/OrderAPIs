package com.example.orderapis.service;

import com.example.orderapis.entity.Customer;
import com.example.orderapis.entity.Store;
import com.example.orderapis.model.customer.CustomerDetailedDTO;
import com.example.orderapis.model.store.StoreServiceResponse;

public interface StoreService {
    Store createOrFindStore(StoreServiceResponse storeModel);
}
