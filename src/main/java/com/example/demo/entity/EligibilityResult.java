package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class EligibilityResult {

    @Id
    private Long id;
    private boolean eligible;
    private LocalDateTime calculatedAt;

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
