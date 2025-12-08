package com.example.VRS.exception;

/**
 * Exception thrown when rental operation is invalid
 */
public class InvalidRentalException extends RuntimeException {
    public InvalidRentalException(String message) {
        super(message);
    }

    public InvalidRentalException(String message, Throwable cause) {
        super(message, cause);
    }
}
