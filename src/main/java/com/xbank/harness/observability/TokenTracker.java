package com.xbank.harness.observability;

import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class TokenTracker {

    private final AtomicInteger totalTokensConsumed = new AtomicInteger(0);

    public void trackTokens(String operation, int estimatedTokens) {
        int currentTotal = totalTokensConsumed.addAndGet(estimatedTokens);
        System.out.println(String.format("TOKEN_METRIC [%s]: +%d tokens. Total session usage: %d", operation, estimatedTokens, currentTotal));
    }
}