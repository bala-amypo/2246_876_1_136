package com.example.demo.service;

import com.example.demo.entity.User;

public interface AuthService {
    User register(String fullName, String email, String password);
    User login(String email, String password);
}
