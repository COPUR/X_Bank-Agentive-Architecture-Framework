package com.xbank.harness.orchestration.adapter.in.temporal;

import com.xbank.harness.core.harness.LlmProvider;
import org.springframework.stereotype.Component;
import java.util.Collections;

@Component
public class RequirementOrchestratorActivitiesImpl implements RequirementOrchestratorActivities {

    private final LlmProvider llmProvider;

    public RequirementOrchestratorActivitiesImpl(LlmProvider llmProvider) {
        this.llmProvider = llmProvider;
    }

    @Override
    public String translateToTasks(String rawRequirements) {
        String systemPrompt = "You are a technical orchestrator. Extract technical tasks from the provided Product Requirements Document (PRD). " +
                "You MUST return the output strictly as a JSON array of objects. Each object must have a 'taskId' (string), " +
                "'description' (string detailing the technical need), and 'status' (string, set to 'PENDING'). " +
                "Do not return any markdown formatting or surrounding text, just the raw JSON array.";
        
        LlmProvider.ModelResponse response = llmProvider.invoke(systemPrompt, rawRequirements, Collections.emptyList());
        
        if (response.isToolCall || response.textContent == null || response.textContent.isEmpty()) {
            throw new RuntimeException("Failed to translate requirements to JSON tasks. Model did not return valid text.");
        }
        
        return response.textContent;
    }
}
