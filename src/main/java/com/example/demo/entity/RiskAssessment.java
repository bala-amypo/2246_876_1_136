package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RiskAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Integer riskScore; // Method getRiskScore expected
}