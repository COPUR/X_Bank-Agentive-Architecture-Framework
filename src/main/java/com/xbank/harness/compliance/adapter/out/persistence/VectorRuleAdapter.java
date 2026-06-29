package com.xbank.harness.compliance.adapter.out.persistence;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VectorRuleAdapter {

    private final RegulatoryRuleRepository repository;

    public VectorRuleAdapter(RegulatoryRuleRepository repository) {
        this.repository = repository;
    }

    public List<String> retrieveRelevantRules(String schemaContext) {
        // Mock embedding generation for the query
        String mockEmbedding = "[0.1, 0.2, 0.3]"; 
        
        List<RegulatoryRuleEntity> entities = repository.findSimilarRules(mockEmbedding, 3);
        
        return entities.stream()
            .map(RegulatoryRuleEntity::getRuleCode)
            .collect(Collectors.toList());
    }
}
