package com.tundut.realtime_chat.service;

import org.springframework.stereotype.Service;
import com.tundut.realtime_chat.model.Message;
import com.tundut.realtime_chat.repository.MessageRepository;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getMessages(Long conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }
}
