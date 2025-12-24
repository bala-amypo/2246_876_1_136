package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class LoanRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String status; // Field expected by test line 297
    private LocalDateTime submittedAt; // Method getSubmittedAt expected
}

   