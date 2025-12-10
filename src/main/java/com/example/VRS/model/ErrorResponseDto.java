package com.example.VRS.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private int statusCode;
    private String message;
    private String error;
    private LocalDateTime timestamp;
    private String path;
}
