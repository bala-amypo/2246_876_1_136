package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UserService userService;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody AuthRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFullName(request.getFullName());
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        User user = userService.login(request.getEmail(), request.getPassword());
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token);
    }
}
File: src/main/java/com/example/demo/controller/FinancialProfileController.java
code
Java
package com.example.demo.controller;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.User;
import com.example.demo.service.FinancialProfileService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financial-profiles")
public class FinancialProfileController {

    @Autowired private FinancialProfileService service;
    @Autowired private UserService userService;

    @PostMapping("/")
    public FinancialProfile createProfile(@RequestBody LoanDtos.FinancialProfileDto dto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);

        FinancialProfile profile = new FinancialProfile();
        profile.setUser(user);
        profile.setMonthlyIncome(dto.getMonthlyIncome());
        profile.setMonthlyExpenses(dto.getMonthlyExpenses());
        profile.setExistingLoanEmi(dto.getExistingLoanEmi());
        profile.setCreditScore(dto.getCreditScore());
        profile.setSavingsBalance(dto.getSavingsBalance());

        return service.createOrUpdateProfile(profile);
    }

    @GetMapping("/user/{userId}")
    public FinancialProfile getProfile(@PathVariable Long userId) {
        return service.getProfileByUserId(userId);
    }
}