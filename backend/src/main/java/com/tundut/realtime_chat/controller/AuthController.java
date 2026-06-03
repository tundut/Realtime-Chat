package com.tundut.realtime_chat.controller;

import com.tundut.realtime_chat.dto.AuthResponse;
import com.tundut.realtime_chat.dto.LoginRequest;
import com.tundut.realtime_chat.dto.RegisterRequest;
import com.tundut.realtime_chat.service.AuthService;
import com.tundut.realtime_chat.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    @Operation(summary = "Register")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok(new AuthResponse("User registered successfully", null));
    }

    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        authService.login(request);
        String token = jwtService.generateToken(request.username());
        return ResponseEntity.ok(new AuthResponse("Login successful", token));
    }
}
