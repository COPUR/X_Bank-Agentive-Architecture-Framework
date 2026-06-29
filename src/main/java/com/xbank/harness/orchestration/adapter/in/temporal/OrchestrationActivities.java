package com.xbank.harness.orchestration.adapter.in.temporal;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface OrchestrationActivities {
    @ActivityMethod
    String ingestAndMap(String prdContent);
    
    @ActivityMethod
    String queryTopology(String mappedDomain);
    
    @ActivityMethod
    String runComplianceGate(String lldSchema);
    
    @ActivityMethod
    void notifyCabBoard(String complianceResult);
    
    @ActivityMethod
    String computeDoraMetrics(String deploymentId);
}
