package com.example.VRS.repository;

import com.example.VRS.entity.Rental;
import com.example.VRS.enums.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    
    List<Rental> findByCustomerId(Long customerId);
    
    List<Rental> findByVehicleId(Long vehicleId);
    
    List<Rental> findByStatus(RentalStatus status);
    
    @Query("SELECT r FROM Rental r WHERE r.customer.id = :customerId AND r.status = 'ACTIVE'")
    List<Rental> findActiveRentalsByCustomer(@Param("customerId") Long customerId);
    
    @Query("SELECT r FROM Rental r WHERE r.vehicle.id = :vehicleId AND r.status = 'ACTIVE'")
    List<Rental> findActiveRentalsByVehicle(@Param("vehicleId") Long vehicleId);
    
    @Query("SELECT r FROM Rental r WHERE r.startDate BETWEEN :startDate AND :endDate")
    List<Rental> findRentalsByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
