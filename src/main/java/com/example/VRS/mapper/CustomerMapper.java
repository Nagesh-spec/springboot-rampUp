package com.example.VRS.mapper;

import com.example.VRS.entity.Customer;
import com.example.VRS.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

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
}