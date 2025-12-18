


File: src/main/java/com/example/demo/controller/EligibilityController.java
code
Java
package com.example.demo.controller;

import com.example.demo.entity.EligibilityResult;
import com.example.demo.service.LoanEligibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityController {

    @Autowired private LoanEligibilityService service;

    @PostMapping("/evaluate/{loanRequestId}")
    public EligibilityResult evaluate(@PathVariable Long loanRequestId) {
        return service.evaluateEligibility(loanRequestId);
    }

    @GetMapping("/result/{loanRequestId}")
    public EligibilityResult getResult(@PathVariable Long loanRequestId) {
        return service.getResultByRequest(loanRequestId);
    }
}
File: src/main/java/com/example/demo/controller/RiskLogController.java
code
Java
package com.example.demo.controller;

import com.example.demo.entity.RiskAssessmentLog;
import com.example.demo.service.RiskAssessmentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/risk-logs")
public class RiskLogController {

    @Autowired private RiskAssessmentLogService service;

    @GetMapping("/{loanRequestId}")
    public List<RiskAssessmentLog> getLogs(@PathVariable Long loanRequestId) {
        return service.getLogsByRequest(loanRequestId);
    }
}