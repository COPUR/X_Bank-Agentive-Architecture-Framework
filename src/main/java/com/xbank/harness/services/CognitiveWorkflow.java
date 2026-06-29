package com.xbank.harness.services;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import io.temporal.workflow.SignalMethod;

@WorkflowInterface
public interface CognitiveWorkflow {
    @WorkflowMethod
    String executeSdclCycle(String prdContent);
    
    @SignalMethod
    void approveCab(boolean approved);
}
