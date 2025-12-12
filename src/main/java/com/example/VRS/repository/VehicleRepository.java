package com.example.VRS.repository;

import com.example.VRS.entity.Vehicle;
import com.example.VRS.enums.VehicleStatus;
import com.example.VRS.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    
    List<Vehicle> findByStatus(VehicleStatus status);
    
    List<Vehicle> findByType(VehicleType type);
    
    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);
    
    @Query("SELECT v FROM Vehicle v WHERE v.type = :type AND v.dailyRate <= :maxPrice AND v.status = com.example.VRS.enums.VehicleStatus.AVAILABLE")
    List<Vehicle> searchVehicles(@Param("type") VehicleType type, @Param("maxPrice") BigDecimal maxPrice);
    
    @Query("SELECT v FROM Vehicle v WHERE v.status = com.example.VRS.enums.VehicleStatus.AVAILABLE ORDER BY v.dailyRate ASC")
    List<Vehicle> findAllAvailableVehicles();
    
    @Query("SELECT v FROM Vehicle v WHERE v.dailyRate = (SELECT MAX(v2.dailyRate) FROM Vehicle v2)")
    Optional<Vehicle> findMostExpensiveVehicle();
    
    List<Vehicle> findByTypeAndDailyRateLessThanEqual(VehicleType type, BigDecimal dailyRate);
}
