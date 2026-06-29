package com.xbank.harness.orchestration.adapter.in.temporal;

import com.xbank.harness.core.config.AgentConfiguration;
import com.xbank.harness.core.harness.AgentHarness;
import org.springframework.stereotype.Component;

@Component
public class AgentHarnessActivitiesImpl implements AgentHarnessActivities {

    private final AgentHarness agentHarness;

    public AgentHarnessActivitiesImpl(AgentHarness agentHarness) {
        this.agentHarness = agentHarness;
    }

    @Override
    public String executeModelDrivenTask(String sessionId, AgentConfiguration config, String taskDescription) {
        return agentHarness.executeTask(sessionId, config, taskDescription);
    }
}
