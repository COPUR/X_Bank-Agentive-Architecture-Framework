package com.xbank.harness.agents.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.xbank.harness.core.harness.ToolDefinition;
import org.springframework.stereotype.Component;

@Component
public class AlfabetClient implements ToolDefinition {
    
    @Override
    public String getName() {
        return "query_alfabet_cmdb";
    }

    @Override
    public String getDescription() {
        return "Queries the Alfabet CMDB to retrieve the current topology and LLD schema for a given BIAN domain.";
    }

    @Override
    public String getInputSchema() {
        return "{\n" +
               "  \"type\": \"object\",\n" +
               "  \"properties\": {\n" +
               "    \"domain\": {\n" +
               "      \"type\": \"string\",\n" +
               "      \"description\": \"The BIAN service domain to query (e.g., 'Payment Execution').\"\n" +
               "    }\n" +
               "  },\n" +
               "  \"required\": [\"domain\"]\n" +
               "}";
    }

    @Override
    public String execute(JsonNode input) {
        String domain = input.get("domain").asText();
        // Mocked response for now
        return "TOPOLOGY_DATA_FOR_" + domain + ": { \"schema\": \"PUBLIC_S3_BUCKET\" }";
    }
}