package com.tundut.realtime_chat.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record RegisterRequest(
        @Schema(example = "User")
        String username,

        @Schema(example = "user@example.com")
        String email,

        @Schema(example = "123456")
        String password) {

}
