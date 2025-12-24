package com.example.demo.repository;

import com.example.demo.entity.RiskAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RiskAssessmentRepository extends JpaRepository<RiskAssessment, Long> {
    List<RiskAssessment> findByUserId(long userId);
    // Add this for the RiskLogController fix
    List<RiskAssessment> findByLoanRequestId(Long loanRequestId);
}