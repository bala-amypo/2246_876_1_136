package com.example.demo.service.impl;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) { this.userRepository = userRepository; }

    public User getById(long id) {
        // Fixes incompatible types Optional -> User error
        return userRepository.findById(id).orElse(null);
    }
    
    public User register(User user) { return userRepository.save(user); }
    public User findByEmail(String email) { return userRepository.findByEmail(email); }
}