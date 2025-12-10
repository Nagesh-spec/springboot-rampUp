package com.example.VRS.model;

import com.example.VRS.enums.RentalStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto {
    private Long id;
    private Long vehicleId;
    private String vehicleDetails;
    private Long customerId;
    private String customerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualReturnDate;
    private RentalStatus status;
    private BigDecimal totalCost;
    private String notes;
}
