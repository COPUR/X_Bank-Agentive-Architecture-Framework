package com.xbank.harness.orchestration.adapter.in.temporal;

import com.xbank.harness.ingestion.application.port.in.IngestionUseCase;
import com.xbank.harness.topology.application.port.in.TopologyUseCase;
import com.xbank.harness.compliance.application.port.in.ComplianceUseCase;
import com.xbank.harness.governance.application.port.in.GovernanceUseCase;
import com.xbank.harness.quality.application.port.in.QualityUseCase;
import org.springframework.stereotype.Service;

@Service
public class OrchestrationActivitiesImpl implements OrchestrationActivities {

    private final IngestionUseCase ingestionUseCase;
    private final TopologyUseCase topologyUseCase;
    private final ComplianceUseCase complianceUseCase;
    private final GovernanceUseCase governanceUseCase;
    private final QualityUseCase qualityUseCase;

    public OrchestrationActivitiesImpl(
            IngestionUseCase ingestionUseCase,
            TopologyUseCase topologyUseCase,
            ComplianceUseCase complianceUseCase,
            GovernanceUseCase governanceUseCase,
            QualityUseCase qualityUseCase) {
        this.ingestionUseCase = ingestionUseCase;
        this.topologyUseCase = topologyUseCase;
        this.complianceUseCase = complianceUseCase;
        this.governanceUseCase = governanceUseCase;
        this.qualityUseCase = qualityUseCase;
    }

    @Override
    public String ingestAndMap(String prdContent) {
        return ingestionUseCase.ingestAndMap(prdContent);
    }

    @Override
    public String queryTopology(String mappedDomain) {
        return topologyUseCase.queryTopology(mappedDomain);
    }

    @Override
    public String runComplianceGate(String lldSchema) {
        return complianceUseCase.runComplianceGate(lldSchema);
    }

    @Override
    public void notifyCabBoard(String complianceResult) {
        governanceUseCase.notifyCabBoard(complianceResult);
    }

    @Override
    public String computeDoraMetrics(String deploymentId) {
        return qualityUseCase.computeDoraMetrics(deploymentId);
    }
}
