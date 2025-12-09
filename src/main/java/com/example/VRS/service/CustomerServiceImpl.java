package com.example.VRS.service;

import com.example.VRS.entity.Customer;
import com.example.VRS.model.CustomerDto;
import com.example.VRS.repository.CustomerRepository;
import com.example.VRS.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.CallableStatementCallback;
import oracle.jdbc.OracleTypes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        try {
            // Call stored procedure using JdbcTemplate
            return getAllCustomersFromProcedure();
        } catch (Exception e) {
            System.err.println("Error calling stored procedure: " + e.getMessage());
            // Fallback to regular JPA if procedure fails
            return customerRepository.findAll().stream()
                    .map(this::convertToDto)
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
                        CustomerDto customerDto = mapResultSetToDto(rs);
                        customers.add(customerDto);
                    }
                }
                
                return customers;
            }
        );
    }
    
    private CustomerDto mapResultSetToDto(ResultSet rs) throws SQLException {
        return new CustomerDto(
            rs.getLong("ID"),
            rs.getString("FIRST_NAME"),
            rs.getString("LAST_NAME"),
            rs.getString("EMAIL"),
            rs.getString("PHONE_NUMBER"),
            rs.getString("ADDRESS"),
            rs.getString("CITY"),
            rs.getString("STATE"),
            rs.getString("POSTAL_CODE"),
            rs.getString("LICENSE_NUMBER"),
            rs.getDate("LICENSE_EXPIRY") != null ? rs.getDate("LICENSE_EXPIRY").toLocalDate() : null,
            rs.getDate("DATE_OF_BIRTH") != null ? rs.getDate("DATE_OF_BIRTH").toLocalDate() : null,
            rs.getDate("REGISTRATION_DATE") != null ? rs.getDate("REGISTRATION_DATE").toLocalDate() : null
        );
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
