package com.xbank.harness.agents;

import com.xbank.harness.services.AgentActivities;
import org.springframework.stereotype.Service;

@Service
public class Agent5Dora implements AgentActivities {
    public String ingestAndMap(String prdContent) { throw new UnsupportedOperationException(); }
    public String queryTopology(String domain) { throw new UnsupportedOperationException(); }
    public String runComplianceGate(String schema) { throw new UnsupportedOperationException(); }
    public void notifyCabBoard(String res) { throw new UnsupportedOperationException(); }
    public String computeDoraMetrics(String id) { return "DORA_METRICS_COMPUTED"; }
}
