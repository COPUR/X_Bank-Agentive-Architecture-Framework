package com.xbank.harness.core.harness;

import com.fasterxml.jackson.databind.JsonNode;
import com.xbank.harness.observability.TokenTracker;
import com.xbank.harness.observability.TraceLogger;
import org.springframework.stereotype.Component;

@Component
public class ObservabilityLifecycleHooks implements HarnessLifecycleHooks {

    private final TraceLogger traceLogger;
    private final TokenTracker tokenTracker;

    public ObservabilityLifecycleHooks(TraceLogger traceLogger, TokenTracker tokenTracker) {
        this.traceLogger = traceLogger;
        this.tokenTracker = tokenTracker;
    }

    @Override
    public void beforeToolCall(ToolDefinition tool, JsonNode input) {
        traceLogger.logTrace("AgentHarness", "Model requested tool: " + tool.getName() + " with args: " + input.toString());
        // Rough estimate of tokens used for prompt and tool call generation
        tokenTracker.trackTokens("ToolCall_Generation", 150); 
    }

    @Override
    public void afterToolCall(ToolDefinition tool, String result) {
        traceLogger.logTrace("AgentHarness", "Tool " + tool.getName() + " executed successfully. Result snippet: " + 
                (result.length() > 50 ? result.substring(0, 50) + "..." : result));
        
        // Rough estimate of tokens consumed by the tool's result text
        int resultTokens = result.length() / 4; 
        tokenTracker.trackTokens("ToolCall_Result", resultTokens);
    }
}
