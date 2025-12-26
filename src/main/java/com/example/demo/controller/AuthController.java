 package com.example.demo.controller;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.security.JwtUtil;
// import com.example.demo.service.UserService;
// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {
//     private final UserService userService;
//     private final JwtUtil jwtUtil;
//     private final UserRepository userRepository;

//     public AuthController(UserService userService, JwtUtil jwtUtil, UserRepository userRepository) {
//         this.userService = userService;
//         this.jwtUtil = jwtUtil;
//         this.userRepository = userRepository;
//     }

//     @PostMapping("/register")
//     public User register(@RequestBody User user) { return userService.register(user); }

//     @PostMapping("/login")
//     public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
//         User user = userService.findByEmail(authRequest.getEmail());
//         String token = jwtUtil.generateToken(new java.util.HashMap<>(), user.getEmail());
//         return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), user.getRole(), user.getFullName()));
//     }
// }




import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserServiceImpl userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public AuthController(UserServiceImpl userService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new BadRequestException("Invalid credentials"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }
        
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        claims.put("email", user.getEmail());
        claims.put("userId", user.getId());
        
        String token = jwtUtil.generateToken(claims, user.getEmail());
        
        return ResponseEntity.ok(new AuthResponse(token, user.getEmail(), user.getRole()));
    }
}