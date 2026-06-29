package com.xbank.harness.core.harness;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Interface for Observability and Lifecycle Management.
 * Implements hooks that trigger before and after tool calls.
 * Provides transparency into the agent's decision-making process.
 */
public interface HarnessLifecycleHooks {
    
    /**
     * Triggered just before the model executes a chosen tool.
     * @param tool The selected tool.
     * @param input The validated JSON input schema provided by the model.
     */
    void beforeToolCall(ToolDefinition tool, JsonNode input);

    /**
     * Triggered immediately after the tool execution completes.
     * @param tool The selected tool.
     * @param result The outcome of the tool call.
     */
    void afterToolCall(ToolDefinition tool, String result);
}
