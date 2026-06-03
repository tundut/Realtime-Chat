package com.tundut.realtime_chat.dto;

import java.time.LocalDateTime;

public record ConversationResponse(
        Long conversationId,
        String username,
        String lastMessage,
        LocalDateTime updateAt) {
}
