package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class RiskAssessmentLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long loanRequestId;
    private Double calculatedDti;
    private String creditCheckStatus;
    private String remarks;

    @CreationTimestamp
    private Timestamp logTimestamp;
}