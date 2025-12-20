package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Main entry point for the Loan Eligibility & EMI Risk Checker application.
 * 
 * Technical Requirement: @ServletComponentScan is used to enable discovery 
 * of the @WebServlet annotation used in com.example.demo.servlet.SimpleStatusServlet.
 */
@SpringBootApplication
@ServletComponentScan
public class LoanEligibilityEmiRiskCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanEligibilityEmiRiskCheckerApplication.class, args);
    }

}