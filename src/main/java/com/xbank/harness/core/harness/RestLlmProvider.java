package com.xbank.harness.core.harness;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * Concrete implementation of LlmProvider using Java's built-in HttpClient.
 * Uses an OpenAI-compatible REST payload to remain provider-agnostic 
 * (can point to OpenAI, Local Ollama, vLLM, etc.) without heavy SDKs.
 */
@Component
public class RestLlmProvider implements LlmProvider {

    private final String endpointUrl;
    private final String apiKey;
    private final String modelName;
    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public RestLlmProvider(
            @Value("${llm.provider.url:https://api.openai.com/v1/chat/completions}") String endpointUrl,
            @Value("${llm.provider.apiKey:default-key}") String apiKey,
            @Value("${llm.provider.model:gpt-4}") String modelName) {
        this.endpointUrl = endpointUrl;
        this.apiKey = apiKey;
        this.modelName = modelName;
        this.httpClient = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    @Override
    public ModelResponse invoke(String systemPrompt, String userPrompt, List<ToolDefinition> tools) {
        try {
            ObjectNode payload = mapper.createObjectNode();
            payload.put("model", modelName);
            
            ArrayNode messages = payload.putArray("messages");
            
            ObjectNode systemMsg = mapper.createObjectNode();
            systemMsg.put("role", "system");
            systemMsg.put("content", systemPrompt);
            messages.add(systemMsg);
            
            ObjectNode userMsg = mapper.createObjectNode();
            userMsg.put("role", "user");
            userMsg.put("content", userPrompt);
            messages.add(userMsg);
            
            if (tools != null && !tools.isEmpty()) {
                ArrayNode toolsArray = payload.putArray("tools");
                for (ToolDefinition tool : tools) {
                    ObjectNode toolNode = mapper.createObjectNode();
                    toolNode.put("type", "function");
                    ObjectNode funcNode = mapper.createObjectNode();
                    funcNode.put("name", tool.getName());
                    funcNode.put("description", tool.getDescription());
                    // Parse inputSchema string into JsonNode
                    funcNode.set("parameters", mapper.readTree(tool.getInputSchema()));
                    toolNode.set("function", funcNode);
                    toolsArray.add(toolNode);
                }
            }

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpointUrl))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(payload)))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() != 200) {
                return new ModelResponse(false, null, null, "API Error: " + response.body());
            }
            
            JsonNode responseJson = mapper.readTree(response.body());
            JsonNode messageNode = responseJson.path("choices").path(0).path("message");
            
            if (messageNode.has("tool_calls")) {
                JsonNode toolCall = messageNode.path("tool_calls").get(0);
                String toolName = toolCall.path("function").path("name").asText();
                String argsString = toolCall.path("function").path("arguments").asText();
                return new ModelResponse(true, toolName, mapper.readTree(argsString), null);
            } else {
                return new ModelResponse(false, null, null, messageNode.path("content").asText());
            }

        } catch (Exception e) {
            return new ModelResponse(false, null, null, "Internal Error during LLM invocation: " + e.getMessage());
        }
    }
}
