package com.example.VRS.controller;

import com.example.VRS.model.CustomerDto;
import com.example.VRS.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    private CustomerService customerService;
    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @PostMapping("/create")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto createdCustomer = customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }
    
    @GetMapping("/get")
    public ResponseEntity<Page<CustomerDto>> getAllCustomers(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<CustomerDto> customers = customerService.getAllCustomers(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
    
    @GetMapping("/get/all")
    public ResponseEntity<List<CustomerDto>> getAllCustomersNoPagination() {
        List<CustomerDto> customers = customerService.getAllCustomersNoPagination();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @PutMapping("update/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomer = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }
    
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
    
}
