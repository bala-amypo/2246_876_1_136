package com.example.demo.repository;
import com.example.demo.entity.RiskAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RiskAssessmentRepository extends JpaRepository<RiskAssessment, Long> {
    // Fixes the "cannot find symbol findByUserId" error
    List<RiskAssessment> findByUserId(long userId);
}