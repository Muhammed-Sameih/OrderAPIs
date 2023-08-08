package com.example.orderapis.mapper;

import com.example.orderapis.entity.Customer;
import com.example.orderapis.model.customer.CustomerDTO;
import com.example.orderapis.model.customer.CustomerDetailedDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerDTO customerModel);
    Customer toEntity(CustomerDetailedDTO customerModel);

    CustomerDetailedDTO toCustomerDetailedDTO(Customer customer);
}
