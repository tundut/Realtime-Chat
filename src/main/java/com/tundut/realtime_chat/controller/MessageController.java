package com.tundut.realtime_chat.controller;

import org.springframework.web.bind.annotation.*;
import com.tundut.realtime_chat.model.Message;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @PostMapping
    public Message sendMessage(@RequestBody Message message) {
        return message;
    }
}
