package com.example.VRS.controller;

import com.example.VRS.model.RentalDto;
import com.example.VRS.service.RentalService;
import com.example.VRS.enums.RentalStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    
    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }
    
    @PostMapping
    public ResponseEntity<RentalDto> createRental(@RequestBody RentalDto rentalDto) {
        RentalDto createdRental = rentalService.createRental(rentalDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRental);
    }
    
    @GetMapping
    public ResponseEntity<List<RentalDto>> getAllRentals() {
        List<RentalDto> rentals = rentalService.getAllRentals();
        return ResponseEntity.status(HttpStatus.OK).body(rentals);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RentalDto> updateRental(
            @PathVariable Long id,
            @RequestBody RentalDto rentalDto) {
        RentalDto updatedRental = rentalService.updateRental(id, rentalDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedRental);
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<RentalDto> updateRentalStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> statusUpdate) {
        RentalStatus status = RentalStatus.valueOf(statusUpdate.get("status").toUpperCase());
        RentalDto updatedRental = rentalService.updateRentalStatus(id, status);
        return ResponseEntity.ok(updatedRental);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }

}
