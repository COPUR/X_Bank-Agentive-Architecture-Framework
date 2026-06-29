package com.xbank.harness.agents;

import com.xbank.harness.services.AgentActivities;
import org.springframework.stereotype.Service;

@Service
public class Agent2Topographer implements AgentActivities {
    public String ingestAndMap(String prdContent) { throw new UnsupportedOperationException(); }
    public String queryTopology(String domain) { return "LLD_SCHEMA_DRAFT"; }
    public String runComplianceGate(String schema) { throw new UnsupportedOperationException(); }
    public void notifyCabBoard(String res) { throw new UnsupportedOperationException(); }
    public String computeDoraMetrics(String id) { throw new UnsupportedOperationException(); }
}
