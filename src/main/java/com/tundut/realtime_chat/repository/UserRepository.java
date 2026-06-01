package com.tundut.realtime_chat.repository;

import com.tundut.realtime_chat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
