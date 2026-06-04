package com.tundut.realtime_chat.service;

import org.springframework.stereotype.Service;
import com.tundut.realtime_chat.model.Message;
import com.tundut.realtime_chat.repository.MessageRepository;
import com.tundut.realtime_chat.dto.MessageResponse;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    private MessageResponse toResponse(Message message) {
        return new MessageResponse(
                message.getId(),
                message.getContent(),
                message.getSender().getId(),
                message.getSender().getUsername(),
                message.getCreatedAt());
    }

    public List<MessageResponse> getMessages(Long conversationId) {
        return messageRepository.findMessages(conversationId)
                .stream()
                .map(this::toResponse)
                .toList();
    }
}
