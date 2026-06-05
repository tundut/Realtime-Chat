package com.tundut.realtime_chat.controller;

import com.tundut.realtime_chat.dto.UserResponse;
import com.tundut.realtime_chat.model.User;
import com.tundut.realtime_chat.repository.UserRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {
    private final UserRepository userRepository;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/me")
    public UserResponse me(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow();

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getUsername());
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/search")
    public List<UserResponse> search(@RequestParam String q, Authentication authentication) {
        String currentUsername = authentication.getName();
        if (q == null || q.trim().isEmpty()) {
            return List.of();
        }

        return userRepository.findByUsernameContainingIgnoreCase(q.trim())
                .stream()
                .filter(user -> !user.getUsername().equals(currentUsername))
                .map(user -> new UserResponse(user.getId(), user.getEmail(), user.getUsername()))
                .collect(Collectors.toList());
    }
}