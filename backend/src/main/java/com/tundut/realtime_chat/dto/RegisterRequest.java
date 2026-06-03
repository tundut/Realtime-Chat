package com.tundut.realtime_chat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @Schema(example = "User")
        @NotBlank(message = "Username is required")
        String username,

        @Schema(example = "user@example.com")
        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is required")
        String email,

        @Schema(example = "123456")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password) {

}
