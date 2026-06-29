package com.xbank.harness.compliance.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface RegulatoryRuleRepository extends JpaRepository<RegulatoryRuleEntity, UUID> {

    // Native query using pgvector's cosine distance operator `<=>`
    @Query(value = "SELECT * FROM regulatory_rules ORDER BY embedding <=> cast(:queryEmbedding as vector) LIMIT :maxResults", nativeQuery = true)
    List<RegulatoryRuleEntity> findSimilarRules(@Param("queryEmbedding") String queryEmbedding, @Param("maxResults") int maxResults);
}
