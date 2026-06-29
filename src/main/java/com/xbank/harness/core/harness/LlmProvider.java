package com.xbank.harness.core.harness;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

/**
 * Provider-agnostic interface to ensure the harness can support 
 * multiple model providers (e.g., Bedrock, OpenAI, Anthropic) 
 * with minimal configuration changes.
 */
public interface LlmProvider {
    
    /**
     * Invokes the model with a prompt and a list of available tools.
     * The model determines the sequence of actions (Dynamic Sequencing).
     * 
     * @param systemPrompt The plain-language constraints and persona instructions.
     * @param userPrompt The specific task or context.
     * @param tools The standardized list of available tools.
     * @return The raw response from the model, or the name of the tool it decided to call along with arguments.
     */
    ModelResponse invoke(String systemPrompt, String userPrompt, List<ToolDefinition> tools);
    
    class ModelResponse {
        public final boolean isToolCall;
        public final String toolName;
        public final JsonNode toolArguments;
        public final String textContent;
        
        public ModelResponse(boolean isToolCall, String toolName, JsonNode toolArguments, String textContent) {
            this.isToolCall = isToolCall;
            this.toolName = toolName;
            this.toolArguments = toolArguments;
            this.textContent = textContent;
        }
    }
}
