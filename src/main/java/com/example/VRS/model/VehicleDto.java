package com.example.VRS.model;

import java.math.BigDecimal;

import com.example.VRS.enums.VehicleStatus;
import com.example.VRS.enums.VehicleType;

public class VehicleDto{
    private Long id;
    private String registrationNumber;
    private String brand;
    private String model;
    private String color;
    private Integer year;
    private Integer seatingCapacity;
    private BigDecimal dailyRate;
    private VehicleType type;
    private VehicleStatus status;

    public VehicleDto() {
    }

    public VehicleDto(Long id,String registrationNumber, String brand, String model, String color, Integer year,
            Integer seatingCapacity, BigDecimal dailyRate, VehicleType type, VehicleStatus status) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
        this.seatingCapacity = seatingCapacity;
        this.dailyRate = dailyRate;
        this.type = type;
        this.status = status;
    }

    public Integer getYear() {
        return year;
    }
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public VehicleType getType() {
        return type;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }
}