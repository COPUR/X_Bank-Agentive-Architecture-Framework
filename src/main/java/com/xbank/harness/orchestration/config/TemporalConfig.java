package com.xbank.harness.orchestration.config;

import com.xbank.harness.orchestration.adapter.in.temporal.CognitiveWorkflowImpl;
import com.xbank.harness.orchestration.adapter.in.temporal.OrchestrationActivitiesImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class TemporalConfig {

    public static final String TASK_QUEUE = "COGNITIVE_HARNESS_TASK_QUEUE";

    @Bean
    public WorkflowServiceStubs workflowServiceStubs() {
        return WorkflowServiceStubs.newLocalServiceStubs();
    }

    @Bean
    public WorkflowClient workflowClient(WorkflowServiceStubs serviceStubs) {
        return WorkflowClient.newInstance(serviceStubs);
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient workflowClient) {
        return WorkerFactory.newInstance(workflowClient);
    }

    @Bean
    public OrchestrationActivitiesImpl orchestrationActivities(
            com.xbank.harness.ingestion.application.port.in.IngestionUseCase ingestionUseCase,
            com.xbank.harness.topology.application.port.in.TopologyUseCase topologyUseCase,
            com.xbank.harness.compliance.application.port.in.ComplianceUseCase complianceUseCase,
            com.xbank.harness.governance.application.port.in.GovernanceUseCase governanceUseCase,
            com.xbank.harness.quality.application.port.in.QualityUseCase qualityUseCase) {
        return new OrchestrationActivitiesImpl(
                ingestionUseCase, 
                topologyUseCase, 
                complianceUseCase, 
                governanceUseCase, 
                qualityUseCase);
    }

    // Since Temporal requires the worker factory to start, we can define a bean to initialize it.
    @Bean
    public Worker worker(WorkerFactory factory, OrchestrationActivitiesImpl activities) {
        Worker worker = factory.newWorker(TASK_QUEUE);
        worker.registerWorkflowImplementationTypes(CognitiveWorkflowImpl.class);
        worker.registerActivitiesImplementations(activities);
        factory.start();
        return worker;
    }
}
