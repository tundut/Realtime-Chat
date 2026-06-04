package com.tundut.realtime_chat.controller;

import com.tundut.realtime_chat.dto.ChatMessage;
import com.tundut.realtime_chat.service.ConversationService;
import com.tundut.realtime_chat.service.MessageService;

import lombok.RequiredArgsConstructor;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import java.security.Principal;
import java.time.LocalDateTime;

import com.tundut.realtime_chat.model.Message;
import com.tundut.realtime_chat.repository.UserRepository;
import com.tundut.realtime_chat.model.User;
import com.tundut.realtime_chat.model.Conversation;

@Controller
@RequiredArgsConstructor
public class ChatController {
        private final SimpMessagingTemplate messagingTemplate;
        private final MessageService messageService;
        private final ConversationService conversationService;
        private final UserRepository userRepository;

        @MessageMapping("/chat.private")
        public void sendPrivateMessage(ChatMessage chatMessage, Principal principal) {
                User sender = userRepository.findByUsername(principal.getName())
                                .orElseThrow(() -> new RuntimeException("User not found"));
                User receiver = userRepository.findByUsername(chatMessage.receiver())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                Conversation conversation = conversationService.getOrCreate(sender, receiver);

                Message savedMessage = messageService.save(
                                Message.builder()
                                                .conversation(conversation)
                                                .sender(sender)
                                                .content(chatMessage.content())
                                                .createdAt(LocalDateTime.now())
                                                .build());

                conversation.setLastMessage(savedMessage);
                conversation.setUpdatedAt(LocalDateTime.now());
                conversationService.save(conversation);

                messagingTemplate.convertAndSend(
                                "/topic/conversation/" + conversation.getId(),
                                savedMessage);
        }
}
