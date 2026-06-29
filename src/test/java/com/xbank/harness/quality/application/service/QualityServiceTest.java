package com.xbank.harness.quality.application.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QualityServiceTest {

    private final QualityService service = new QualityService();

    @Test
    public void testComputeDoraMetrics() {
        String result = service.computeDoraMetrics("DEPLOY-12345");
        assertEquals("DORA_METRICS_CALCULATED_FOR_DEPLOY-12345", result);
    }

    @Test
    public void testComputeDoraMetricsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> service.computeDoraMetrics(null));
        assertThrows(IllegalArgumentException.class, () -> service.computeDoraMetrics(""));
    }
}
