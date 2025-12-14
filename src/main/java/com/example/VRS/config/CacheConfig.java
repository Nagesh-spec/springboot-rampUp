package com.example.VRS.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Cache configuration for the VRS application using Caffeine.
 * This configuration sets up caching for customers, vehicles, and rentals.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * Caffeine cache manager configuration
     * @return CacheManager with Caffeine implementation
     */
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeineCacheBuilder());
        
        // Set cache names that will be used in the application
        List<String> cacheNames = new ArrayList<>(List.of("customers","vehicles","rentals"));
        cacheManager.setCacheNames(cacheNames);
        
        return cacheManager;
    }

    /**
     * Caffeine cache builder with custom configuration
     * @return Caffeine builder with TTL and size limits
     */
    @Bean
    public Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(100)           // Initial cache size
                .maximumSize(1000)              // Maximum cache entries
                .expireAfterWrite(10, TimeUnit.MINUTES)  // Cache entries expire 10 minutes after write
                .expireAfterAccess(5, TimeUnit.MINUTES)  // Cache entries expire 5 minutes after last access
                .recordStats();                 // Enable cache statistics
    }
}