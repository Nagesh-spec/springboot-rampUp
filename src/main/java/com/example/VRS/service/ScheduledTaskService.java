package com.example.VRS.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Scheduled Task Service with ShedLock integration
 * Demonstrates logging using SLF4J and distributed locking using ShedLock
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledTaskService {

    private final LoggerService loggerService;
    private final VehicleService vehicleService;

    /**
     * Sample scheduled task that runs every 5 minutes
     * Uses ShedLock to ensure only one instance executes this task
     */
    @Scheduled(fixedRate = 300000) // 5 minutes
    @SchedulerLock(
        name = "sampleScheduledTask", 
        lockAtMostFor = "4m", 
        lockAtLeastFor = "1m"
    )
    public void sampleScheduledTask() {
        long startTime = System.currentTimeMillis();
        String taskName = "Sample Scheduled Task";
        
        try {
            log.info("Starting scheduled task: {}", taskName);
            loggerService.logSystemEvent("SCHEDULED_TASK_STARTED", taskName);
            
            // Simulate some work
            performSampleWork();
            
            long executionTime = System.currentTimeMillis() - startTime;
            loggerService.logOperationTime(taskName, executionTime);
            loggerService.logSystemEvent("SCHEDULED_TASK_COMPLETED", taskName);
            
        } catch (Exception e) {
            loggerService.logError("Error in scheduled task: " + taskName, e);
        }
    }

    /**
     * Vehicle status monitoring task that runs every hour
     * Logs vehicle statistics and monitors system health
     */
    @Scheduled(fixedDelay = 3000)
    @SchedulerLock(
        name = "vehicleStatusMonitoring",
        lockAtMostFor = "10s",
        lockAtLeastFor = "1s"
    )
    public void vehicleStatusMonitoring() {
        
        try {
            System.out.println("***************EVERY 3 SEC*****************");
            
            // Get vehicle statistics
            long totalVehicles = vehicleService.getTotalVehicleCount();
            long availableVehicles = vehicleService.getAvailableVehicleCount();
            long rentedVehicles = vehicleService.getRentedVehicleCount();
            
            // Display only vehicle data
            System.out.println("Vehicle Statistics:");
            System.out.println("  Total: " + totalVehicles);
            System.out.println("  Available: " + availableVehicles);
            System.out.println("  Rented: " + rentedVehicles);
            
            double availabilityRate = totalVehicles > 0 ? 
                (double) availableVehicles / totalVehicles * 100 : 0;
            
            System.out.println("  Availability Rate: " + String.format("%.2f%%", availabilityRate));
            
            if (availabilityRate < 20.0) {
                System.out.println("  WARNING: Low vehicle availability!");
            }
            
            System.out.println("***************END*****************");
            
        } catch (Exception e) {
            System.out.println("Error in vehicle monitoring: " + e.getMessage());
        }
    }

    /**
     * Daily cleanup task that runs at 2 AM
     * Demonstrates logging and distributed locking for maintenance operations
     */
    @Scheduled(cron = "0 0 2 * * *") // 2 AM daily
    @SchedulerLock(
        name = "dailyCleanupTask",
        lockAtMostFor = "2h",
        lockAtLeastFor = "30m"
    )
    public void dailyCleanupTask() {
        long startTime = System.currentTimeMillis();
        String taskName = "Daily Cleanup Task";
        
        try {
            log.info("Starting daily cleanup task");
            loggerService.logSystemEvent("CLEANUP_TASK_STARTED", taskName);
            
            // Simulate cleanup operations
            performCleanupOperations();
            
            long executionTime = System.currentTimeMillis() - startTime;
            loggerService.logOperationTime(taskName, executionTime);
            loggerService.logSystemEvent("CLEANUP_TASK_COMPLETED", taskName);
            
        } catch (Exception e) {
            loggerService.logError("Error in daily cleanup task", e);
        }
    }

    /**
     * Simulates some sample work for demonstration
     */
    private void performSampleWork() {
        try {
            log.debug("Performing sample work...");
            
            // Simulate database operations
            Thread.sleep(2000);
            loggerService.logDebug("Database operations completed");
            
            // Simulate API calls
            Thread.sleep(1000);
            loggerService.logDebug("External API calls completed");
            
            // Log current timestamp
            String currentTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            loggerService.logInfo("Sample work completed at: " + currentTime);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            loggerService.logError("Sample work interrupted", e);
        }
    }

    /**
     * Simulates cleanup operations
     */
    private void performCleanupOperations() {
        try {
            log.debug("Starting cleanup operations...");
            
            // Simulate cache cleanup
            Thread.sleep(1000);
            loggerService.logDebug("Cache cleanup completed");
            
            // Simulate log file rotation
            Thread.sleep(500);
            loggerService.logDebug("Log file rotation completed");
            
            // Simulate temporary file cleanup
            Thread.sleep(500);
            loggerService.logDebug("Temporary file cleanup completed");
            
            loggerService.logInfo("All cleanup operations completed successfully");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            loggerService.logError("Cleanup operations interrupted", e);
        }
    }
}