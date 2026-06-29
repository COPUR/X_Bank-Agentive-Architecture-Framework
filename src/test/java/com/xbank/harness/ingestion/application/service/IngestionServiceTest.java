package com.xbank.harness.ingestion.application.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IngestionServiceTest {

    private final IngestionService service = new IngestionService();

    @Test
    public void testSuccessfulMapping() {
        String prdContent = "This requirement is for Payment Execution module.";
        String result = service.ingestAndMap(prdContent);
        assertEquals("MAPPED_BIAN_DOMAIN: Payment Execution", result);
    }

    @Test
    public void testFailedMappingDueToInvalidDomain() {
        String prdContent = "This requirement is for Unknown Domain module.";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.ingestAndMap(prdContent);
        });
        assertTrue(exception.getMessage().contains("PRD does not map to any standard BIAN Service Domain"));
    }

    @Test
    public void testFailedMappingDueToNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.ingestAndMap(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            service.ingestAndMap("   ");
        });
    }
}
