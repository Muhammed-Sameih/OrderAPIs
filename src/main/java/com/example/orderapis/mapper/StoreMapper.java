package com.example.orderapis.mapper;

import com.example.orderapis.entity.Store;
import com.example.orderapis.model.store.StoreResponseDTO;
import com.example.orderapis.model.store.StoreServiceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    public Store toEntity(StoreServiceResponse storeResponse);

    public StoreResponseDTO toDTO(Store store);

}
