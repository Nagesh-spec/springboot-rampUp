package com.example.VRS.service;

import com.example.VRS.model.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;


public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);
    
    Page<CustomerDto> getAllCustomers(Pageable pageable);
    
    List<CustomerDto> getAllCustomersNoPagination();
    
    Optional<CustomerDto> getCustomerById(Long customerId);
    
    CustomerDto updateCustomer(Long customerId, CustomerDto customerDto);
    
    void deleteCustomer(Long customerId);
}
