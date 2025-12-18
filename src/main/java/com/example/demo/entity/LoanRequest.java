package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class LoanRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double requestedAmount;
    private Integer tenureMonths;
    private String purpose;
    private String status; 

    @CreationTimestamp
    private Timestamp appliedAt;
}