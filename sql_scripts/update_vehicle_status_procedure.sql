-- Oracle Procedure to update vehicle status
CREATE OR REPLACE PROCEDURE UpdateVehicleStatus(
    p_vehicle_id IN NUMBER,
    p_new_status IN VARCHAR2
)
IS
    v_vehicle_count NUMBER;
BEGIN
    -- Check if vehicle exists
    SELECT COUNT(*) 
    INTO v_vehicle_count
    FROM vehicles 
    WHERE id = p_vehicle_id;
    
    IF v_vehicle_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Vehicle not found with ID: ' || p_vehicle_id);
    END IF;
    
    -- Update vehicle status
    UPDATE vehicles 
    SET status = p_new_status
    WHERE id = p_vehicle_id;
    
    COMMIT;
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END UpdateVehicleStatus;
/

-- Test runner for VRS.UPDATEVEHICLESTATUS
SET SERVEROUTPUT ON
DECLARE
  P_VEHICLE_ID NUMBER;
  P_NEW_STATUS VARCHAR2(200);
BEGIN
  -- Test with actual values
  P_VEHICLE_ID := 1;  -- Change this to an existing vehicle ID
  P_NEW_STATUS := 'AVAILABLE';  -- Valid status: AVAILABLE, RENTED, MAINTENANCE
  
  -- Display what we're testing
  DBMS_OUTPUT.PUT_LINE('Testing UpdateVehicleStatus with:');
  DBMS_OUTPUT.PUT_LINE('Vehicle ID: ' || P_VEHICLE_ID);
  DBMS_OUTPUT.PUT_LINE('New Status: ' || P_NEW_STATUS);
  
  VRS.UPDATEVEHICLESTATUS(
    P_VEHICLE_ID => P_VEHICLE_ID,
    P_NEW_STATUS => P_NEW_STATUS);
    
  DBMS_OUTPUT.PUT_LINE('Procedure executed successfully!');
  
  -- Uncomment the rollback if you want to undo changes during testing
  -- ROLLBACK;
  
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    ROLLBACK;
    RAISE;
END;
/