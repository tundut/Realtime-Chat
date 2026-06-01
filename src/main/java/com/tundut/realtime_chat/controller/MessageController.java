package com.tundut.realtime_chat.controller;

import org.springframework.web.bind.annotation.*;
import com.tundut.realtime_chat.model.ChatMessage;
import com.tundut.realtime_chat.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String test() {
        return "Message API OK";
    }

    @PostMapping
    public ChatMessage sendMessage(@RequestBody ChatMessage message) {
        return messageService.processMessage(message);
    }
}
