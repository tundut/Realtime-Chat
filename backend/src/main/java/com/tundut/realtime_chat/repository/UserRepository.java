package com.tundut.realtime_chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tundut.realtime_chat.model.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
