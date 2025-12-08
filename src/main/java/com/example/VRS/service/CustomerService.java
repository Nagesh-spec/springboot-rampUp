package com.example.VRS.service;

import com.example.VRS.model.CustomerDto;
import java.util.List;
import java.util.Optional;


public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);
    
    List<CustomerDto> getAllCustomers();
    
    Optional<CustomerDto> getCustomerById(Long customerId);
    
    CustomerDto updateCustomer(Long customerId, CustomerDto customerDto);
    
    void deleteCustomer(Long customerId);
}
