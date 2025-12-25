package com.example.demo.repository;

import com.example.demo.entity.FinancialProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; // Import this

public interface FinancialProfileRepository extends JpaRepository<FinancialProfile, Long> {
    // Change from FinancialProfile to Optional<FinancialProfile>
    Optional<FinancialProfile> findByUserId(Long userId);
}