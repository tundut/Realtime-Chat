package com.tundut.realtime_chat.model;

import lombok.Data;

@Data
public class ChatMessage {
    private String sender;
    private String content;
    private String type;
}
