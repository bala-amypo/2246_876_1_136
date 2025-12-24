package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // Add this method to fix the "cannot find symbol" error
    User findByUsername(String username);

    // Ensure this exists for your UserServiceImpl
    User findByEmail(String email);
}