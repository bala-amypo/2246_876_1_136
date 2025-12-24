package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Fix for findById returning Optional
    public User getById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User register(User user) {
        return userRepository.save(user);
    }

    // In case your UserRepository.findByEmail also returns Optional
    public User findByEmail(String email) {
        Object result = userRepository.findByEmail(email);
        if (result instanceof Optional) {
            return ((Optional<User>) result).orElse(null);
        }
        return (User) result;
    }

    // If the above method feels too complex, use this standard version:
    /*
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    */
}