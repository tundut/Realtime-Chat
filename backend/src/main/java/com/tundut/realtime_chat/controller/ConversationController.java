package com.tundut.realtime_chat.controller;

import com.tundut.realtime_chat.service.MessageService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.tundut.realtime_chat.service.ConversationService;
import com.tundut.realtime_chat.dto.ConversationResponse;
import com.tundut.realtime_chat.dto.MessageResponse;
import com.tundut.realtime_chat.model.User;
import com.tundut.realtime_chat.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final UserRepository userRepository;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/conversations")
    public List<ConversationResponse> getConversations(Principal principal) {
        return conversationService.getConversations(principal.getName());
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/conversation/{conversationId}")
    public ConversationResponse getConversationById(@PathVariable Long conversationId, Principal principal) {
        return conversationService.getConversationById(conversationId, principal.getName());
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/conversation")
    public ConversationResponse getOrCreateConversation(@RequestParam String username, Principal principal) {
        User sender = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        User receiver = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        var conversation = conversationService.getOrCreate(sender, receiver);
        return conversationService.toConversationResponse(conversation, sender.getUsername());
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/messages/{conversationId}")
    public List<MessageResponse> getMessages(@PathVariable Long conversationId) {
        return messageService.getMessages(conversationId);
    }
}
