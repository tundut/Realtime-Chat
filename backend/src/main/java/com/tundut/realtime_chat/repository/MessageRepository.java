package com.tundut.realtime_chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tundut.realtime_chat.model.Message;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("""
            SELECT m
            FROM Message m
            WHERE m.conversation.id = :conversationId
            ORDER BY m.createdAt ASC
            """)
    List<Message> findMessages(@Param("conversationId") Long conversationId);
}
