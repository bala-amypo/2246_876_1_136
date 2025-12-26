// package com.example.demo.repository;

// import com.example.demo.entity.RiskAssessment;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.Optional;

// public interface RiskAssessmentRepository extends JpaRepository<RiskAssessment, Long> {
//     // Note: Test suite usually expects Optional for single results
//     Optional<RiskAssessment> findByLoanRequestId(Long loanRequestId);
// }
package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RiskAssessmentRepository extends JpaRepository<RiskAssessment, Long> {
    Optional<RiskAssessment> findByLoanRequestId(Long loanRequestId);
}