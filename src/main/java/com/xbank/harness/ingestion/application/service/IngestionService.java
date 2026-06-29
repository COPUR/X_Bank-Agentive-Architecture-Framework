package com.xbank.harness.ingestion.application.service;

import com.xbank.harness.ingestion.application.port.in.IngestionUseCase;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class IngestionService implements IngestionUseCase {
    private static final List<String> STANDARD_DOMAINS = Arrays.asList("Payment Execution", "Party Routing", "Customer Offer", "Current Account");

    @Override
    public String ingestAndMap(String prdContent) {
        if (prdContent == null || prdContent.trim().isEmpty()) {
            throw new IllegalArgumentException("COMPLIANCE VIOLATION: PRD content cannot be empty.");
        }
        for (String domain : STANDARD_DOMAINS) {
            if (prdContent.contains(domain)) {
                return "MAPPED_BIAN_DOMAIN: " + domain;
            }
        }
        throw new IllegalArgumentException("COMPLIANCE VIOLATION: PRD does not map to any standard BIAN Service Domain 100% accurately.");
    }
}
