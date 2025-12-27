package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.EligibilityResult;
import java.util.Optional;

public interface EligibilityResultRepository extends JpaRepository<EligibilityResult, Long> {
    Optional<EligibilityResult> findByLoanRequestId(Long id);
}
