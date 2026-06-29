package com.xbank.harness.orchestration.adapter.in.temporal;

import com.xbank.harness.ingestion.application.port.in.IngestionUseCase;
import com.xbank.harness.topology.application.port.in.TopologyUseCase;
import com.xbank.harness.compliance.application.port.in.ComplianceUseCase;
import com.xbank.harness.governance.application.port.in.GovernanceUseCase;
import com.xbank.harness.quality.application.port.in.QualityUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrchestrationActivitiesImplTest {

    @Test
    public void testActivitiesDelegation() {
        IngestionUseCase ingestion = mock(IngestionUseCase.class);
        TopologyUseCase topology = mock(TopologyUseCase.class);
        ComplianceUseCase compliance = mock(ComplianceUseCase.class);
        GovernanceUseCase governance = mock(GovernanceUseCase.class);
        QualityUseCase quality = mock(QualityUseCase.class);

        OrchestrationActivitiesImpl activities = new OrchestrationActivitiesImpl(
                ingestion, topology, compliance, governance, quality);

        when(ingestion.ingestAndMap("prd")).thenReturn("mapped");
        when(topology.queryTopology("mapped")).thenReturn("schema");
        when(compliance.runComplianceGate("schema")).thenReturn("pass");
        when(quality.computeDoraMetrics("id")).thenReturn("dora");

        assertEquals("mapped", activities.ingestAndMap("prd"));
        assertEquals("schema", activities.queryTopology("mapped"));
        assertEquals("pass", activities.runComplianceGate("schema"));
        assertEquals("dora", activities.computeDoraMetrics("id"));
        
        activities.notifyCabBoard("pass");
        verify(governance, times(1)).notifyCabBoard("pass");
    }
}
