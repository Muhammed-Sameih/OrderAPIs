package com.example.orderapis.service.impl;

import com.example.orderapis.entity.Store;
import com.example.orderapis.model.store.StoreServiceResponse;
import com.example.orderapis.repository.StoreRepo;
import com.example.orderapis.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepo storeRepo;

    @Override
    public Store createOrFindStore(StoreServiceResponse storeModel) {
        Optional<Store> existingStore = storeRepo.findByCode(storeModel.getCode());
        return existingStore.orElseGet(() -> createStore(storeModel));
    }

    private Store createStore(StoreServiceResponse storeModel) {
        Store store = new Store();
        store.setCode(storeModel.getCode());
        store.setLocation(storeModel.getLocation());
        return storeRepo.save(store);
    }
}
