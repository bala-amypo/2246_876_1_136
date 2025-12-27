package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.AuthService;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.BadRequestException;

@Service
public class UserServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String fullName, String email, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("CUSTOMER");

        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        if (!user.getPassword().equals(password)) {
            throw new BadRequestException("Invalid credentials");
        }

        return user;
    }
}
