package com.xbank.harness.observability;

import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class TraceLogger {
    
    public void logTrace(String context, String message) {
        // In a real application, this would send structured logs to Datadog/ELK
        System.out.println(String.format("[%s] TRACE [%s]: %s", Instant.now(), context, message));
    }
}