package com.example.VRS.service;

import com.example.VRS.entity.User;
import com.example.VRS.model.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User registerUser(RegisterRequest registerRequest);
    boolean existsByUsername(String username);
}