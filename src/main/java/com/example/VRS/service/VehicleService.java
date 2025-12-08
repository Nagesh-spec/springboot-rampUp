package com.example.VRS.service;

import com.example.VRS.enums.VehicleType;
import com.example.VRS.model.VehicleDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface VehicleService {
    VehicleDto createVehicle(VehicleDto vehicleDto);
    
    List<VehicleDto> getAllVehicles();
    
    Optional<VehicleDto> getVehicleById(Long vehicleId);
    
    VehicleDto updateVehicle(Long vehicleId, VehicleDto vehicleDto);
    
    void deleteVehicle(Long vehicleId);
    
    List<VehicleDto> searchVehicles(VehicleType type, BigDecimal maxPrice);

}
