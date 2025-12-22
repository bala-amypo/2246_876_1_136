package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    User getUserById(Long userId);

    User getUserByEmail(String email);

    User saveUser(User user);
}
