package com.example.VRS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.VRS.enums.RentalStatus;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "rentals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    @Column(name = "actual_return_date")
    private LocalDate actualReturnDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RentalStatus status;
    
    @Column(name = "total_cost")
    private BigDecimal totalCost;
    
    @Column(name = "notes")
    private String notes;
    
    @Column(name = "rental_date")
    private LocalDate rentalDate;
}