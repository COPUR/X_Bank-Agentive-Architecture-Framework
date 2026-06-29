package com.xbank.harness.core.harness;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xbank.harness.core.config.AgentConfiguration;
import com.xbank.harness.core.memory.PersistentMemoryManager;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgentHarnessTest {

    @Test
    public void testManagedHarnessDynamicSequencing() {
        // Mock Tool
        ToolDefinition mockTool = new ToolDefinition() {
            @Override
            public String getName() { return "mock_tool"; }
            @Override
            public String getDescription() { return "A mock tool."; }
            @Override
            public String getInputSchema() { return "{}"; }
            @Override
            public String execute(JsonNode input) { return "MOCK_RESULT"; }
        };

        // Mock LLM Provider
        LlmProvider mockProvider = (systemPrompt, userPrompt, tools) -> {
            if (userPrompt.contains("MOCK_RESULT")) {
                return new LlmProvider.ModelResponse(false, null, null, "Final Task Complete");
            }
            ObjectMapper mapper = new ObjectMapper();
            return new LlmProvider.ModelResponse(true, "mock_tool", mapper.createObjectNode(), null);
        };

        // Mock Hook
        boolean[] hookCalled = {false};
        HarnessLifecycleHooks mockHook = new HarnessLifecycleHooks() {
            @Override
            public void beforeToolCall(ToolDefinition tool, JsonNode input) { hookCalled[0] = true; }
            @Override
            public void afterToolCall(ToolDefinition tool, String result) { }
        };

        // Initialize Memory Manager
        PersistentMemoryManager memoryManager = new PersistentMemoryManager();
        String sessionId = "test-session-" + System.currentTimeMillis();

        AgentHarness harness = new AgentHarness(mockProvider, Collections.singletonList(mockTool), Collections.singletonList(mockHook), memoryManager);

        // Configuration-Driven Orchestration
        AgentConfiguration config = new AgentConfiguration();
        config.setAgentId("test-agent");
        config.setSystemPrompt("System Config Prompt");
        config.setEnabledTools(Arrays.asList("mock_tool"));

        String result = harness.executeTask(sessionId, config, "Do something");

        assertEquals("Final Task Complete", result);
        assertTrue(hookCalled[0], "Lifecycle hook should be triggered");

        // Verify Cross-Session Memory
        String savedContext = memoryManager.loadContext(sessionId);
        assertTrue(savedContext.contains("Do something"));
        assertTrue(savedContext.contains("MOCK_RESULT"));
        assertTrue(savedContext.contains("Final Task Complete"));
    }
}
