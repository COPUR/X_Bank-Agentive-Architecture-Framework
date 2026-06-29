package com.xbank.harness.topology.application.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TopologyServiceTest {

    private final TopologyService service = new TopologyService();

    @Test
    public void testQueryTopologyForPayment() {
        String result = service.queryTopology("MAPPED_BIAN_DOMAIN: Payment Execution");
        assertTrue(result.contains("\"schema\": \"LLD_PAYMENT\""));
    }

    @Test
    public void testQueryTopologyForDefault() {
        String result = service.queryTopology("MAPPED_BIAN_DOMAIN: Current Account");
        assertTrue(result.contains("\"schema\": \"LLD_DEFAULT\""));
    }

    @Test
    public void testQueryTopologyEmptyDomain() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.queryTopology("");
        });
    }
}
