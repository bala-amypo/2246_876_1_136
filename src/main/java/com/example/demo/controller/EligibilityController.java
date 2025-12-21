package com.example.demo.controller;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.entity.LoanRequest;
import com.example.demo.repository.EligibilityResultRepository;
import com.example.demo.repository.LoanRequestRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

    private final EligibilityResultRepository resultRepository;
    private final LoanRequestRepository loanRequestRepository;

    public EligibilityController(EligibilityResultRepository resultRepository,
                                 LoanRequestRepository loanRequestRepository) {
        this.resultRepository = resultRepository;
        this.loanRequestRepository = loanRequestRepository;
    }

    @GetMapping("/{loanRequestId}")
    public EligibilityResult checkEligibility(@PathVariable Long loanRequestId) {

        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new RuntimeException("Loan request not found"));

        EligibilityResult result = new EligibilityResult();
        result.setLoanRequest(loanRequest);
        result.setIsEligible(true);
        result.setMaxEligibleAmount(loanRequest.getRequestedAmount());
        result.setEstimatedEmi(5000.0);
        result.setRiskLevel("LOW");

        return resultRepository.save(result);
    }
}
