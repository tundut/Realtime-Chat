package com.tundut.realtime_chat.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Welcome to the Real-time Chat Application!";
    }
}
