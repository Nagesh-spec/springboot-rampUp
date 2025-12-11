package com.example.VRS.config;

import com.example.VRS.entity.Vehicle;
import com.example.VRS.entity.Customer;
import com.example.VRS.entity.Rental;
import com.example.VRS.enums.VehicleType;
import com.example.VRS.enums.VehicleStatus;
import com.example.VRS.enums.RentalStatus;
import com.example.VRS.repository.VehicleRepository;
import com.example.VRS.repository.CustomerRepository;
import com.example.VRS.repository.RentalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private VehicleRepository vehicleRepository;    
    private CustomerRepository customerRepository;
    private RentalRepository rentalRepository;

    public DataInitializer(VehicleRepository vehicleRepository,CustomerRepository customerRepository,RentalRepository rentalRepository){
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
        this.rentalRepository = rentalRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        if (vehicleRepository.count() == 0) {
            initializeVehicles();
            initializeCustomers();
            initializeRentals();
            System.out.println("Database initialized with mock data!");
        }
    }
    
    private void initializeVehicles() {
        // Sedan vehicles
        Vehicle sedan1 = new Vehicle(null, "Toyota", "Camry", "Silver", 2023, 5, 
                new BigDecimal("60"), VehicleType.SEDAN, VehicleStatus.AVAILABLE, "ABC-001", null);
        Vehicle sedan2 = new Vehicle(null, "Honda", "Accord", "Black", 2023, 5, 
                new BigDecimal("65"), VehicleType.SEDAN, VehicleStatus.AVAILABLE, "ABC-002",null);
        Vehicle sedan3 = new Vehicle(null, "BMW", "3 Series", "White", 2022, 5, 
                new BigDecimal("95"), VehicleType.SEDAN, VehicleStatus.AVAILABLE, "ABC-003", null);
        
        // SUV vehicles
        Vehicle suv1 = new Vehicle(null, "Toyota", "RAV4", "Red", 2023, 5, 
                new BigDecimal("85"), VehicleType.SUV, VehicleStatus.AVAILABLE, "XYZ-001",null);
        Vehicle suv2 = new Vehicle(null, "Honda", "CR-V", "Blue", 2023, 5, 
                new BigDecimal("80"), VehicleType.SUV, VehicleStatus.AVAILABLE, "XYZ-002",null);
        Vehicle suv3 = new Vehicle(null, "BMW", "X5", "Black", 2022, 7, 
                new BigDecimal("150"), VehicleType.SUV, VehicleStatus.AVAILABLE, "XYZ-003",null);
        
        // Hatchback vehicles
        Vehicle hatch1 = new Vehicle(null, "Honda", "Civic", "Silver", 2023, 5, 
                new BigDecimal("50"), VehicleType.HATCHBACK, VehicleStatus.AVAILABLE, "HAT-001", null);
        Vehicle hatch2 = new Vehicle(null, "Toyota", "Corolla", "White", 2023, 5, 
                new BigDecimal("48"), VehicleType.HATCHBACK, VehicleStatus.AVAILABLE, "HAT-002", null);
        
        // Luxury vehicle
        Vehicle luxury1 = new Vehicle(null, "Mercedes", "S-Class", "Black", 2022, 5, 
                new BigDecimal("200"), VehicleType.LUXURY, VehicleStatus.AVAILABLE, "LUX-001", null);
        Vehicle luxury2 = new Vehicle(null, "Audi", "A8", "Silver", 2023, 5, 
                new BigDecimal("180"), VehicleType.LUXURY, VehicleStatus.AVAILABLE, "LUX-002", null);
        
        // Van vehicles
        Vehicle van1 = new Vehicle(null, "Toyota", "Sienna", "White", 2023, 8, 
                new BigDecimal("120"), VehicleType.VAN, VehicleStatus.AVAILABLE, "VAN-001", null);
        Vehicle van2 = new Vehicle(null, "Honda", "Odyssey", "Gray", 2022, 8, 
                new BigDecimal("115"), VehicleType.VAN, VehicleStatus.AVAILABLE, "VAN-002", null);
        
        // Motorcycle
        Vehicle bike1 = new Vehicle(null, "Harley-Davidson", "Street 750", "Black", 2023, 2, 
                new BigDecimal("35"), VehicleType.MOTORCYCLE, VehicleStatus.AVAILABLE, "BIKE-001", null);
        Vehicle bike2 = new Vehicle(null, "Yamaha", "YZF-R1", "Red", 2023, 2, 
                new BigDecimal("40"), VehicleType.MOTORCYCLE, VehicleStatus.AVAILABLE, "BIKE-002", null);
        
        
        vehicleRepository.save(sedan1);
        vehicleRepository.save(sedan2);
        vehicleRepository.save(sedan3);
        vehicleRepository.save(suv1);
        vehicleRepository.save(suv2);
        vehicleRepository.save(suv3);
        vehicleRepository.save(hatch1);
        vehicleRepository.save(hatch2);
        vehicleRepository.save(luxury1);
        vehicleRepository.save(luxury2);
        vehicleRepository.save(van1);
        vehicleRepository.save(van2);
        vehicleRepository.save(bike1);
        vehicleRepository.save(bike2);
    }
    
    private void initializeCustomers() {
        // Create customers
        Customer cust1 = new Customer(null, "John", "Doe", "john.doe@email.com", "555-0001", 
                "123 Main St", "New York", "NY", "10001", 
                "DL001", LocalDate.of(2026, 12, 31), LocalDate.of(1990, 5, 15), null, null);
        
        Customer cust2 = new Customer(null, "Jane", "Smith", "jane.smith@email.com", "555-0002", 
                "456 Oak Ave", "Los Angeles", "CA", "90001", 
                "DL002", LocalDate.of(2026, 6, 30), LocalDate.of(1985, 3, 20), null, null);
        
        Customer cust3 = new Customer(null, "Robert", "Johnson", "robert.johnson@email.com", "555-0003", 
                "789 Pine St", "Chicago", "IL", "60601", 
                "DL003", LocalDate.of(2025, 9, 15), LocalDate.of(1988, 7, 10), null, null);
        
        Customer cust4 = new Customer(null, "Maria", "Williams", "maria.williams@email.com", "555-0004", 
                "321 Elm St", "Houston", "TX", "77001", 
                "DL004", LocalDate.of(2026, 3, 22), LocalDate.of(1992, 11, 8), null, null);
        
        Customer cust5 = new Customer(null, "Michael", "Brown", "michael.brown@email.com", "555-0005", 
                "654 Maple Dr", "Phoenix", "AZ", "85001", 
                "DL005", LocalDate.of(2025, 7, 14), LocalDate.of(1987, 9, 25), null, null);
        
        customerRepository.save(cust1);
        customerRepository.save(cust2);
        customerRepository.save(cust3);
        customerRepository.save(cust4);
        customerRepository.save(cust5);
    }
    
    private void initializeRentals() {
        // Get sample vehicles and customers
        Vehicle vehicle1 = vehicleRepository.findByRegistrationNumber("ABC-001").orElse(null);
        Vehicle vehicle2 = vehicleRepository.findByRegistrationNumber("XYZ-001").orElse(null);
        Customer customer1 = customerRepository.findByEmail("john.doe@email.com").orElse(null);
        Customer customer2 = customerRepository.findByEmail("jane.smith@email.com").orElse(null);
        
        if (vehicle1 != null && customer1 != null) {
            LocalDate startDate = LocalDate.now().plusDays(1);
            LocalDate endDate = startDate.plusDays(5);
            Rental rental1 = new Rental(null, vehicle1, customer1, startDate, endDate, endDate, RentalStatus.PENDING, null, null, endDate);
            rental1.setTotalCost(new BigDecimal("60").multiply(BigDecimal.valueOf(5)));
            rentalRepository.save(rental1);
        }
        
        if (vehicle2 != null && customer2 != null) {
            LocalDate startDate = LocalDate.now().plusDays(3);
            LocalDate endDate = startDate.plusDays(7);
            Rental rental2 = new Rental(null, vehicle2, customer2, startDate, endDate, endDate, RentalStatus.PENDING, null, null, endDate);
            rental2.setTotalCost(new BigDecimal("85").multiply(BigDecimal.valueOf(7)));
            rentalRepository.save(rental2);
        }
    }
}
