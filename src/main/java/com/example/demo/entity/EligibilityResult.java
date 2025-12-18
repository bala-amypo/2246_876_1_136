package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class EligibilityResult {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "loan_request_id")
    private LoanRequest loanRequest;

    private Double maxEligibleAmount;
    private Double estimatedEmi;
    private String riskLevel;
    
    @CreationTimestamp
    private Timestamp calculatedAt;
}