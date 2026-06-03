package com.tundut.realtime_chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tundut.realtime_chat.model.Message;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByConversationId(Long conversationId);
}
