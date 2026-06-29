package com.xbank.harness.orchestration.adapter.in.temporal;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OpenFinanceWorkflow {

    @WorkflowMethod
    String executeOpenFinanceCycle(String rawRequirements);

    @SignalMethod
    void approveCab(boolean approved);
}
