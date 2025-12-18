package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {
    List<LoanRequest> findByUserId(Long userId);
}

