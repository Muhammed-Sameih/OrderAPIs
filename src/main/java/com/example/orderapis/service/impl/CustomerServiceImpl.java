package com.example.orderapis.service.impl;

import com.example.orderapis.entity.Customer;
import com.example.orderapis.model.customer.CustomerDetailedDTO;
import com.example.orderapis.repository.CustomerRepo;
import com.example.orderapis.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepository;

    @Override
    public Customer createOrFindCustomer(CustomerDetailedDTO customerDTO) {
        Optional<Customer> existingCustomer = customerRepository.findByEmail(customerDTO.getEmail());
        return existingCustomer.orElseGet(() -> createCustomer(customerDTO));
    }

    private Customer createCustomer(CustomerDetailedDTO customerDTO) {
        Customer customer = new Customer();
        customer.setEmail(customerDTO.getEmail());
        customer.setLocation(customerDTO.getLocation());
        return customerRepository.save(customer);
    }

}
