package com.example.demo.service.impl;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.service.EligibilityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EligibilityServiceImpl implements EligibilityService {

    @Override
    public EligibilityResult checkEligibility(Long userId) {
        EligibilityResult result = new EligibilityResult();
        result.setEligible(true);
        result.setCalculatedAt(LocalDateTime.now());
        return result;
    }
}
