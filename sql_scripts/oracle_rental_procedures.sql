-- Oracle SQL Procedures for Rental Management
-- Vehicle Rental System Database Procedures

-- =============================================
-- Author: Vehicle Rental System
-- Create date: December 9, 2025
-- Description: Oracle stored procedures for updating rental records
-- =============================================

-- Oracle Procedure to update rental status
CREATE OR REPLACE PROCEDURE UpdateRentalStatus(
    p_rental_id IN NUMBER,
    p_new_status IN VARCHAR2
)
IS
    v_vehicle_id NUMBER;
    v_old_status VARCHAR2(20);
    rental_not_found EXCEPTION;
BEGIN
    -- Get current status and vehicle ID
    SELECT status, vehicle_id 
    INTO v_old_status, v_vehicle_id
    FROM rentals 
    WHERE id = p_rental_id;
    
    -- Update rental status
    UPDATE rentals 
    SET status = p_new_status,
        actual_return_date = CASE 
            WHEN p_new_status = 'COMPLETED' AND actual_return_date IS NULL 
            THEN SYSDATE 
            ELSE actual_return_date 
        END
    WHERE id = p_rental_id;
    
    -- Update vehicle status based on rental status
    IF p_new_status = 'ACTIVE' AND v_old_status = 'PENDING' THEN
        UPDATE vehicles SET status = 'RENTED' WHERE id = v_vehicle_id;
    ELSIF p_new_status = 'COMPLETED' THEN
        UPDATE vehicles SET status = 'AVAILABLE' WHERE id = v_vehicle_id;
    ELSIF p_new_status = 'CANCELLED' THEN
        UPDATE vehicles SET status = 'AVAILABLE' WHERE id = v_vehicle_id;
    END IF;
    
    COMMIT;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Rental not found with ID: ' || p_rental_id);
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END UpdateRentalStatus;
/

-- =============================================
-- Oracle Procedure to update rental dates and recalculate cost
-- =============================================
CREATE OR REPLACE PROCEDURE UpdateRentalDates(
    p_rental_id IN NUMBER,
    p_start_date IN DATE,
    p_end_date IN DATE
)
IS
    v_vehicle_id NUMBER;
    v_daily_rate NUMBER(10,2);
    v_days NUMBER;
    v_total_cost NUMBER(10,2);
BEGIN
    -- Get vehicle information
    SELECT r.vehicle_id, v.daily_rate
    INTO v_vehicle_id, v_daily_rate
    FROM rentals r
    JOIN vehicles v ON r.vehicle_id = v.id
    WHERE r.id = p_rental_id;
    
    -- Validate dates
    IF p_start_date >= p_end_date THEN
        RAISE_APPLICATION_ERROR(-20002, 'End date must be after start date');
    END IF;
    
    -- Calculate rental duration and cost
    v_days := p_end_date - p_start_date;
    v_total_cost := v_daily_rate * v_days;
    
    -- Update rental dates and cost
    UPDATE rentals 
    SET start_date = p_start_date,
        end_date = p_end_date,
        total_cost = v_total_cost
    WHERE id = p_rental_id;
    
    COMMIT;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Rental not found with ID: ' || p_rental_id);
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END UpdateRentalDates;
/

-- =============================================
-- Oracle Procedure to complete a rental
-- =============================================
CREATE OR REPLACE PROCEDURE CompleteRental(
    p_rental_id IN NUMBER,
    p_actual_return_date IN DATE DEFAULT NULL
)
IS
    v_vehicle_id NUMBER;
    v_return_date DATE;
BEGIN
    -- Set return date (use provided date or current date)
    v_return_date := NVL(p_actual_return_date, SYSDATE);
    
    -- Get vehicle ID
    SELECT vehicle_id 
    INTO v_vehicle_id
    FROM rentals 
    WHERE id = p_rental_id;
    
    -- Update rental to completed status
    UPDATE rentals 
    SET status = 'COMPLETED',
        actual_return_date = v_return_date
    WHERE id = p_rental_id;
    
    -- Make vehicle available
    UPDATE vehicles 
    SET status = 'AVAILABLE' 
    WHERE id = v_vehicle_id;
    
    COMMIT;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Rental not found with ID: ' || p_rental_id);
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END CompleteRental;
/

-- =============================================
-- Oracle Procedure to update rental notes
-- =============================================
CREATE OR REPLACE PROCEDURE UpdateRentalNotes(
    p_rental_id IN NUMBER,
    p_notes IN VARCHAR2
)
IS
    v_rental_count NUMBER;
BEGIN
    -- Check if rental exists
    SELECT COUNT(*) 
    INTO v_rental_count
    FROM rentals 
    WHERE id = p_rental_id;
    
    IF v_rental_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Rental not found with ID: ' || p_rental_id);
    END IF;
    
    -- Update notes
    UPDATE rentals 
    SET notes = p_notes
    WHERE id = p_rental_id;
    
    COMMIT;
