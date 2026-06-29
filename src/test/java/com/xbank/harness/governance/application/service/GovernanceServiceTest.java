package com.xbank.harness.governance.application.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GovernanceServiceTest {

    private final GovernanceService service = new GovernanceService();

    @Test
    public void testNotifyCabBoard() {
        assertDoesNotThrow(() -> service.notifyCabBoard("PASS_COMPLIANCE"));
    }

    @Test
    public void testNotifyCabBoardNull() {
        assertThrows(IllegalArgumentException.class, () -> service.notifyCabBoard(null));
    }
}
