package com.xbank.harness.orchestration.adapter.in.temporal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xbank.harness.orchestration.application.port.in.TaskDTO;
import io.temporal.workflow.Workflow;
import io.temporal.activity.ActivityOptions;

import java.time.Duration;
import java.util.List;

public class CognitiveWorkflowImpl implements CognitiveWorkflow {
    
    private boolean isCabApproved = false;
    private boolean cabVoteReceived = false;

    private final OrchestrationActivities activities = Workflow.newActivityStub(
            OrchestrationActivities.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofMinutes(10))
                    .build());

    @Override
    public String executeSdclCycle(String jsonTasksPayload) {
        ObjectMapper mapper = new ObjectMapper();
        List<TaskDTO> tasks;
        try {
            tasks = mapper.readValue(jsonTasksPayload, new TypeReference<List<TaskDTO>>() {});
        } catch (Exception e) {
            return "Workflow Terminated: Invalid JSON payload provided for tasks.";
        }

        StringBuilder finalReport = new StringBuilder("Workflow Execution Report:\n");

        for (TaskDTO task : tasks) {
            finalReport.append("Executing Task: ").append(task.getTaskId()).append("\n");
            
            // Isolated State Management per task
            String mappedDomain = activities.ingestAndMap(task.getDescription());
            String lldSchema = activities.queryTopology(mappedDomain);
            String complianceResult = activities.runComplianceGate(lldSchema);
            
            if (complianceResult != null && complianceResult.contains("HARD_STOP")) {
                task.setStatus("FAILED_COMPLIANCE");
                finalReport.append("  -> Failed at Agent 3 (Compliance): ").append(complianceResult).append("\n");
                continue; // Skip failed task, process next
            }
            
            // Reset CAB state for the new task
            isCabApproved = false;
            cabVoteReceived = false;

            activities.notifyCabBoard("Task " + task.getTaskId() + ": " + complianceResult);
            
            Workflow.await(() -> cabVoteReceived);
            
            if (!isCabApproved) {
                task.setStatus("FAILED_GOVERNANCE");
                finalReport.append("  -> Failed at Agent 4 (Governance): CAB Rejected\n");
                continue;
            }
            
            task.setStatus("COMPLETED");
            String doraMetrics = activities.computeDoraMetrics("DEPLOY-" + task.getTaskId());
            finalReport.append("  -> Success. DORA: ").append(doraMetrics).append("\n");
        }

        return finalReport.toString();
    }

    @Override
    public void approveCab(boolean approved) {
        this.isCabApproved = approved;
        this.cabVoteReceived = true;
    }
}
