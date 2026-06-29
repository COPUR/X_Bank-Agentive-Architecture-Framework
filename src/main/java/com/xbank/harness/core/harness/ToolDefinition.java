package com.xbank.harness.core.harness;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Standardized interface for all tools used by the Agent Harness.
 * Enforces Model-Driven Architecture principles.
 */
public interface ToolDefinition {
    
    /**
     * @return The clear name of the tool.
     */
    String getName();

    /**
     * @return A precise natural-language description to guide the model (Descriptive Steering).
     */
    String getDescription();

    /**
     * @return The structured JSON schema required as input for this tool.
     */
    String getInputSchema();

    /**
     * The functional callback executed when the model selects this tool.
     * @param input The JSON payload matching the input schema.
     * @return The result of the tool execution as a String.
     */
    String execute(JsonNode input);
}
