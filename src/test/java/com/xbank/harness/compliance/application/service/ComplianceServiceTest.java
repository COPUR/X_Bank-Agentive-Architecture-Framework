package com.xbank.harness.compliance.application.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComplianceServiceTest {

    private final ComplianceService service = new ComplianceService();

    @Test
    public void testRunComplianceGateHardStop() {
        String result = service.runComplianceGate("{ \"components\": [\"PUBLIC_S3_BUCKET\"] }");
        assertEquals("HARD_STOP_CDE_VIOLATION", result);
    }

    @Test
    public void testRunComplianceGateRemediation() {
        String result = service.runComplianceGate("{ \"components\": [\"PII_RISK\"] }");
        assertEquals("PASS_COMPLIANCE_WITH_REMEDIATION", result);
    }

    @Test
    public void testRunComplianceGatePass() {
        String result = service.runComplianceGate("{ \"components\": [\"SECURE_DB\"] }");
        assertEquals("PASS_COMPLIANCE", result);
    }

    @Test
    public void testRunComplianceGateEmpty() {
        assertThrows(IllegalArgumentException.class, () -> service.runComplianceGate(null));
        assertThrows(IllegalArgumentException.class, () -> service.runComplianceGate(""));
    }
}
