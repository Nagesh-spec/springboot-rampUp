# Rental Update Procedures Documentation

## Overview
This document explains the various methods available for updating rental records in the Vehicle Rental System.

## API Endpoints

### 1. Complete Rental Update
**Endpoint:** `PUT /api/rentals/{id}`
**Description:** Updates all fields of a rental record
```json
{
  "startDate": "2025-12-10",
  "endDate": "2025-12-15",
  "status": "ACTIVE",
  "notes": "Updated rental information",
  "actualReturnDate": "2025-12-15",
  "totalCost": 250.00
}
```

### 2. Update Rental Status
**Endpoint:** `PATCH /api/rentals/{id}/status`
**Description:** Updates only the rental status and handles related business logic
```json
{
  "status": "COMPLETED"
}
```
**Available statuses:** PENDING, ACTIVE, COMPLETED, CANCELLED

### 3. Update Rental Dates
**Endpoint:** `PATCH /api/rentals/{id}/dates`
**Description:** Updates start and/or end dates and recalculates total cost
```json
{
  "startDate": "2025-12-10",
  "endDate": "2025-12-15"
}
```

### 4. Complete Rental
**Endpoint:** `PATCH /api/rentals/{id}/complete`
**Description:** Marks a rental as completed and releases the vehicle
```json
{
  "actualReturnDate": "2025-12-09"
}
```
Note: If no date is provided, current date is used.

### 5. Update Rental Notes
**Endpoint:** `PATCH /api/rentals/{id}/notes`
**Description:** Updates only the notes field
```json
{
  "notes": "Vehicle returned in excellent condition"
}
```

### 6. Update Rental Cost
**Endpoint:** `PATCH /api/rentals/{id}/cost`
**Description:** Updates the total cost manually
```json
{
  "totalCost": "275.50"
}
```

## Business Logic

### Status Update Logic
- **PENDING → ACTIVE**: Vehicle status changes to RENTED
- **ANY → COMPLETED**: Vehicle status changes to AVAILABLE, actual return date set if null
- **ANY → CANCELLED**: Vehicle status changes to AVAILABLE

### Cost Calculation
- When dates are updated, total cost is automatically recalculated: `daily_rate × number_of_days`
- Manual cost updates override automatic calculations

### Date Validation
- End date must be after start date
- System prevents invalid date ranges

## SQL Stored Procedures

### Available Procedures
1. `UpdateRentalStatus(rental_id, new_status)`
2. `UpdateRentalDates(rental_id, start_date, end_date)`
3. `CompleteRental(rental_id, actual_return_date)`
4. `UpdateRentalNotes(rental_id, notes)`
5. `UpdateRentalCost(rental_id, total_cost)`
6. `UpdateRentalComprehensive(rental_id, start_date, end_date, status, notes, actual_return_date, total_cost)`

### Example SQL Usage
```sql
-- Update status
CALL UpdateRentalStatus(1, 'ACTIVE');

-- Update dates
CALL UpdateRentalDates(1, '2025-12-10', '2025-12-15');

-- Complete rental
CALL CompleteRental(1, '2025-12-09');

-- Update notes
CALL UpdateRentalNotes(1, 'Excellent condition');

-- Comprehensive update (null values are ignored)
CALL UpdateRentalComprehensive(1, '2025-12-10', '2025-12-15', 'ACTIVE', 'Updated', NULL, NULL);
```

## Error Handling

### Common Exceptions
- `ResourceNotFoundException`: Rental ID does not exist
- `InvalidRentalException`: Business rule violation (e.g., invalid dates, negative cost)

### HTTP Status Codes
- `200 OK`: Successful update
- `404 NOT FOUND`: Rental not found
- `400 BAD REQUEST`: Invalid input data

## Examples

### Example 1: Start a rental
```bash
curl -X PATCH http://localhost:8080/api/rentals/1/status \
  -H "Content-Type: application/json" \
  -d '{"status": "ACTIVE"}'
```

### Example 2: Complete a rental
```bash
curl -X PATCH http://localhost:8080/api/rentals/1/complete \
  -H "Content-Type: application/json" \
  -d '{"actualReturnDate": "2025-12-09"}'
```

### Example 3: Update rental dates
```bash
curl -X PATCH http://localhost:8080/api/rentals/1/dates \
  -H "Content-Type: application/json" \
  -d '{"startDate": "2025-12-10", "endDate": "2025-12-20"}'
```

### Example 4: Add notes
```bash
curl -X PATCH http://localhost:8080/api/rentals/1/notes \
  -H "Content-Type: application/json" \
  -d '{"notes": "Customer requested vehicle inspection"}'
```

## Integration Notes

- All update operations are transactional
- Vehicle status is automatically managed based on rental status
- Cost recalculation occurs automatically when dates change
- Optimistic updates: only provided fields are modified
- Full audit trail maintained through JPA/Hibernate