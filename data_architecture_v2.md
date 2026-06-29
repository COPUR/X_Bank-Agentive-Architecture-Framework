Data Architecture - RedBank V2 Directory Mapping
The BDAT Data Architecture governs our database-per-service isolation, vector compliance rules, Temporal events, and zero-trust masking filters. It maps directly to our directory structure as follows:
1. Database-Per-Service Isolation (PostgreSQL RDS Multi-AZ)
	•	Implementation Code: data/migrations/
	•	Mechanism: Database schemas are maintained and migrated using migration scripts inside data/migrations/. These scripts separate the co-located monolithic database into physically isolated schemas:
	•	tbl_accounts: Created and managed using isolated schemas (matching app/models.py definitions).
	•	tbl_cards (Isolated CDE): Maintained on a physically separate RDS instance with zero database-level foreign key references to accounts.
2. Distributed State & Temporal Transaction Orchestration
	•	Implementation Code: app/services/saga_orchestrator.py
	•	Mechanism: Since databases are isolated, cross-service transactions are coordinated asynchronously. The saga_orchestrator.py service consumes and publishes state change event payloads to the temporal-intent-events Kafka topic. It enforces strict rollback operations (e.g., reversing balance reservations) upon authorization failures.
3. Vector Compliance Rules (pgvector)
	•	Implementation Code: scripts/seed_pgvector.py & app/agents/tools/vector_search.py
	•	Mechanism:
	•	The seed_pgvector.py script vectorizes and embeds raw regulatory texts (CBUAE Circular 3/2025, PCI-DSS v4, GDPR) and seeds them into the PostgreSQL RDS instance via data/seed_regulations.sql.
	•	During design evaluations, app/agents/tools/vector_search.py performs cosine-similarity searches to check LLD schemas against these embedded compliance rules.
4. Zero-Trust Data Masking & Sanitization
	•	Implementation Code: app/security/pii_masker.py & app/security/log_sanitizer.py
	•	Mechanism:
	•	pii_masker.py acts as an input filter, sanitizing and masking IBANs or customer IDs before processing.
	•	log_sanitizer.py operates as a runtime interceptor (ADR-004), programmatically scanning standard out stream buffers to replace plaintext PII or card tokens with encrypted masking tags before writing to the diagnostic logging files.

