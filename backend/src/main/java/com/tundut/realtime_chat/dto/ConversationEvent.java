package com.tundut.realtime_chat.dto;

import java.time.LocalDateTime;

public record ConversationEvent(
        Long conversationId,
        String lastMessage,
        String lastMessageSenderName,
        LocalDateTime updateAt) {
}
