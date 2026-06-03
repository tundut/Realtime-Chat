package com.tundut.realtime_chat.controller;

import com.tundut.realtime_chat.service.MessageService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.tundut.realtime_chat.service.ConversationService;
import com.tundut.realtime_chat.model.Message;
import com.tundut.realtime_chat.dto.ConversationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Conversation")
public class ConversationController {
    private final MessageService messageService;
    private final ConversationService conversationService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/conversations")
    public List<ConversationResponse> getConversations(Principal principal) {
        return conversationService.getConversations(principal.getName());
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/messages/{conversationId}")
    public List<Message> getMessages(Long conversationId) {
        return messageService.getMessages(conversationId);
    }
}