END UpdateRentalNotes;
/

-- =============================================
-- Oracle Procedure to update rental cost
-- =============================================
CREATE OR REPLACE PROCEDURE UpdateRentalCost(
    p_rental_id IN NUMBER,
    p_total_cost IN NUMBER
)
IS
    v_rental_count NUMBER;
BEGIN
    -- Check if rental exists
    SELECT COUNT(*) 
    INTO v_rental_count
    FROM rentals 
    WHERE id = p_rental_id;
    
    IF v_rental_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Rental not found with ID: ' || p_rental_id);
    END IF;
    
    -- Validate cost
    IF p_total_cost < 0 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Total cost must be non-negative');
    END IF;
    
    -- Update cost
    UPDATE rentals 
    SET total_cost = p_total_cost
    WHERE id = p_rental_id;
    
    COMMIT;
END UpdateRentalCost;
/

-- =============================================
-- Oracle Comprehensive procedure to update multiple rental fields
-- =============================================
CREATE OR REPLACE PROCEDURE UpdateRentalComprehensive(
    p_rental_id IN NUMBER,
    p_start_date IN DATE DEFAULT NULL,
    p_end_date IN DATE DEFAULT NULL,
    p_status IN VARCHAR2 DEFAULT NULL,
    p_notes IN VARCHAR2 DEFAULT NULL,
    p_actual_return_date IN DATE DEFAULT NULL,
    p_total_cost IN NUMBER DEFAULT NULL
)
IS
    v_vehicle_id NUMBER;
    v_daily_rate NUMBER(10,2);
    v_days NUMBER;
    v_calculated_cost NUMBER(10,2);
    v_old_status VARCHAR2(20);
    v_current_start_date DATE;
    v_current_end_date DATE;
    v_final_start_date DATE;
    v_final_end_date DATE;
BEGIN
    -- Get current rental and vehicle information
    SELECT r.vehicle_id, r.status, r.start_date, r.end_date, v.daily_rate
    INTO v_vehicle_id, v_old_status, v_current_start_date, v_current_end_date, v_daily_rate
    FROM rentals r
    JOIN vehicles v ON r.vehicle_id = v.id
    WHERE r.id = p_rental_id;
    
    -- Determine final dates
    v_final_start_date := NVL(p_start_date, v_current_start_date);
    v_final_end_date := NVL(p_end_date, v_current_end_date);
    
    -- Validate dates if provided
    IF v_final_start_date >= v_final_end_date THEN
        RAISE_APPLICATION_ERROR(-20002, 'End date must be after start date');
    END IF;
    
    -- Recalculate cost if dates changed
    IF p_start_date IS NOT NULL OR p_end_date IS NOT NULL THEN
        v_days := v_final_end_date - v_final_start_date;
        v_calculated_cost := v_daily_rate * v_days;
    END IF;
    
    -- Update rental fields (only update non-null values)
    UPDATE rentals 
    SET start_date = v_final_start_date,
        end_date = v_final_end_date,
        status = NVL(p_status, status),
        notes = NVL(p_notes, notes),
        actual_return_date = NVL(p_actual_return_date, actual_return_date),
        total_cost = CASE 
            WHEN p_total_cost IS NOT NULL THEN p_total_cost
            WHEN v_calculated_cost IS NOT NULL THEN v_calculated_cost
            ELSE total_cost
        END
    WHERE id = p_rental_id;
    
    -- Update vehicle status based on rental status change
    IF p_status IS NOT NULL AND p_status != v_old_status THEN
        IF p_status = 'ACTIVE' AND v_old_status = 'PENDING' THEN
            UPDATE vehicles SET status = 'RENTED' WHERE id = v_vehicle_id;
        ELSIF p_status = 'COMPLETED' THEN
            UPDATE vehicles SET status = 'AVAILABLE' WHERE id = v_vehicle_id;
        ELSIF p_status = 'CANCELLED' THEN
            UPDATE vehicles SET status = 'AVAILABLE' WHERE id = v_vehicle_id;
        END IF;
    END IF;
    
    COMMIT;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Rental not found with ID: ' || p_rental_id);
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END UpdateRentalComprehensive;
/

-- =============================================
-- Example usage of the Oracle procedures:
-- =============================================

/*
-- Update rental status
EXEC UpdateRentalStatus(1, 'ACTIVE');

-- Update rental dates
EXEC UpdateRentalDates(1, DATE '2025-12-10', DATE '2025-12-15');

-- Complete a rental
EXEC CompleteRental(1, DATE '2025-12-09');

-- Update rental notes
EXEC UpdateRentalNotes(1, 'Vehicle returned in excellent condition');

-- Update rental cost
EXEC UpdateRentalCost(1, 250.00);

-- Comprehensive update
EXEC UpdateRentalComprehensive(1, DATE '2025-12-10', DATE '2025-12-15', 'ACTIVE', 'Updated rental details', NULL, NULL);
*/