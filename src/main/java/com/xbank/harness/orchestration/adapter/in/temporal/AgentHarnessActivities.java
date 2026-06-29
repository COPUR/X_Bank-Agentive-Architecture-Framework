package com.xbank.harness.orchestration.adapter.in.temporal;

import com.xbank.harness.core.config.AgentConfiguration;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface AgentHarnessActivities {
    
    @ActivityMethod
    String executeModelDrivenTask(String sessionId, AgentConfiguration config, String taskDescription);
}
