package com.xbank.harness.orchestration.adapter.in.temporal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xbank.harness.orchestration.application.port.in.TaskDTO;
import io.temporal.workflow.Workflow;
import io.temporal.activity.ActivityOptions;

import java.time.Duration;
import java.util.List;

public class OpenFinanceWorkflowImpl implements OpenFinanceWorkflow {

    private boolean isCabApproved = false;
    private boolean cabVoteReceived = false;

    private final RequirementOrchestratorActivities reqActivities = Workflow.newActivityStub(
            RequirementOrchestratorActivities.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofMinutes(5))
                    .build());

    private final AgentHarnessActivities harnessActivities = Workflow.newActivityStub(
            AgentHarnessActivities.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofMinutes(30))
                    .build());

    @Override
    public String executeOpenFinanceCycle(String rawRequirements) {
        // 1. Translate Raw Requirements to Structured Tasks
        var jsonTasksPayload = reqActivities.translateToTasks(rawRequirements);
        
        var mapper = new ObjectMapper();
        List<TaskDTO> tasks;
        try {
            tasks = mapper.readValue(jsonTasksPayload, new TypeReference<List<TaskDTO>>() {});
        } catch (Exception e) {
            return "Workflow Terminated: Invalid JSON payload generated for tasks.";
        }

        var finalReport = new StringBuilder("OpenFinance Workflow Execution Report:\n");
        
        // 2. Iterative Looping with Isolated State (Ralph Architecture + Model-Driven Harness)
        for (var task : tasks) {
            finalReport.append("Executing Task: ").append(task.getTaskId()).append("\n");
            
            // Generate a session ID for memory scoping
            var sessionId = Workflow.getInfo().getWorkflowId() + "-" + task.getTaskId();
            
            // Hot-Swappable Logic: Config defined at API level (mocked here for demonstration)
            var config = new com.xbank.harness.core.config.AgentConfiguration();
            config.setAgentId("open-finance-agent-1");
            config.setSystemPrompt("You are an autonomous agent responsible for orchestrating Open Finance tasks.");
            config.setEnabledTools(java.util.Arrays.asList("query_alfabet_cmdb", "create_jira_ticket"));
            config.setPermissions(java.util.Arrays.asList("READ_CMDB", "WRITE_JIRA"));
            
            // Reset state for each isolated task iteration
            isCabApproved = false;
            cabVoteReceived = false;
            
            // Dynamic Sequencing via the Agent Harness with Context
            var taskResult = harnessActivities.executeModelDrivenTask(sessionId, config, task.getDescription());
            
            finalReport.append("  -> Harness Result: ").append(taskResult).append("\n");
            
            // Wait for human-in-the-loop if the harness requested it (mocked logic for CAB approval)
            if (taskResult.contains("JIRA_TICKET_CREATED")) {
                Workflow.await(() -> cabVoteReceived);
                if (!isCabApproved) {
                    finalReport.append("  -> Task ").append(task.getTaskId()).append(" Rejected by CAB.\n");
                    continue;
                }
            }
            
            task.setStatus("COMPLETED");
        }

        return finalReport.toString();
    }

    @Override
    public void approveCab(boolean approved) {
        this.isCabApproved = approved;
        this.cabVoteReceived = true;
    }
}
