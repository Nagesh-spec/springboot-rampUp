package com.example.VRS.controller;

import com.example.VRS.config.JwtUtils;
import com.example.VRS.entity.User;
import com.example.VRS.model.AuthResponse;
import com.example.VRS.model.LoginRequest;
import com.example.VRS.model.RegisterRequest;
import com.example.VRS.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Create a new user account with username and password", security = {})
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = userService.registerUser(registerRequest);
        
        // Generate JWT token for the newly registered user
        String jwt = jwtUtils.generateTokenFromUsername(user.getUsername());
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(jwt, user.getUsername()));
    }
    
    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Login with username and password to get JWT token", security = {})
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword())
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userPrincipal);
        
        return ResponseEntity.ok(new AuthResponse(jwt, userPrincipal.getUsername()));
    }
}