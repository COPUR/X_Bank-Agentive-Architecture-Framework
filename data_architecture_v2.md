# Data Architecture - X_Bank V2 Directory Mapping

The BDAT Data Architecture governs our database-per-service isolation, vector compliance rules, Temporal events, and zero-trust masking filters. It maps directly to our directory structure as follows:

## 1. Database-Per-Service Isolation (PostgreSQL RDS Multi-AZ)
*   **Implementation Code**: `data/migrations/`
*   **Mechanism**: Database schemas are maintained and migrated using migration scripts inside `data/migrations/`. These scripts separate the co-located monolithic database into physically isolated schemas:
    *   `tbl_accounts`: Created and managed using isolated schemas (matching `app/Entities.java` definitions).
    *   `tbl_cards` (Isolated CDE): Maintained on a physically separate RDS instance with zero database-level foreign key references to accounts.

## 2. Distributed State & Intent-Driven Temporal Workflows (ADR-002)
*   **Implementation Code**: `app/services/TemporalOrchestrator.java`
*   **Mechanism**: Since databases are isolated, cross-service transactions are coordinated asynchronously. The `TemporalOrchestrator.java` service consumes and publishes state change event payloads to the `temporal-intent-events` Kafka topic via Apache Kafka (AWS MSK). It enforces strict rollback operations (e.g., reversing balance reservations) upon authorization failures to maintain eventual consistency without distributed locking.

## 3. Vector Compliance Rules (pgvector)
*   **Implementation Code**: `scripts/SeedPgvector.java` & `app/agents/tools/VectorSearchService.java`
*   **Mechanism**:
    *   The `SeedPgvector.java` script vectorizes and embeds raw regulatory texts (CBUAE Circular 3/2025, PCI-DSS v4, GDPR) and seeds them into the PostgreSQL RDS instance via `data/seed_regulations.sql`.
    *   During design evaluations, Agent 3 evaluates payloads via LLM cascades. If inference fails, it gracefully degrades to deterministic cosine-similarity rules (`app/agents/tools/VectorSearchService.java`) against these embedded compliance vectors.

## 4. Semantic Vector Cache (Redis/Qdrant)
*   **Implementation Code**: `app/components/SemanticCacheManager.java`
*   **Mechanism**: Operates ahead of the LLM inference layer at the Kong Agent Gateway. It stores previously validated prompts and their embeddings. If a structurally similar architectural intent is detected, it returns the cached response, completely bypassing the heavy LLM computation and avoiding Time-to-First-Token (TTFT) latency. Persistent Memory Banks employ strict write-filters to prevent Stored XSS.

## 5. Zero-Trust Data Masking & Sanitization (ADR-004)
*   **Implementation Code**: `app/security/PiiMaskerFilter.java` & `app/security/LogSanitizer.java`
*   **Mechanism**:
    *   `PiiMaskerFilter.java` acts as an input filter, sanitizing and masking IBANs or customer IDs before processing.
    *   `LogSanitizer.java` operates as a runtime interceptor programmatically scanning standard out stream buffers to replace plaintext PII or card tokens with encrypted masking tags before writing to the diagnostic logging files.
