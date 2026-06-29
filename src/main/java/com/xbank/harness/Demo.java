package com.xbank.harness;

import com.xbank.harness.agents.*;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("==================================================");
        System.out.println("🚀 X_Bank Cognitive Harness - Demo Execution");
        System.out.println("==================================================\n");

        String prdInput = "Build a new Open Finance API for retrieving Card Limits.";
        System.out.println("[TRIGGER] Incoming PRD from Jira/Confluence Webhook:");
        System.out.println("          -> " + prdInput + "\n");

        // Agent 1
        System.out.println("🤖 [AGENT 1 - Ingestion] Mapping PRD to BIAN Service Landscape...");
        Agent1Ingestion a1 = new Agent1Ingestion();
        String mappedDomain = a1.ingestAndMap(prdInput);
        System.out.println("   ✓ Output: " + mappedDomain + "\n");
        Thread.sleep(1000);

        // Agent 2
        System.out.println("🤖 [AGENT 2 - Topographer] Querying CMDB GraphQL for physical infrastructure...");
        Agent2Topographer a2 = new Agent2Topographer();
        String schema = a2.queryTopology(mappedDomain);
        System.out.println("   ✓ Output: " + schema + "\n");
        Thread.sleep(1000);

        // Agent 3
        System.out.println("🤖 [AGENT 3 - Scoper (Circuit Breaker)] Validating schema against CBUAE / PCI-DSS guardrails via pgvector RAG...");
        Agent3Scoper a3 = new Agent3Scoper();
        String compliance = a3.runComplianceGate(schema);
        System.out.println("   ✓ Output: " + compliance + "\n");
        Thread.sleep(1000);

        if (compliance.contains("HARD_STOP")) {
            System.err.println("❌ [SYSTEM] Graceful Degradation Triggered. Workflow Terminated.\n");
            return;
        }

        // Agent 4
        System.out.println("🤖 [AGENT 4 - Governor] Halting Workflow for Human-in-the-Loop CAB Sign-off...");
        Agent4Governor a4 = new Agent4Governor();
        a4.notifyCabBoard(compliance);
        System.out.println("   [TEMPORAL] Workflow.await() -> Paused.");
        Thread.sleep(2000); // Simulate waiting for Human click
        System.out.println("   [IDP WEBHOOK] -> Received CAB 'APPROVE' Signal!\n");
        
        // Agent 5
        System.out.println("🤖 [AGENT 5 - DORA] Reconciling GitOps state and computing Workload Metrics...");
        Agent5Dora a5 = new Agent5Dora();
        String metrics = a5.computeDoraMetrics("DEPLOY-XYZ-123");
        System.out.println("   ✓ Output: " + metrics + "\n");
        
        System.out.println("==================================================");
        System.out.println("✅ Workflow Complete. Architecture fully deployed and verified.");
        System.out.println("==================================================");
    }
}
