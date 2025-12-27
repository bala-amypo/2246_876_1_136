package com.example.demo.dto;

import com.example.demo.entity.*;

public class LoanDtos {

    public static class UserDto {
        public Long id;
        public String fullName;
        public String email;
        public String role;
    }

    public static class FinancialProfileDto {
        public Double monthlyIncome;
        public Double monthlyExpenses;
        public Double existingLoanEmi;
        public Integer creditScore;
        public Double savingsBalance;
    }

    public static class LoanRequestDto {
        public Double requestedAmount;
        public Integer tenureMonths;
    }

    public static class EligibilityDto {
        public Double maxEligibleAmount;
    }

    public static class RiskDto {
        public Double riskScore;
        public Double dtiRatio;
    }
}
