package com.example.VRS.controller;

import com.example.VRS.service.LoggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller for testing logging functionality
 */
@RestController
@RequestMapping("/api/logger")
@RequiredArgsConstructor
public class LoggerController {

    private final LoggerService loggerService;

    /**
     * Test endpoint for logging info messages
     */
    @PostMapping("/info")
    public ResponseEntity<String> logInfo(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        loggerService.logInfo(message);
        return ResponseEntity.ok("Info message logged successfully");
    }

    /**
     * Test endpoint for logging warning messages
     */
    @PostMapping("/warning")
    public ResponseEntity<String> logWarning(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        loggerService.logWarning(message);
        return ResponseEntity.ok("Warning message logged successfully");
    }

    /**
     * Test endpoint for logging error messages
     */
    @PostMapping("/error")
    public ResponseEntity<String> logError(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        loggerService.logError(message);
        return ResponseEntity.ok("Error message logged successfully");
    }

    /**
     * Test endpoint for logging debug messages
     */
    @PostMapping("/debug")
    public ResponseEntity<String> logDebug(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        loggerService.logDebug(message);
        return ResponseEntity.ok("Debug message logged successfully");
    }

    /**
     * Test endpoint for logging user activity
     */
    @PostMapping("/user-activity")
    public ResponseEntity<String> logUserActivity(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        String action = request.get("action");
        loggerService.logUserActivity(userId, action);
        return ResponseEntity.ok("User activity logged successfully");
    }

    /**
     * Test endpoint for logging system events
     */
    @PostMapping("/system-event")
    public ResponseEntity<String> logSystemEvent(@RequestBody Map<String, String> request) {
        String event = request.get("event");
        String details = request.get("details");
        loggerService.logSystemEvent(event, details);
        return ResponseEntity.ok("System event logged successfully");
    }

    /**
     * Test endpoint that demonstrates operation timing
     */
    @GetMapping("/operation-time-demo")
    public ResponseEntity<String> demonstrateOperationTime() {
        long startTime = System.currentTimeMillis();
        
        try {
            // Simulate some work
            Thread.sleep(1000);
            
            long executionTime = System.currentTimeMillis() - startTime;
            loggerService.logOperationTime("Demo Operation", executionTime);
            
            return ResponseEntity.ok("Operation time demo completed and logged");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            loggerService.logError("Demo operation interrupted", e);
            return ResponseEntity.internalServerError().body("Demo operation failed");
        }
    }
}