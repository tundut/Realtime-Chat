package com.tundut.realtime_chat.model;

import lombok.Data;

@Data
public class Message {
    private String sender;
    private String content;
}
