package com.xbank.harness.agents.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.xbank.harness.core.harness.ToolDefinition;
import org.springframework.stereotype.Component;

@Component
public class JiraConnector implements ToolDefinition {
    
    @Override
    public String getName() {
        return "create_jira_ticket";
    }

    @Override
    public String getDescription() {
        return "Creates a Jira ticket for human-in-the-loop review (e.g., CAB approval).";
    }

    @Override
    public String getInputSchema() {
        return "{\n" +
               "  \"type\": \"object\",\n" +
               "  \"properties\": {\n" +
               "    \"title\": {\n" +
               "      \"type\": \"string\",\n" +
               "      \"description\": \"The title of the ticket.\"\n" +
               "    },\n" +
               "    \"description\": {\n" +
               "      \"type\": \"string\",\n" +
               "      \"description\": \"Detailed description of what needs review.\"\n" +
               "    }\n" +
               "  },\n" +
               "  \"required\": [\"title\", \"description\"]\n" +
               "}";
    }

    @Override
    public String execute(JsonNode input) {
        String title = input.get("title").asText();
        return "JIRA_TICKET_CREATED: " + title;
    }
}