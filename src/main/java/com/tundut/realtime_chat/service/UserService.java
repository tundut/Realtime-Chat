package com.tundut.realtime_chat.service;

import com.tundut.realtime_chat.entity.User;
import com.tundut.realtime_chat.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
