package com.example.demo.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
security
├── JwtUtil.java
├── JwtFilter.java
