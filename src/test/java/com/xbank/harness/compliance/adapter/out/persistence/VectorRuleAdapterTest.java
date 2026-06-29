package com.xbank.harness.compliance.adapter.out.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class VectorRuleAdapterTest {

    @Mock
    private RegulatoryRuleRepository repository;

    @InjectMocks
    private VectorRuleAdapter vectorRuleAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveRelevantRules() {
        RegulatoryRuleEntity rule1 = new RegulatoryRuleEntity();
        rule1.setRuleCode("PCI-DSS-4.0-REQ1");
        
        RegulatoryRuleEntity rule2 = new RegulatoryRuleEntity();
        rule2.setRuleCode("CBUAE-3-2025-OPENFINANCE");

        when(repository.findSimilarRules(anyString(), anyInt())).thenReturn(Arrays.asList(rule1, rule2));

        List<String> result = vectorRuleAdapter.retrieveRelevantRules("schema-context-json");

        assertEquals(2, result.size());
        assertEquals("PCI-DSS-4.0-REQ1", result.get(0));
        assertEquals("CBUAE-3-2025-OPENFINANCE", result.get(1));
    }
}
