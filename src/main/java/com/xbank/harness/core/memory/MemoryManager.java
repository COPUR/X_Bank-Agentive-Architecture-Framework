package com.xbank.harness.core.memory;

/**
 * Manages Cross-Session Memory for agents.
 * Allows agents to maintain context, user preferences, and history across multiple invocations.
 */
public interface MemoryManager {

    /**
     * Loads the existing context/memory for a given session.
     * @param sessionId The unique identifier scoping the memory.
     * @return The saved context, or an empty string if none exists.
     */
    String loadContext(String sessionId);

    /**
     * Saves or updates the context/memory for a given session.
     * @param sessionId The unique identifier scoping the memory.
     * @param context The memory data to persist.
     */
    void saveContext(String sessionId, String context);
}
