package com.tundut.realtime_chat.repository;

import com.tundut.realtime_chat.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Optional<Conversation> findByConversationKey(String conversationKey);

    @Query("""
            SELECT c
            FROM Conversation c
            JOIN c.participants p
            WHERE p.username = :username
            ORDER BY c.updatedAt DESC
            """)
    List<Conversation> findByUsername(@Param("username") String username);
}