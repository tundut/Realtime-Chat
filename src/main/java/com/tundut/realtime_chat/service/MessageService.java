package com.tundut.realtime_chat.service;

import org.springframework.stereotype.Service;
import com.tundut.realtime_chat.model.ChatMessage;

@Service
public class MessageService {
    public ChatMessage processMessage(ChatMessage message) {
        message.setContent(
            "[SERVER] " + message.getContent()
        );
        
        return message;
    }
}
