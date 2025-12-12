package com.example.VRS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    
    @Column(name = "address", nullable = false)
    private String address;
    
    @Column(name = "city", nullable = false)
    private String city;
    
    @Column(name = "state", nullable = false)
    private String state;
    
    @Column(name = "postal_code", nullable = false)
    private String postalCode;
    
    @Column(name = "license_number", unique = true, nullable = false)
    private String licenseNumber;
    
    @Column(name = "license_expiry", nullable = false)
    private LocalDate licenseExpiry;
    
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
    
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rental> rentals;

}
