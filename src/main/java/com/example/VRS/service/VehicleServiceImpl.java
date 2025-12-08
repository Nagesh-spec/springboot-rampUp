package com.example.VRS.service;

import com.example.VRS.entity.Vehicle;
import com.example.VRS.enums.VehicleType;
import com.example.VRS.model.VehicleDto;
import com.example.VRS.repository.VehicleRepository;
import com.example.VRS.exception.ResourceNotFoundException;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("vehicleService")
@Primary
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = new Vehicle(null, null, null, null, null, null, null, null, null, null, null);
        vehicle.setMake(vehicleDto.getBrand());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setColor(vehicleDto.getColor());
        vehicle.setYear(vehicleDto.getYear());
        vehicle.setSeatingCapacity(vehicleDto.getSeatingCapacity());
        vehicle.setDailyRate(vehicleDto.getDailyRate());
        vehicle.setType(vehicleDto.getType());
        vehicle.setStatus(vehicleDto.getStatus());
        vehicle.setRegistrationNumber(vehicleDto.getRegistrationNumber());
        
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return convertToDto(savedVehicle);
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<VehicleDto> getVehicleById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .map(this::convertToDto);
    }

    @Override
    public VehicleDto updateVehicle(Long vehicleId, VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + vehicleId));
        
        vehicle.setMake(vehicleDto.getBrand());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setColor(vehicleDto.getColor());
        vehicle.setYear(vehicleDto.getYear());
        vehicle.setSeatingCapacity(vehicleDto.getSeatingCapacity());
        vehicle.setDailyRate(vehicleDto.getDailyRate());
        vehicle.setType(vehicleDto.getType());
        vehicle.setStatus(vehicleDto.getStatus());
        
        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return convertToDto(updatedVehicle);
    }


    @Override
    public void deleteVehicle(Long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    public List<VehicleDto> getAvailableVehicles() {
        return vehicleRepository.findAllAvailableVehicles().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDto> searchVehicles(VehicleType type, BigDecimal maxPrice) {
        return vehicleRepository.searchVehicles(type, maxPrice).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private VehicleDto convertToDto(Vehicle vehicle) {
        return new VehicleDto(
                vehicle.getId(),
                vehicle.getRegistrationNumber(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getYear(),
                vehicle.getSeatingCapacity(),
                vehicle.getDailyRate(),
                vehicle.getType(),
                vehicle.getStatus()
        );
    }
}
