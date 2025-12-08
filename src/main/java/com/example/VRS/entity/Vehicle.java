package com.example.VRS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.VRS.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.VRS.enums.VehicleStatus;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "make", nullable = false)
    private String make;
    
    @Column(name = "model", nullable = false)
    private String model;
    
    @Column(name = "color")
    private String color;
    
    @Column(name = "year", nullable = false)
    private Integer year;
    
    @Column(name = "seating_capacity")
    private Integer seatingCapacity;
    
    @Column(name = "daily_rate", nullable = false)
    private BigDecimal dailyRate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private VehicleType type;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private VehicleStatus status;
    
    @Column(name = "registration_number", unique = true, nullable = false)
    private String registrationNumber;
    
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Rental> rentals;
    
}