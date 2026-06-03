package com.tundut.realtime_chat.dto;

public record AuthResponse(
        String message,
        String username,
        String token
) {

}