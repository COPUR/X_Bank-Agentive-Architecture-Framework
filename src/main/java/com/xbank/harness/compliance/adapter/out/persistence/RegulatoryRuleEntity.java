package com.xbank.harness.compliance.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "regulatory_rules")
public class RegulatoryRuleEntity {

    @Id
    private UUID id;

    @Column(name = "rule_code", nullable = false)
    private String ruleCode;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // pgvector column handling typically maps to an Object or custom type.
    // For simplicity with standard JPA without custom dialects in this mock, we store it as a generic float array string or custom type.
    @Column(name = "embedding", columnDefinition = "vector(1536)")
    private String embedding;

    public RegulatoryRuleEntity() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    
    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getEmbedding() { return embedding; }
    public void setEmbedding(String embedding) { this.embedding = embedding; }
}
