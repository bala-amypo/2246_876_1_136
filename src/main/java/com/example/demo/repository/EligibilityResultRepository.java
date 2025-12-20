package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.EligibilityResult;

public interface EligibilityResultRepository extends JpaRepository<EligibilityResult, Long> {

    Optional<EligibilityResult> findByLoanRequestId(Long loanRequestId);
}
