package com.xbank.harness.compliance.application.service;

import com.xbank.harness.compliance.application.port.in.ComplianceUseCase;
import org.springframework.stereotype.Service;

@Service
public class ComplianceService implements ComplianceUseCase {
    
    @Override
    public String runComplianceGate(String schema) {
        if (schema == null || schema.isEmpty()) {
            throw new IllegalArgumentException("Schema cannot be null or empty.");
        }
        if (schema.contains("PUBLIC_S3_BUCKET")) {
            return "HARD_STOP_CDE_VIOLATION";
        }
        if (schema.contains("PII_RISK")) {
            // Simulated remediation
            return "PASS_COMPLIANCE_WITH_REMEDIATION";
        }
        return "PASS_COMPLIANCE";
    }
}
