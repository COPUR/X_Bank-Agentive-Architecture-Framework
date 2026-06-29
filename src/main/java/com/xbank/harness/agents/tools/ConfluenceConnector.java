package com.xbank.harness.agents.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.xbank.harness.core.harness.ToolDefinition;
import org.springframework.stereotype.Service;

@Service
public class ConfluenceConnector implements ToolDefinition {

    @Override
    public String getName() {
        return "search_confluence_docs";
    }

    @Override
    public String getDescription() {
        return "Retrieves documentation and existing architecture decisions from the Confluence knowledge base.";
    }

    @Override
    public String getInputSchema() {
        return "{\n" +
               "  \"type\": \"object\",\n" +
               "  \"properties\": {\n" +
               "    \"pageTitle\": {\n" +
               "      \"type\": \"string\",\n" +
               "      \"description\": \"The title of the Confluence page to search for.\"\n" +
               "    }\n" +
               "  },\n" +
               "  \"required\": [\"pageTitle\"]\n" +
               "}";
    }

    @Override
    public String execute(JsonNode input) {
        String pageTitle = input.get("pageTitle").asText();
        // Mocked response for now
        return "CONFLUENCE_DATA_FOR_" + pageTitle.replace(" ", "_").toUpperCase() + ": Architecture Document details.";
    }
}