package com.example.demo.security;

import com.example.demo.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;

    // Normal Spring Constructor
    public JwtFilter(JwtUtil jwtUtil, CustomUserDetailsService uds, UserRepository ur) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = uds;
        this.userRepository = ur;
    }

    // Constructor required by the Automated Test Suite
    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = null;
        this.userRepository = null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // ... (existing logic)
        chain.doFilter(request, response);
    }
}