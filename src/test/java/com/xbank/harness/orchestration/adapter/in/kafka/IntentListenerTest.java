package com.xbank.harness.orchestration.adapter.in.kafka;

import com.xbank.harness.orchestration.adapter.in.temporal.CognitiveWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IntentListenerTest {

    @Mock
    private WorkflowClient workflowClient;

    @Mock
    private CognitiveWorkflow cognitiveWorkflow;

    @InjectMocks
    private IntentListener intentListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListen() {
        when(workflowClient.newWorkflowStub(eq(CognitiveWorkflow.class), any(WorkflowOptions.class)))
                .thenReturn(cognitiveWorkflow);

        String jsonPayload = "[{\"taskId\":\"T-1\",\"description\":\"Build API\",\"relatedPrd\":\"PRD-1\"}]";
        intentListener.listen(jsonPayload);

        verify(workflowClient).newWorkflowStub(eq(CognitiveWorkflow.class), any(WorkflowOptions.class));
        // We cannot easily verify WorkflowClient.start() in a simple Mockito test since it's a static method,
        // but we can verify the stub creation which ensures the logic reached that point.
    }
}
