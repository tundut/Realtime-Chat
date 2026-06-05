package com.tundut.realtime_chat.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.tundut.realtime_chat.repository.ConversationRepository;
import com.tundut.realtime_chat.model.Conversation;
import com.tundut.realtime_chat.model.User;
import com.tundut.realtime_chat.dto.ConversationResponse;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationService {
        private final ConversationRepository conversationRepository;

        private String generateKey(User u1, User u2) {
                Long min = Math.min(u1.getId(), u2.getId());
                Long max = Math.max(u1.getId(), u2.getId());

                return min + "_" + max;
        }

        public Conversation save(Conversation conversation) {
                return conversationRepository.save(conversation);
        }

        public Conversation getOrCreate(User u1, User u2) {
                String key = generateKey(u1, u2);
                return conversationRepository
                                .findByConversationKey(key)
                                .orElseGet(() -> {
                                        Conversation conversation = new Conversation();
                                        conversation.setConversationKey(key);
                                        conversation.setParticipants(List.of(u1, u2));
                                        return conversationRepository.save(conversation);
                                });
        }

        public ConversationResponse toConversationResponse(Conversation c, String currentUsername) {
                User other = c.getParticipants()
                                .stream()
                                .filter(p -> !p.getUsername().equals(currentUsername))
                                .findFirst()
                                .orElse(null);

                String lastMessage = c.getLastMessage() != null
                                ? c.getLastMessage().getContent()
                                : null;

                String lastMessageSenderName = c.getLastMessage() != null
                                ? c.getLastMessage().getSender().getUsername()
                                : null;

                LocalDateTime updatedAt = c.getLastMessage() != null
                                ? c.getLastMessage().getCreatedAt()
                                : null;

                return new ConversationResponse(
                                c.getId(),
                                other != null ? other.getUsername() : currentUsername,
                                lastMessage,
                                lastMessageSenderName,
                                updatedAt);
        }

        public List<ConversationResponse> getConversations(String currentUsername) {
                return conversationRepository.findByUsername(currentUsername)
                                .stream()
                                .map(c -> toConversationResponse(c, currentUsername))
                                .toList();
        }

        public ConversationResponse getConversationById(
                        Long conversationId,
                        String currentUsername) {
                return conversationRepository.findById(conversationId)
                                .map(c -> toConversationResponse(c, currentUsername))
                                .orElse(null);
        }
}
