package com.example.VRS.mapper;

import com.example.VRS.entity.Customer;
import com.example.VRS.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    /**
     * Convert Customer entity to CustomerDto
     */
    CustomerDto toDto(Customer customer);

    /**
     * Convert CustomerDto to Customer entity
     * @param customerDto the DTO to convert
     * @return Customer entity (id will be null for new entities)
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "rentals", ignore = true)
    Customer toEntity(CustomerDto customerDto);

    /**
     * Update existing Customer entity with data from CustomerDto
     * @param customerDto the DTO with updated data
     * @param customer the existing entity to update
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "rentals", ignore = true)
    void updateEntityFromDto(CustomerDto customerDto, @MappingTarget Customer customer);

    /**
     * Map ResultSet to CustomerDto (for stored procedure results)
     */
    default CustomerDto mapResultSetToDto(ResultSet rs) throws SQLException {
        CustomerDto dto = new CustomerDto();
        dto.setId(rs.getLong("ID"));
        dto.setFirstName(rs.getString("FIRST_NAME"));
        dto.setLastName(rs.getString("LAST_NAME"));
        dto.setEmail(rs.getString("EMAIL"));
        dto.setPhoneNumber(rs.getString("PHONE_NUMBER"));
        dto.setAddress(rs.getString("ADDRESS"));
        dto.setCity(rs.getString("CITY"));
        dto.setState(rs.getString("STATE"));
        dto.setPostalCode(rs.getString("POSTAL_CODE"));
        dto.setLicenseNumber(rs.getString("LICENSE_NUMBER"));
        dto.setLicenseExpiry(rs.getDate("LICENSE_EXPIRY") != null ? rs.getDate("LICENSE_EXPIRY").toLocalDate() : null);
        dto.setDateOfBirth(rs.getDate("DATE_OF_BIRTH") != null ? rs.getDate("DATE_OF_BIRTH").toLocalDate() : null);
        dto.setRegistrationDate(rs.getDate("REGISTRATION_DATE") != null ? rs.getDate("REGISTRATION_DATE").toLocalDate() : null);
        return dto;
    }
}