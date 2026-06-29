package com.xbank.harness.governance.application.service;

import com.xbank.harness.governance.application.port.in.GovernanceUseCase;
import org.springframework.stereotype.Service;

@Service
public class GovernanceService implements GovernanceUseCase {
    @Override
    public void notifyCabBoard(String complianceResult) {
        if (complianceResult == null) {
            throw new IllegalArgumentException("Cannot notify board with null compliance result.");
        }
        System.out.println("Notifying CAB Board with result: " + complianceResult);
    }
}
