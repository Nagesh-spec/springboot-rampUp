package com.example.VRS.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Simple Logger Service using SLF4J for demonstration purposes
 */
@Slf4j
@Service
public class LoggerService {

    /**
     * Log an info message
     * @param message the message to log
     */
    public void logInfo(String message) {
        log.info("INFO: {}", message);
    }

    /**
     * Log a warning message
     * @param message the message to log
     */
    public void logWarning(String message) {
        log.warn("WARNING: {}", message);
    }

    /**
     * Log an error message
     * @param message the message to log
     */
    public void logError(String message) {
        log.error("ERROR: {}", message);
    }

    /**
     * Log an error message with exception
     * @param message the message to log
     * @param throwable the exception
     */
    public void logError(String message, Throwable throwable) {
        log.error("ERROR: {}", message, throwable);
    }

    /**
     * Log a debug message
     * @param message the message to log
     */
    public void logDebug(String message) {
        log.debug("DEBUG: {}", message);
    }

    /**
     * Log an operation with execution time
     * @param operation the operation name
     * @param executionTimeMs execution time in milliseconds
     */
    public void logOperationTime(String operation, long executionTimeMs) {
        log.info("Operation '{}' completed in {} ms", operation, executionTimeMs);
    }

    /**
     * Log user activity
     * @param userId the user ID
     * @param action the action performed
     */
    public void logUserActivity(String userId, String action) {
        log.info("User Activity - UserID: {}, Action: {}", userId, action);
    }

    /**
     * Log system events
     * @param event the system event
     * @param details additional details
     */
    public void logSystemEvent(String event, String details) {
        log.info("System Event - Event: {}, Details: {}", event, details);
    }
}