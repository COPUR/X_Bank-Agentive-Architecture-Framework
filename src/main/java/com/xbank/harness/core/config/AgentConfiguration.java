package com.xbank.harness.core.config;

import java.util.List;

/**
 * Declarative configuration for an Agent, fulfilling the "Configuration-Driven Orchestration" requirement.
 * This replaces hardcoded manual setup, defining behavior, tools, and system instructions.
 */
public class AgentConfiguration {
    private String agentId;
    private String systemPrompt;
    private List<String> enabledTools;
    private List<String> permissions;

    public AgentConfiguration() {}

    public AgentConfiguration(String agentId, String systemPrompt, List<String> enabledTools, List<String> permissions) {
        this.agentId = agentId;
        this.systemPrompt = systemPrompt;
        this.enabledTools = enabledTools;
        this.permissions = permissions;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getSystemPrompt() {
        return systemPrompt;
    }

    public void setSystemPrompt(String systemPrompt) {
        this.systemPrompt = systemPrompt;
    }

    public List<String> getEnabledTools() {
        return enabledTools;
    }

    public void setEnabledTools(List<String> enabledTools) {
        this.enabledTools = enabledTools;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
