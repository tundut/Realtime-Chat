package com.tundut.realtime_chat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "conversations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String conversationKey;

    @ManyToMany
    @JoinTable(
            name = "conversation_users", 
            joinColumns = @JoinColumn(name = "conversation_id"), 
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> participants;

    @OneToOne
    @JoinColumn(name = "last_message_id")
    private Message lastMessage;

    private LocalDateTime updatedAt;
}
