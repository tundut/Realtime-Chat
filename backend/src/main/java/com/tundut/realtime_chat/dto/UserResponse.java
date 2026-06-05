package com.tundut.realtime_chat.dto;

public record UserResponse(
        Long id,
        String email,
        String username
) {
}
