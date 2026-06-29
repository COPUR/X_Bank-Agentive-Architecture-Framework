package com.xbank.harness.core.config;

import org.springframework.stereotype.Component;
import java.util.Arrays;

/**
 * Manages the loading and hot-swapping of Agent Configurations.
 */
@Component
public class ConfigurationManager {

    /**
     * Loads the agent configuration dynamically. In a real scenario, this would load
     * from a YAML/JSON file or a database to enable hot-swapping without redeploy.
     */
    public AgentConfiguration getConfiguration(String agentId) {
        // Mocking dynamic load
        AgentConfiguration config = new AgentConfiguration();
        config.setAgentId(agentId);
        config.setSystemPrompt("You are an autonomous agent responsible for orchestrating tasks. Use tools as needed.");
        config.setEnabledTools(Arrays.asList("query_alfabet_cmdb", "create_jira_ticket"));
        config.setPermissions(Arrays.asList("READ_CMDB", "WRITE_JIRA"));
        return config;
    }
}
