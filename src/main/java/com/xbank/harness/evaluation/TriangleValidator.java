package com.xbank.harness.evaluation;

import org.springframework.stereotype.Component;

@Component
public class TriangleValidator {

    public String generateRTM(java.util.List<String> prdReqs, java.util.List<String> jamaRuns, java.util.List<String> codeEndpoints) {
        StringBuilder report = new StringBuilder("Requirements Traceability Matrix (RTM) Report\n");
        boolean fullyCovered = true;

        for (String req : prdReqs) {
            boolean hasJama = jamaRuns.contains(req);
            boolean hasCode = codeEndpoints.contains(req);
            
            report.append(String.format("Req: %s -> Jama: %b -> Code: %b\n", req, hasJama, hasCode));
            
            if (!hasJama || !hasCode) {
                fullyCovered = false;
            }
        }

        if (!fullyCovered) {
            throw new IllegalStateException("COMPLIANCE VIOLATION: RTM does not trace 100% of requirements.");
        }

        report.append("Status: 100% Traceability Confirmed.");
        return report.toString();
    }
}