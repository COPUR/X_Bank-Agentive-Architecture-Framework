package com.xbank.harness.services;

import io.temporal.workflow.Workflow;
import io.temporal.activity.ActivityOptions;
import java.time.Duration;

public class CognitiveWorkflowImpl implements CognitiveWorkflow {
    
    private boolean isCabApproved = false;
    private boolean cabVoteReceived = false;

    private final AgentActivities activities = Workflow.newActivityStub(
            AgentActivities.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofMinutes(10))
                    .build());

    @Override
    public String executeSdclCycle(String prdContent) {
        // Agent 1: Ingest PRD & Map to BIAN
        String mappedDomain = activities.ingestAndMap(prdContent);
        
        // Agent 2: Query CMDB Topology
        String lldSchema = activities.queryTopology(mappedDomain);
        
        // Agent 3: Cognitive Circuit Breaker (Compliance Gate)
        String complianceResult = activities.runComplianceGate(lldSchema);
        
        if (complianceResult.contains("HARD_STOP")) {
            return "Workflow Terminated: Agent 3 detected compliance violation.";
        }
        
        // Agent 4: CAB Board Notification and Human-in-the-Loop Pause
        activities.notifyCabBoard(complianceResult);
        
        // Pause workflow execution until Signal is received from CAB IdP
        Workflow.await(() -> cabVoteReceived);
        
        if (!isCabApproved) {
            return "Workflow Terminated: CAB Board rejected the architecture.";
        }
        
        // Agent 5: Compute Workload Metrics
        return activities.computeDoraMetrics("DEPLOY-" + Workflow.getInfo().getWorkflowId());
    }

    @Override
    public void approveCab(boolean approved) {
        this.isCabApproved = approved;
        this.cabVoteReceived = true;
    }
}
