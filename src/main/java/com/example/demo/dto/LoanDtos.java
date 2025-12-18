package com.example.demo.dto;

import lombok.Data;

public class LoanDtos {
    @Data
    public static class LoanRequestDto {
        private Double requestedAmount;
        private Integer tenureMonths;
        private String purpose;
    }

    @Data
    public static class FinancialProfileDto {
        private Double monthlyIncome;
        private Double monthlyExpenses;
        private Double existingLoanEmi;
        private Integer creditScore;
        private Double savingsBalance;
    }
}