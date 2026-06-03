package com.tundut.realtime_chat.dto;

public record ChatMessage(
        String sender,
        String receiver,
        String content) {
}