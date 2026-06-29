package com.xbank.harness.agents;

import com.xbank.harness.services.AgentActivities;
import org.springframework.stereotype.Service;

@Service
public class Agent1Ingestion implements AgentActivities {
    public String ingestAndMap(String prdContent) { return "MAPPED_BIAN_DOMAIN"; }
    public String queryTopology(String domain) { throw new UnsupportedOperationException(); }
    public String runComplianceGate(String schema) { throw new UnsupportedOperationException(); }
    public void notifyCabBoard(String res) { throw new UnsupportedOperationException(); }
    public String computeDoraMetrics(String id) { throw new UnsupportedOperationException(); }
}
