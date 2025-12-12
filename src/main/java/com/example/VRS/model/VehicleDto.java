package com.example.VRS.model;

import java.math.BigDecimal;

import com.example.VRS.enums.VehicleStatus;
import com.example.VRS.enums.VehicleType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private Long id;
    private String registrationNumber;
    private String make;
    private String model;
    private String color;
    private Integer year;
    private Integer seatingCapacity;
    private BigDecimal dailyRate;
    private VehicleType type;
    private VehicleStatus status;
}