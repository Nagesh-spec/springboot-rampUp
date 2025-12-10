package com.example.VRS.service;

import com.example.VRS.entity.Customer;
import com.example.VRS.model.CustomerDto;
import com.example.VRS.repository.CustomerRepository;
import com.example.VRS.exception.ResourceNotFoundException;
import com.example.VRS.mapper.CustomerMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.CallableStatementCallback;
import oracle.jdbc.OracleTypes;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    private CustomerRepository customerRepository;
    
    private JdbcTemplate jdbcTemplate;
    
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository,JdbcTemplate jdbcTemplate,CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    @Override
    public Page<CustomerDto> getAllCustomers(Pageable pageable) {
        // Use JPA pagination directly - let exceptions bubble up for proper error handling
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        List<CustomerDto> customerDtos = customerPage.getContent().stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
        
        return new PageImpl<>(customerDtos, pageable, customerPage.getTotalElements());
    }
    
    @Override
    public List<CustomerDto> getAllCustomersNoPagination() {
        try {
            // Call stored procedure using JdbcTemplate
            return getAllCustomersFromProcedure();
        } catch (Exception e) {
            System.err.println("Error calling stored procedure: " + e.getMessage());
            // Fallback to regular JPA if procedure fails
            return customerRepository.findAll().stream()
                    .map(customerMapper::toDto)
                    .collect(Collectors.toList());
        }
    }
    
    private List<CustomerDto> getAllCustomersFromProcedure() {
        return jdbcTemplate.execute(
            "{ call GET_ALL_CUSTOMERS(?) }",
            (CallableStatementCallback<List<CustomerDto>>) callableStatement -> {
                // Register the OUT parameter as REF_CURSOR
                callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
                
                // Execute the procedure
                callableStatement.execute();
                
                // Get the result set from the cursor
                List<CustomerDto> customers = new ArrayList<>();
                try (ResultSet rs = (ResultSet) callableStatement.getObject(1)) {
                    while (rs.next()) {
                        CustomerDto customerDto = customerMapper.mapResultSetToDto(rs);
                        customers.add(customerDto);
                    }
                }
                
                return customers;
            }
        );
    }


    @Override
    public Optional<CustomerDto> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::toDto);
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));
        
        customerMapper.updateEntityFromDto(customerDto, customer);
        
        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

}

