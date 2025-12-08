package com.example.VRS.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {
    private int statusCode;
    private String message;
    private String error;
    private LocalDateTime timestamp;
    private String path;

    public ErrorResponse() {
    }

    public ErrorResponse(int statusCode, String message, String error, LocalDateTime timestamp, String path) {
        this.statusCode = statusCode;
        this.message = message;
        this.error = error;
        this.timestamp = timestamp;
        this.path = path;
    }

}
