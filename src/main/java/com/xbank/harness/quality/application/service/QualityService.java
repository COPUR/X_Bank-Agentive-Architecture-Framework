package com.xbank.harness.quality.application.service;

import com.xbank.harness.quality.application.port.in.QualityUseCase;
import org.springframework.stereotype.Service;

@Service
public class QualityService implements QualityUseCase {
    @Override
    public String computeDoraMetrics(String deploymentId) {
        if (deploymentId == null || deploymentId.isEmpty()) {
            throw new IllegalArgumentException("Deployment ID must be provided.");
        }
        return "DORA_METRICS_CALCULATED_FOR_" + deploymentId;
    }
}
