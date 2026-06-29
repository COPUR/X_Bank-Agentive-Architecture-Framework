package com.xbank.harness.orchestration.adapter.in.temporal;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface RequirementOrchestratorActivities {
    
    @ActivityMethod
    String translateToTasks(String rawRequirements);
}
