package com.example.orderapis.service;

import com.example.orderapis.entity.Customer;
import com.example.orderapis.model.customer.CustomerDetailedDTO;

public interface CustomerService {
    Customer createOrFindCustomer(CustomerDetailedDTO customerDTO);
}
