package com.xbank.harness.agents.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.xbank.harness.core.harness.ToolDefinition;
import org.springframework.stereotype.Service;

@Service
public class VectorSearchService implements ToolDefinition {

    @Override
    public String getName() {
        return "search_vector_database";
    }

    @Override
    public String getDescription() {
        return "Searches the vector database for regulatory and compliance rules (e.g., PCI-DSS, CBUAE) relevant to the architecture.";
    }

    @Override
    public String getInputSchema() {
        return "{\n" +
               "  \"type\": \"object\",\n" +
               "  \"properties\": {\n" +
               "    \"query\": {\n" +
               "      \"type\": \"string\",\n" +
               "      \"description\": \"The specific compliance or regulatory search query.\"\n" +
               "    }\n" +
               "  },\n" +
               "  \"required\": [\"query\"]\n" +
               "}";
    }

    @Override
    public String execute(JsonNode input) {
        String query = input.get("query").asText();
        // Mocked response for now
        return "VECTOR_SEARCH_RESULTS_FOR_" + query.replace(" ", "_").toUpperCase() + ": [Rule 1: No public S3 buckets, Rule 2: FAPI2 required]";
    }
}