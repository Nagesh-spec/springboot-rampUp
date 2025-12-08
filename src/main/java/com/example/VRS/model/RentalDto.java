package com.example.VRS.model;

import com.example.VRS.enums.RentalStatus;
import java.time.LocalDate;
import java.math.BigDecimal;

public class RentalDto {
    private Long id;
    private Long vehicleId;
    private String vehicleDetails;
    private Long customerId;
    private String customerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualReturnDate;
    private RentalStatus status;
    private BigDecimal totalCost;
    private String notes;

    public RentalDto() {
    }

    public RentalDto(Long id, Long vehicleId, String vehicleDetails, Long customerId, 
                    String customerName, LocalDate startDate, LocalDate endDate, 
                    LocalDate actualReturnDate, RentalStatus status, BigDecimal totalCost, String notes) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.vehicleDetails = vehicleDetails;
        this.customerId = customerId;
        this.customerName = customerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.actualReturnDate = actualReturnDate;
        this.status = status;
        this.totalCost = totalCost;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public String getNotes() {
        return notes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }
}
