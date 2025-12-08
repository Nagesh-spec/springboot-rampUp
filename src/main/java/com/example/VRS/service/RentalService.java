package com.example.VRS.service;

import com.example.VRS.model.RentalDto;
import java.util.List;
import java.util.Optional;

public interface RentalService {
    RentalDto createRental(RentalDto rentalDto);
    
    List<RentalDto> getAllRentals();
    
    Optional<RentalDto> getRentalById(Long rentalId);
    
    RentalDto updateRental(Long rentalId, RentalDto rentalDto);
    
    void deleteRental(Long rentalId);
}
