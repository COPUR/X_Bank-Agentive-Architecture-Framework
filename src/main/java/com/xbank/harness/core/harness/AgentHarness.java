package com.xbank.harness.core.harness;

import com.xbank.harness.core.config.AgentConfiguration;
import com.xbank.harness.core.memory.MemoryManager;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The core Agent Harness that acts as the control layer between the model and the real world.
 * Enforces dynamic sequencing instead of hardcoding execution flows.
 */
public class AgentHarness {
    
    private final LlmProvider llmProvider;
    private final List<HarnessLifecycleHooks> lifecycleHooks;
    private final Map<String, ToolDefinition> toolsByName;
    private final List<ToolDefinition> allTools;
    private final MemoryManager memoryManager;

    public AgentHarness(LlmProvider llmProvider, List<ToolDefinition> tools, List<HarnessLifecycleHooks> lifecycleHooks, MemoryManager memoryManager) {
        this.llmProvider = llmProvider;
        this.allTools = tools;
        this.toolsByName = tools.stream().collect(Collectors.toMap(ToolDefinition::getName, Function.identity()));
        this.lifecycleHooks = lifecycleHooks;
        this.memoryManager = memoryManager;
    }

    /**
     * Executes a task using dynamic sequencing.
     * The model interprets instructions and determines the sequence of actions.
     */
    public String executeTask(String sessionId, AgentConfiguration config, String taskDescription) {
        // 1. Cross-Session Memory Management
        String previousContext = memoryManager.loadContext(sessionId);
        String currentContext = previousContext.isEmpty() ? taskDescription : previousContext + "\nNew Task: " + taskDescription;
        
        // 2. Configuration-Driven Tool Scoping
        List<ToolDefinition> enabledTools = allTools.stream()
                .filter(t -> config.getEnabledTools().contains(t.getName()))
                .collect(Collectors.toList());

        int maxIterations = 10;
        int currentIteration = 0;
        String systemPrompt = config.getSystemPrompt();
        
        while (currentIteration < maxIterations) {
            LlmProvider.ModelResponse response = llmProvider.invoke(systemPrompt, currentContext, enabledTools);
            
            if (!response.isToolCall) {
                // The model decided the task is complete and returned text
                memoryManager.saveContext(sessionId, currentContext + "\nResult: " + response.textContent);
                return response.textContent;
            }
            
            ToolDefinition tool = toolsByName.get(response.toolName);
            if (tool == null || !config.getEnabledTools().contains(tool.getName())) {
                currentContext += "\nSystem Error: Tool " + response.toolName + " not found or not enabled.";
                currentIteration++;
                continue;
            }
            
            // Lifecycle Hook: Before Tool Call
            for (HarnessLifecycleHooks hook : lifecycleHooks) {
                hook.beforeToolCall(tool, response.toolArguments);
            }
            
            // Execute Tool Callback
            String toolResult;
            try {
                toolResult = tool.execute(response.toolArguments);
            } catch (Exception e) {
                toolResult = "Error executing tool: " + e.getMessage();
            }
            
            // Lifecycle Hook: After Tool Call
            for (HarnessLifecycleHooks hook : lifecycleHooks) {
                hook.afterToolCall(tool, toolResult);
            }
            
            // Append result to context for next dynamic sequence step
            currentContext += "\nTool " + tool.getName() + " returned: " + toolResult;
            currentIteration++;
        }
        
        memoryManager.saveContext(sessionId, currentContext + "\nTask terminated after max iterations.");
        return "Task terminated after reaching maximum iterations (10).";
    }
}
