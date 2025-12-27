package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.FinancialProfile;
import java.util.Optional;

public interface FinancialProfileRepository extends JpaRepository<FinancialProfile, Long> {
    Optional<FinancialProfile> findByUserId(Long id);
}
