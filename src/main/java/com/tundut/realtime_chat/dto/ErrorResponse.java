package com.tundut.realtime_chat.dto;

import java.time.LocalDateTime;

public record ErrorResponse(int status,
        String message,
        LocalDateTime timestamp) {

}