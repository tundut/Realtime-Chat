package com.tundut.realtime_chat.controller;

import com.tundut.realtime_chat.dto.UserResponse;
import com.tundut.realtime_chat.model.User;
import com.tundut.realtime_chat.repository.UserRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/me")
    public UserResponse me(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getUsername());
    }
}