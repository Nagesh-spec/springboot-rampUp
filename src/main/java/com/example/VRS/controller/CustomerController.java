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
    
    private final CustomerService customerService;
    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto createdCustomer = customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }
    
    @GetMapping
    public ResponseEntity<Page<CustomerDto>> getAllCustomers(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<CustomerDto> customers = customerService.getAllCustomers(pageable);
        return ResponseEntity.ok(customers);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> getAllCustomersNoPagination() {
        List<CustomerDto> customers = customerService.getAllCustomersNoPagination();
        return ResponseEntity.ok(customers);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomer = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
