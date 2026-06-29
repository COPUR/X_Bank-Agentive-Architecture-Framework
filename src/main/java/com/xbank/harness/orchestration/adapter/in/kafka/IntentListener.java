package com.xbank.harness.orchestration.adapter.in.kafka;

import com.xbank.harness.orchestration.adapter.in.temporal.CognitiveWorkflow;
import com.xbank.harness.orchestration.config.TemporalConfig;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IntentListener {

    private final WorkflowClient workflowClient;

    public IntentListener(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }

    @KafkaListener(topics = "temporal-intent-events", groupId = "harness-group")
    public void listen(String intentPayload) {
        System.out.println("Received Orchestration Intent: " + intentPayload);
        
        // When an intent is received, we trigger a new Temporal workflow execution
        String workflowId = "CognitiveIntent-" + UUID.randomUUID().toString();
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(TemporalConfig.TASK_QUEUE)
                .setWorkflowId(workflowId)
                .build();
                
        CognitiveWorkflow workflow = workflowClient.newWorkflowStub(CognitiveWorkflow.class, options);
        
        // Start asynchronously
        WorkflowClient.start(workflow::executeSdclCycle, intentPayload);
        
        System.out.println("Started Temporal Workflow ID: " + workflowId);
    }
}
