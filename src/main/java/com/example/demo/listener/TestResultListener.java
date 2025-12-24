package com.example.demo.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestResultListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting Test Case: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Case Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.err.println("Test Case Failed: " + result.getName() + " - Reason: " + result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test execution finished for: " + context.getName());
    }
}