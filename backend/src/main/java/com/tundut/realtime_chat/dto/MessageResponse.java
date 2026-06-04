package com.tundut.realtime_chat.dto;

import java.time.LocalDateTime;

public record MessageResponse(
                Long id,
                Long conversationId,
                String senderName,
                String content,
                LocalDateTime createdAt) {
}