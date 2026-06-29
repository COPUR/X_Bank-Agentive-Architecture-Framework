package com.xbank.harness.core.memory;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Implementation of MemoryManager providing Persistent Storage.
 * Uses a dedicated directory that persists across agent restarts.
 */
@Component
public class PersistentMemoryManager implements MemoryManager {

    private final Path memoryDir;

    public PersistentMemoryManager() {
        this.memoryDir = Paths.get("data", "agent-memory");
        try {
            if (!Files.exists(memoryDir)) {
                Files.createDirectories(memoryDir);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize Persistent Memory directory", e);
        }
    }

    @Override
    public String loadContext(String sessionId) {
        Path sessionFile = memoryDir.resolve(sessionId + ".txt");
        if (Files.exists(sessionFile)) {
            try {
                return Files.readString(sessionFile);
            } catch (IOException e) {
                System.err.println("Failed to read memory for session: " + sessionId);
            }
        }
        return "";
    }

    @Override
    public void saveContext(String sessionId, String context) {
        Path sessionFile = memoryDir.resolve(sessionId + ".txt");
        try {
            Files.writeString(sessionFile, context);
        } catch (IOException e) {
            System.err.println("Failed to write memory for session: " + sessionId);
        }
    }
}
