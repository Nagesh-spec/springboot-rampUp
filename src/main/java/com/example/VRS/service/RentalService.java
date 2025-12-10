package com.example.VRS.service;

import com.example.VRS.model.RentalDto;
import com.example.VRS.enums.RentalStatus;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.math.BigDecimal;

public interface RentalService {
    RentalDto createRental(RentalDto rentalDto);
    
    List<RentalDto> getAllRentals();
    
    Optional<RentalDto> getRentalById(Long rentalId);
    
    RentalDto updateRental(Long rentalId, RentalDto rentalDto);
    
    // Enhanced update methods
    RentalDto updateRentalStatus(Long rentalId, RentalStatus status);
    
    RentalDto updateRentalDates(Long rentalId, LocalDate startDate, LocalDate endDate);
    
    RentalDto completeRental(Long rentalId, LocalDate actualReturnDate);
    
    RentalDto updateRentalNotes(Long rentalId, String notes);
    
    RentalDto updateRentalCost(Long rentalId, BigDecimal totalCost);
    
    void deleteRental(Long rentalId);
}
