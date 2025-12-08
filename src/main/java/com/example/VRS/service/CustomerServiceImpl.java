package com.example.VRS.service;

import com.example.VRS.entity.Customer;
import com.example.VRS.model.CustomerDto;
import com.example.VRS.repository.CustomerRepository;
import com.example.VRS.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer(
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getEmail(),
                customerDto.getPhoneNumber(),
                customerDto.getAddress(),
                customerDto.getCity(),
                customerDto.getState(),
                customerDto.getPostalCode(),
                customerDto.getLicenseNumber(),
                customerDto.getLicenseExpiry(),
                customerDto.getDateOfBirth()
        );
        
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDto(savedCustomer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDto> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .map(this::convertToDto);
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));
        
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        customer.setCity(customerDto.getCity());
        customer.setState(customerDto.getState());
        customer.setPostalCode(customerDto.getPostalCode());
        customer.setLicenseNumber(customerDto.getLicenseNumber());
        customer.setLicenseExpiry(customerDto.getLicenseExpiry());
        customer.setDateOfBirth(customerDto.getDateOfBirth());
        
        Customer updatedCustomer = customerRepository.save(customer);
        return convertToDto(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    private CustomerDto convertToDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getCity(),
                customer.getState(),
                customer.getPostalCode(),
                customer.getLicenseNumber(),
                customer.getLicenseExpiry(),
                customer.getDateOfBirth(),
                customer.getRegistrationDate()
        );
    }

}
