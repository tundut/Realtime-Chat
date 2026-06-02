package com.tundut.realtime_chat.service;

import com.tundut.realtime_chat.dto.LoginRequest;
import com.tundut.realtime_chat.dto.RegisterRequest;
import com.tundut.realtime_chat.repository.UserRepository;
import com.tundut.realtime_chat.exception.AuthenticationException;
import com.tundut.realtime_chat.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new AuthenticationException("Email already exists");
        }
        if (userRepository.existsByUsername(request.username())) {
            throw new AuthenticationException("Username already exists");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(user);
    }

    public void login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new AuthenticationException("Invalid email or password"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new AuthenticationException("Invalid email or password");
        }
    }
}