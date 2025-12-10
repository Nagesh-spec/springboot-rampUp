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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalServiceImpl implements RentalService {
    
    private RentalRepository rentalRepository;
    
    private VehicleRepository vehicleRepository;
    
    private CustomerRepository customerRepository;

    public RentalServiceImpl(RentalRepository rentalRepository,VehicleRepository vehicleRepository,CustomerRepository customerRepository){
        this.rentalRepository = rentalRepository;
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }

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
        
        Rental rental = new Rental(null, vehicle, customer, rentalDto.getStartDate(), rentalDto.getEndDate(), null, RentalStatus.PENDING, null, null, null);
        
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
        
        // Update basic fields
        if (rentalDto.getStartDate() != null) {
            rental.setStartDate(rentalDto.getStartDate());
        }
        if (rentalDto.getEndDate() != null) {
            rental.setEndDate(rentalDto.getEndDate());
        }
        if (rentalDto.getStatus() != null) {
            updateRentalStatusInternal(rental, rentalDto.getStatus());
        }
        if (rentalDto.getNotes() != null) {
            rental.setNotes(rentalDto.getNotes());
        }
        if (rentalDto.getActualReturnDate() != null) {
            rental.setActualReturnDate(rentalDto.getActualReturnDate());
        }
        if (rentalDto.getTotalCost() != null) {
            rental.setTotalCost(rentalDto.getTotalCost());
        }
        
        // Recalculate total cost if dates changed
        if (rentalDto.getStartDate() != null || rentalDto.getEndDate() != null) {
            recalculateTotalCost(rental);
        }
        
        Rental updatedRental = rentalRepository.save(rental);
        return convertToDto(updatedRental);
    }

    @Override
    public RentalDto updateRentalStatus(Long rentalId, RentalStatus status) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with ID: " + rentalId));
        
        updateRentalStatusInternal(rental, status);
        
        Rental updatedRental = rentalRepository.save(rental);
        return convertToDto(updatedRental);
    }

    @Override
    public RentalDto updateRentalDates(Long rentalId, LocalDate startDate, LocalDate endDate) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with ID: " + rentalId));
        
        if (startDate != null) {
            rental.setStartDate(startDate);
        }
        if (endDate != null) {
            rental.setEndDate(endDate);
        }
        
        // Recalculate total cost
        recalculateTotalCost(rental);
        
        Rental updatedRental = rentalRepository.save(rental);
        return convertToDto(updatedRental);
    }

    @Override
    public RentalDto completeRental(Long rentalId, LocalDate actualReturnDate) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with ID: " + rentalId));
        
        rental.setActualReturnDate(actualReturnDate);
        rental.setStatus(RentalStatus.COMPLETED);
        
        // Release the vehicle
        Vehicle vehicle = rental.getVehicle();
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        vehicleRepository.save(vehicle);
        
        Rental updatedRental = rentalRepository.save(rental);
        return convertToDto(updatedRental);
    }

    @Override
    public RentalDto updateRentalNotes(Long rentalId, String notes) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with ID: " + rentalId));
        
        rental.setNotes(notes);
        
        Rental updatedRental = rentalRepository.save(rental);
        return convertToDto(updatedRental);
    }

    @Override
    public RentalDto updateRentalCost(Long rentalId, BigDecimal totalCost) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with ID: " + rentalId));
        
        if (totalCost != null && totalCost.compareTo(BigDecimal.ZERO) >= 0) {
            rental.setTotalCost(totalCost);
        } else {
            throw new InvalidRentalException("Total cost must be non-negative");
        }
        
        Rental updatedRental = rentalRepository.save(rental);
        return convertToDto(updatedRental);
    }

    /**
     * Internal method to handle status updates and related business logic
     */
    private void updateRentalStatusInternal(Rental rental, RentalStatus newStatus) {
        RentalStatus oldStatus = rental.getStatus();
        rental.setStatus(newStatus);
        
        Vehicle vehicle = rental.getVehicle();
        
        // Handle status-specific business logic
        switch (newStatus) {
            case ACTIVE:
                if (oldStatus == RentalStatus.PENDING) {
                    vehicle.setStatus(VehicleStatus.RENTED);
                    vehicleRepository.save(vehicle);
                }
                break;
            case COMPLETED:
                vehicle.setStatus(VehicleStatus.AVAILABLE);
                vehicleRepository.save(vehicle);
                if (rental.getActualReturnDate() == null) {
                    rental.setActualReturnDate(java.time.LocalDate.now());
                }
                break;
            case CANCELLED:
                vehicle.setStatus(VehicleStatus.AVAILABLE);
                vehicleRepository.save(vehicle);
                break;
            case PENDING:
                // No specific action needed
                break;
        }
    }

    /**
     * Recalculates the total cost based on start and end dates
     */
    private void recalculateTotalCost(Rental rental) {
        if (rental.getStartDate() != null && rental.getEndDate() != null) {
            long days = java.time.temporal.ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());
            if (days <= 0) {
                throw new InvalidRentalException("End date must be after start date");
            }
            BigDecimal totalCost = rental.getVehicle().getDailyRate().multiply(BigDecimal.valueOf(days));
            rental.setTotalCost(totalCost);
        }
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
