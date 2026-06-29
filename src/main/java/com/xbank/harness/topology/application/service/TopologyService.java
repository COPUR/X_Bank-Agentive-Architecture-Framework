package com.xbank.harness.topology.application.service;

import com.xbank.harness.topology.application.port.in.TopologyUseCase;
import org.springframework.stereotype.Service;

@Service
public class TopologyService implements TopologyUseCase {
    @Override
    @org.springframework.cache.annotation.Cacheable(value = "topologyCache", key = "#mappedDomain")
    public String queryTopology(String mappedDomain) {
        System.out.println("TopologyDomain: Querying CMDB for " + mappedDomain);
        
        if (mappedDomain == null || mappedDomain.trim().isEmpty()) {
            throw new IllegalArgumentException("Mapped Domain cannot be empty to query topology.");
        }
        
        if (mappedDomain.contains("Payment Execution")) {
            return "{ \"schema\": \"LLD_PAYMENT\", \"hasPublicEndpoint\": false }";
        }
        return "{ \"schema\": \"LLD_DEFAULT\", \"hasPublicEndpoint\": false }";
    }
}
