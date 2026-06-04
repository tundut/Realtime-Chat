package com.tundut.realtime_chat.dto;

import java.time.LocalDateTime;

public record MessageResponse(
        Long id,
        String content,
        Long senderId,
        String senderName,
        LocalDateTime createdAt) {
}