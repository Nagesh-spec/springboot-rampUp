package com.example.VRS.service;

import com.example.VRS.entity.Rental;
import com.example.VRS.entity.Vehicle;
import com.example.VRS.entity.Customer;
import com.example.VRS.enums.RentalStatus;
import com.example.VRS.enums.VehicleStatus;
import com.example.VRS.model.RentalDto;
import com.example.VRS.repository.RentalRepository;
import com.example.VRS.repository.VehicleRepository;
import com.example.VRS.repository.CustomerRepository;
import com.example.VRS.exception.ResourceNotFoundException;
import com.example.VRS.exception.InvalidRentalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalServiceImpl implements RentalService {
    
    @Autowired
    private RentalRepository rentalRepository;
    
    @Autowired
    private VehicleRepository vehicleRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public RentalDto createRental(RentalDto rentalDto) {
        Vehicle vehicle = vehicleRepository.findById(rentalDto.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + rentalDto.getVehicleId()));
        
        Customer customer = customerRepository.findById(rentalDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + rentalDto.getCustomerId()));
        
        // Check if vehicle is available
        if (vehicle.getStatus() != VehicleStatus.AVAILABLE) {
            throw new InvalidRentalException("Vehicle is not available for rental");
        }
        
        Rental rental = new Rental(vehicle, customer, rentalDto.getStartDate(), rentalDto.getEndDate(), RentalStatus.PENDING);
        
        // Calculate total cost
        long days = java.time.temporal.ChronoUnit.DAYS.between(rentalDto.getStartDate(), rentalDto.getEndDate());
        BigDecimal totalCost = vehicle.getDailyRate().multiply(BigDecimal.valueOf(days));
        rental.setTotalCost(totalCost);
        
        // Update vehicle status
        vehicle.setStatus(VehicleStatus.RENTED);
        vehicleRepository.save(vehicle);
        
        Rental savedRental = rentalRepository.save(rental);
        return convertToDto(savedRental);
    }

    @Override
    public List<RentalDto> getAllRentals() {
        return rentalRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RentalDto> getRentalById(Long rentalId) {
        return rentalRepository.findById(rentalId)
                .map(this::convertToDto);
    }

    @Override
    public RentalDto updateRental(Long rentalId, RentalDto rentalDto) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with ID: " + rentalId));
        
        rental.setStartDate(rentalDto.getStartDate());
        rental.setEndDate(rentalDto.getEndDate());
        rental.setStatus(rentalDto.getStatus());
        rental.setNotes(rentalDto.getNotes());
        
        Rental updatedRental = rentalRepository.save(rental);
        return convertToDto(updatedRental);
    }

    @Override
    public void deleteRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with ID: " + rentalId));
        
        // Release vehicle if rental is cancelled
        Vehicle vehicle = rental.getVehicle();
        if (rental.getStatus() == RentalStatus.PENDING || rental.getStatus() == RentalStatus.CANCELLED) {
            vehicle.setStatus(VehicleStatus.AVAILABLE);
            vehicleRepository.save(vehicle);
        }
        
        rentalRepository.deleteById(rentalId);
    }

    private RentalDto convertToDto(Rental rental) {
        String vehicleDetails = rental.getVehicle().getMake() + " " + rental.getVehicle().getModel();
        String customerName = rental.getCustomer().getFirstName() + " " + rental.getCustomer().getLastName();
        
        return new RentalDto(
                rental.getId(),
                rental.getVehicle().getId(),
                vehicleDetails,
                rental.getCustomer().getId(),
                customerName,
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getActualReturnDate(),
                rental.getStatus(),
                rental.getTotalCost(),
                rental.getNotes()
        );
    }
}
