-- Vehicle Rental System Mock Data Insert Script - Oracle Database Compatible
-- This script inserts mock data for customers, vehicles, and rentals tables
-- Total records: ~65 (25 customers + 20 vehicles + 20 rentals)

-- =================================================================
-- CUSTOMERS TABLE INSERTS (25 records)
-- =================================================================

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('John', 'Smith', 'john.smith@email.com', '555-0101', '123 Main Street', 'New York', 'NY', '10001', 'DL001001', TO_DATE('2026-05-15', 'YYYY-MM-DD'), TO_DATE('1985-03-12', 'YYYY-MM-DD'), TO_DATE('2023-01-15', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Sarah', 'Johnson', 'sarah.johnson@email.com', '555-0102', '456 Oak Avenue', 'Los Angeles', 'CA', '90210', 'DL002002', TO_DATE('2025-08-22', 'YYYY-MM-DD'), TO_DATE('1990-07-08', 'YYYY-MM-DD'), TO_DATE('2023-02-10', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Michael', 'Williams', 'michael.williams@email.com', '555-0103', '789 Pine Street', 'Chicago', 'IL', '60601', 'DL003003', TO_DATE('2027-01-10', 'YYYY-MM-DD'), TO_DATE('1988-11-25', 'YYYY-MM-DD'), TO_DATE('2023-02-20', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Emily', 'Brown', 'emily.brown@email.com', '555-0104', '321 Elm Drive', 'Houston', 'TX', '77001', 'DL004004', TO_DATE('2026-09-18', 'YYYY-MM-DD'), TO_DATE('1992-04-14', 'YYYY-MM-DD'), TO_DATE('2023-03-05', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('David', 'Davis', 'david.davis@email.com', '555-0105', '654 Maple Lane', 'Phoenix', 'AZ', '85001', 'DL005005', TO_DATE('2025-12-03', 'YYYY-MM-DD'), TO_DATE('1987-09-30', 'YYYY-MM-DD'), TO_DATE('2023-03-15', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Jessica', 'Miller', 'jessica.miller@email.com', '555-0106', '987 Cedar Court', 'Philadelphia', 'PA', '19101', 'DL006006', TO_DATE('2026-06-28', 'YYYY-MM-DD'), TO_DATE('1991-01-22', 'YYYY-MM-DD'), TO_DATE('2023-04-01', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Christopher', 'Wilson', 'chris.wilson@email.com', '555-0107', '147 Birch Boulevard', 'San Antonio', 'TX', '78201', 'DL007007', TO_DATE('2027-03-15', 'YYYY-MM-DD'), TO_DATE('1989-08-17', 'YYYY-MM-DD'), TO_DATE('2023-04-10', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Amanda', 'Moore', 'amanda.moore@email.com', '555-0108', '258 Spruce Street', 'San Diego', 'CA', '92101', 'DL008008', TO_DATE('2025-11-20', 'YYYY-MM-DD'), TO_DATE('1993-05-09', 'YYYY-MM-DD'), TO_DATE('2023-04-25', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Daniel', 'Taylor', 'daniel.taylor@email.com', '555-0109', '369 Willow Way', 'Dallas', 'TX', '75201', 'DL009009', TO_DATE('2026-04-12', 'YYYY-MM-DD'), TO_DATE('1986-12-03', 'YYYY-MM-DD'), TO_DATE('2023-05-08', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Ashley', 'Anderson', 'ashley.anderson@email.com', '555-0110', '741 Aspen Avenue', 'San Jose', 'CA', '95101', 'DL010010', TO_DATE('2027-07-25', 'YYYY-MM-DD'), TO_DATE('1994-02-28', 'YYYY-MM-DD'), TO_DATE('2023-05-20', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Matthew', 'Thomas', 'matthew.thomas@email.com', '555-0111', '852 Hickory Hill', 'Austin', 'TX', '73301', 'DL011011', TO_DATE('2026-01-08', 'YYYY-MM-DD'), TO_DATE('1987-06-15', 'YYYY-MM-DD'), TO_DATE('2023-06-02', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Jennifer', 'Jackson', 'jennifer.jackson@email.com', '555-0112', '963 Poplar Place', 'Jacksonville', 'FL', '32099', 'DL012012', TO_DATE('2025-10-14', 'YYYY-MM-DD'), TO_DATE('1990-09-11', 'YYYY-MM-DD'), TO_DATE('2023-06-15', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Joshua', 'White', 'joshua.white@email.com', '555-0113', '159 Magnolia Drive', 'Fort Worth', 'TX', '76101', 'DL013013', TO_DATE('2026-12-30', 'YYYY-MM-DD'), TO_DATE('1988-03-27', 'YYYY-MM-DD'), TO_DATE('2023-07-01', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Michelle', 'Harris', 'michelle.harris@email.com', '555-0114', '753 Dogwood Lane', 'Columbus', 'OH', '43004', 'DL014014', TO_DATE('2027-05-17', 'YYYY-MM-DD'), TO_DATE('1992-10-05', 'YYYY-MM-DD'), TO_DATE('2023-07-12', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Andrew', 'Martin', 'andrew.martin@email.com', '555-0115', '486 Sycamore Street', 'Charlotte', 'NC', '28201', 'DL015015', TO_DATE('2025-09-06', 'YYYY-MM-DD'), TO_DATE('1989-01-19', 'YYYY-MM-DD'), TO_DATE('2023-08-01', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Nicole', 'Thompson', 'nicole.thompson@email.com', '555-0116', '268 Redwood Road', 'San Francisco', 'CA', '94102', 'DL016016', TO_DATE('2026-08-23', 'YYYY-MM-DD'), TO_DATE('1991-07-13', 'YYYY-MM-DD'), TO_DATE('2023-08-18', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Ryan', 'Garcia', 'ryan.garcia@email.com', '555-0117', '135 Palm Avenue', 'Indianapolis', 'IN', '46201', 'DL017017', TO_DATE('2027-02-09', 'YYYY-MM-DD'), TO_DATE('1986-05-31', 'YYYY-MM-DD'), TO_DATE('2023-09-05', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Stephanie', 'Martinez', 'stephanie.martinez@email.com', '555-0118', '579 Chestnut Circle', 'Seattle', 'WA', '98101', 'DL018018', TO_DATE('2026-11-11', 'YYYY-MM-DD'), TO_DATE('1993-12-07', 'YYYY-MM-DD'), TO_DATE('2023-09-20', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Kevin', 'Robinson', 'kevin.robinson@email.com', '555-0119', '913 Beech Boulevard', 'Denver', 'CO', '80201', 'DL019019', TO_DATE('2025-07-04', 'YYYY-MM-DD'), TO_DATE('1987-04-26', 'YYYY-MM-DD'), TO_DATE('2023-10-08', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Rachel', 'Clark', 'rachel.clark@email.com', '555-0120', '467 Walnut Way', 'Washington', 'DC', '20001', 'DL020020', TO_DATE('2026-03-19', 'YYYY-MM-DD'), TO_DATE('1990-11-14', 'YYYY-MM-DD'), TO_DATE('2023-10-25', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Brandon', 'Rodriguez', 'brandon.rodriguez@email.com', '555-0121', '821 Peach Street', 'Boston', 'MA', '02101', 'DL021021', TO_DATE('2027-06-12', 'YYYY-MM-DD'), TO_DATE('1988-08-02', 'YYYY-MM-DD'), TO_DATE('2023-11-10', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Megan', 'Lewis', 'megan.lewis@email.com', '555-0122', '394 Cherry Lane', 'El Paso', 'TX', '79901', 'DL022022', TO_DATE('2025-12-27', 'YYYY-MM-DD'), TO_DATE('1992-01-18', 'YYYY-MM-DD'), TO_DATE('2023-11-25', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Tyler', 'Lee', 'tyler.lee@email.com', '555-0123', '672 Apple Court', 'Detroit', 'MI', '48201', 'DL023023', TO_DATE('2026-10-05', 'YYYY-MM-DD'), TO_DATE('1989-06-23', 'YYYY-MM-DD'), TO_DATE('2023-12-08', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Laura', 'Walker', 'laura.walker@email.com', '555-0124', '105 Orange Drive', 'Nashville', 'TN', '37201', 'DL024024', TO_DATE('2027-04-21', 'YYYY-MM-DD'), TO_DATE('1991-03-16', 'YYYY-MM-DD'), TO_DATE('2024-01-15', 'YYYY-MM-DD'));

INSERT INTO customers (first_name, last_name, email, phone_number, address, city, state, postal_code, license_number, license_expiry, date_of_birth, registration_date) VALUES
('Jason', 'Hall', 'jason.hall@email.com', '555-0125', '738 Lemon Street', 'Memphis', 'TN', '38101', 'DL025025', TO_DATE('2026-07-08', 'YYYY-MM-DD'), TO_DATE('1986-12-29', 'YYYY-MM-DD'), TO_DATE('2024-02-01', 'YYYY-MM-DD'));

-- =================================================================
-- VEHICLES TABLE INSERTS (20 records)
-- =================================================================

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Toyota', 'Camry', 'Silver', 2022, 5, 45.00, 'SEDAN', 'AVAILABLE', 'VEH001');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Honda', 'CR-V', 'White', 2023, 5, 55.00, 'SUV', 'AVAILABLE', 'VEH002');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Ford', 'Focus', 'Blue', 2021, 5, 40.00, 'HATCHBACK', 'AVAILABLE', 'VEH003');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('BMW', '3 Series', 'Black', 2023, 5, 85.00, 'LUXURY', 'RENTED', 'VEH004');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Chevrolet', 'Express', 'White', 2022, 8, 75.00, 'VAN', 'AVAILABLE', 'VEH005');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Harley-Davidson', 'Street 750', 'Black', 2021, 2, 65.00, 'MOTORCYCLE', 'AVAILABLE', 'VEH006');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Nissan', 'Altima', 'Red', 2022, 5, 42.00, 'SEDAN', 'AVAILABLE', 'VEH007');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Jeep', 'Grand Cherokee', 'Green', 2023, 5, 68.00, 'SUV', 'MAINTENANCE', 'VEH008');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Volkswagen', 'Golf', 'Yellow', 2021, 5, 38.00, 'HATCHBACK', 'AVAILABLE', 'VEH009');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Mercedes-Benz', 'C-Class', 'Silver', 2023, 5, 95.00, 'LUXURY', 'AVAILABLE', 'VEH010');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Ford', 'Transit', 'Blue', 2022, 12, 80.00, 'VAN', 'RENTED', 'VEH011');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Yamaha', 'MT-07', 'Blue', 2022, 2, 55.00, 'MOTORCYCLE', 'AVAILABLE', 'VEH012');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Hyundai', 'Elantra', 'Gray', 2021, 5, 41.00, 'SEDAN', 'AVAILABLE', 'VEH013');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Mazda', 'CX-5', 'White', 2023, 5, 58.00, 'SUV', 'AVAILABLE', 'VEH014');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Subaru', 'Impreza', 'Orange', 2022, 5, 44.00, 'HATCHBACK', 'RETIRED', 'VEH015');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Audi', 'A4', 'Black', 2023, 5, 90.00, 'LUXURY', 'AVAILABLE', 'VEH016');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Chrysler', 'Pacifica', 'Red', 2021, 8, 72.00, 'VAN', 'AVAILABLE', 'VEH017');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Kawasaki', 'Ninja 400', 'Green', 2022, 2, 60.00, 'MOTORCYCLE', 'RENTED', 'VEH018');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Kia', 'Optima', 'Purple', 2021, 5, 39.00, 'SEDAN', 'AVAILABLE', 'VEH019');

INSERT INTO vehicles (make, model, color, year, seating_capacity, daily_rate, type, status, registration_number) VALUES
('Acura', 'MDX', 'Brown', 2023, 7, 78.00, 'SUV', 'AVAILABLE', 'VEH020');

-- =================================================================
-- RENTALS TABLE INSERTS (20 records)
-- =================================================================

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(4, 1, TO_DATE('2024-01-15', 'YYYY-MM-DD'), TO_DATE('2024-01-20', 'YYYY-MM-DD'), TO_DATE('2024-01-20', 'YYYY-MM-DD'), 'COMPLETED', 425.00, 'Customer satisfied with luxury service', TO_DATE('2024-01-15', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(11, 3, TO_DATE('2024-02-01', 'YYYY-MM-DD'), TO_DATE('2024-02-05', 'YYYY-MM-DD'), NULL, 'ACTIVE', 400.00, 'Corporate rental for business trip', TO_DATE('2024-02-01', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(18, 5, TO_DATE('2024-02-10', 'YYYY-MM-DD'), TO_DATE('2024-02-12', 'YYYY-MM-DD'), TO_DATE('2024-02-12', 'YYYY-MM-DD'), 'COMPLETED', 120.00, 'Weekend motorcycle rental', TO_DATE('2024-02-10', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(1, 7, TO_DATE('2024-02-15', 'YYYY-MM-DD'), TO_DATE('2024-02-18', 'YYYY-MM-DD'), NULL, 'ACTIVE', 135.00, 'Regular customer, clean driving record', TO_DATE('2024-02-15', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(2, 9, TO_DATE('2024-02-20', 'YYYY-MM-DD'), TO_DATE('2024-02-25', 'YYYY-MM-DD'), TO_DATE('2024-02-25', 'YYYY-MM-DD'), 'COMPLETED', 275.00, 'Family vacation rental', TO_DATE('2024-02-20', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(3, 11, TO_DATE('2024-03-01', 'YYYY-MM-DD'), TO_DATE('2024-03-03', 'YYYY-MM-DD'), TO_DATE('2024-03-04', 'YYYY-MM-DD'), 'COMPLETED', 120.00, 'Returned one day late, extra charges applied', TO_DATE('2024-03-01', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(5, 13, TO_DATE('2024-03-05', 'YYYY-MM-DD'), TO_DATE('2024-03-10', 'YYYY-MM-DD'), NULL, 'CANCELLED', 0.00, 'Customer cancelled due to emergency', TO_DATE('2024-03-05', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(7, 15, TO_DATE('2024-03-12', 'YYYY-MM-DD'), TO_DATE('2024-03-15', 'YYYY-MM-DD'), TO_DATE('2024-03-15', 'YYYY-MM-DD'), 'COMPLETED', 126.00, 'Smooth rental experience', TO_DATE('2024-03-12', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(9, 17, TO_DATE('2024-03-18', 'YYYY-MM-DD'), TO_DATE('2024-03-20', 'YYYY-MM-DD'), TO_DATE('2024-03-20', 'YYYY-MM-DD'), 'COMPLETED', 76.00, 'First-time customer', TO_DATE('2024-03-18', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(10, 19, TO_DATE('2024-03-22', 'YYYY-MM-DD'), TO_DATE('2024-03-27', 'YYYY-MM-DD'), NULL, 'ACTIVE', 475.00, 'Luxury car for special occasion', TO_DATE('2024-03-22', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(12, 21, TO_DATE('2024-04-01', 'YYYY-MM-DD'), TO_DATE('2024-04-03', 'YYYY-MM-DD'), TO_DATE('2024-04-03', 'YYYY-MM-DD'), 'COMPLETED', 110.00, 'Motorcycle enthusiast, excellent condition return', TO_DATE('2024-04-01', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(13, 23, TO_DATE('2024-04-05', 'YYYY-MM-DD'), TO_DATE('2024-04-08', 'YYYY-MM-DD'), NULL, 'PENDING', 123.00, 'Awaiting pickup confirmation', TO_DATE('2024-04-05', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(14, 25, TO_DATE('2024-04-10', 'YYYY-MM-DD'), TO_DATE('2024-04-14', 'YYYY-MM-DD'), TO_DATE('2024-04-14', 'YYYY-MM-DD'), 'COMPLETED', 232.00, 'SUV for family trip', TO_DATE('2024-04-10', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(16, 2, TO_DATE('2024-04-15', 'YYYY-MM-DD'), TO_DATE('2024-04-18', 'YYYY-MM-DD'), NULL, 'ACTIVE', 270.00, 'Repeat customer, premium service', TO_DATE('2024-04-15', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(17, 4, TO_DATE('2024-04-20', 'YYYY-MM-DD'), TO_DATE('2024-04-23', 'YYYY-MM-DD'), TO_DATE('2024-04-23', 'YYYY-MM-DD'), 'COMPLETED', 216.00, 'Van rental for moving purposes', TO_DATE('2024-04-20', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(19, 6, TO_DATE('2024-05-01', 'YYYY-MM-DD'), TO_DATE('2024-05-04', 'YYYY-MM-DD'), NULL, 'PENDING', 117.00, 'Economy option requested', TO_DATE('2024-05-01', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(20, 8, TO_DATE('2024-05-05', 'YYYY-MM-DD'), TO_DATE('2024-05-09', 'YYYY-MM-DD'), TO_DATE('2024-05-09', 'YYYY-MM-DD'), 'COMPLETED', 312.00, 'Large SUV for group travel', TO_DATE('2024-05-05', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(1, 10, TO_DATE('2024-05-12', 'YYYY-MM-DD'), TO_DATE('2024-05-15', 'YYYY-MM-DD'), NULL, 'CANCELLED', 0.00, 'Weather-related cancellation', TO_DATE('2024-05-12', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(6, 12, TO_DATE('2024-05-18', 'YYYY-MM-DD'), TO_DATE('2024-05-20', 'YYYY-MM-DD'), TO_DATE('2024-05-20', 'YYYY-MM-DD'), 'COMPLETED', 130.00, 'Motorcycle rental for touring', TO_DATE('2024-05-18', 'YYYY-MM-DD'));

INSERT INTO rentals (vehicle_id, customer_id, start_date, end_date, actual_return_date, status, total_cost, notes, rental_date) VALUES
(7, 14, TO_DATE('2024-05-25', 'YYYY-MM-DD'), TO_DATE('2024-05-28', 'YYYY-MM-DD'), NULL, 'ACTIVE', 126.00, 'Extended weekend rental', TO_DATE('2024-05-25', 'YYYY-MM-DD'));

-- =================================================================
-- VERIFICATION QUERIES (Uncomment to run after insert)
-- =================================================================

SELECT COUNT(*) as total_customers FROM customers;
SELECT COUNT(*) as total_vehicles FROM vehicles;
SELECT COUNT(*) as total_rentals FROM rentals;

-- Sample queries to verify data integrity:
-- SELECT v.make, v.model, v.status, COUNT(r.id) as rental_count 
-- FROM vehicles v LEFT JOIN rentals r ON v.id = r.vehicle_id 
-- GROUP BY v.id, v.make, v.model, v.status;

-- SELECT c.first_name, c.last_name, COUNT(r.id) as total_rentals 
-- FROM customers c LEFT JOIN rentals r ON c.id = r.customer_id 
-- GROUP BY c.id, c.first_name, c.last_name 
-- ORDER BY total_rentals DESC;

-- SELECT status, COUNT(*) as count FROM rentals GROUP BY status;
-- SELECT type, COUNT(*) as count FROM vehicles GROUP BY type;
-- SELECT status, COUNT(*) as count FROM vehicles GROUP BY status;

COMMIT;