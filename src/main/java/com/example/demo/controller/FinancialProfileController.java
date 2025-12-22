package com.example.demo.controller;

import com.example.demo.dto.LoanDtos.FinancialProfileDto;
import com.example.demo.entity.FinancialProfile;
import com.example.demo.entity.User;
import com.example.demo.repository.FinancialProfileRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/financial-profile")
public class FinancialProfileController {

    private final FinancialProfileRepository profileRepository;
    private final UserRepository userRepository;

    public FinancialProfileController(FinancialProfileRepository profileRepository,
                                      UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/{userId}")
    public FinancialProfile saveProfile(@PathVariable Long userId,
                                        @RequestBody FinancialProfileDto dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FinancialProfile profile = new FinancialProfile();
        profile.setUser(user);
        profile.setMonthlyIncome(dto.monthlyIncome);
        profile.setMonthlyExpenses(dto.monthlyExpenses);
        profile.setExistingLoanEmi(dto.existingLoanEmi);
        profile.setCreditScore(dto.creditScore);
        profile.setSavingsBalance(dto.savingsBalance);

        return profileRepository.save(profile);
    }
}
