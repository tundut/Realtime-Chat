package com.tundut.realtime_chat.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequest(
        @Schema(example = "User")
        String username,

        @Schema(example = "123456")
        String password) {
}
