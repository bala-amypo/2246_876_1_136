package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}

public interface FinancialProfileRepository extends JpaRepository<FinancialProfile, Long> {
    Optional<FinancialProfile> findByUserId(Long userId);
}

public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {
    List<LoanRequest> findByUserId(Long userId);
}

public interface EligibilityResultRepository extends JpaRepository<EligibilityResult, Long> {
    Optional<EligibilityResult> findByLoanRequestId(Long loanRequestId);
}

public interface RiskAssessmentLogRepository extends JpaRepository<RiskAssessmentLog, Long> {
    List<RiskAssessmentLog> findByLoanRequestId(Long loanRequestId);
}