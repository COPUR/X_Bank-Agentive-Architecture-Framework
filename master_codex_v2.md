REDBANK AGENT-FTE-ARCHITECT V2 MASTER CODEX
Codebase-Mapped AI Harness & Sovereign Governance Framework
Document Identifier: RB-EAF-V2-CODEX Classification: RESTRICTED - ENTERPRISE ARCHITECTURE GOVERNANCE Author: Principal Enterprise Architect, RedBank Date: June 28, 2026
1. THE SYSTEM CODEX (THE COUPLING PRINCIPLE)
1.1 The Core Paradigm: Agent = Model + Harness
At RedBank, the foundational design principle is: Agent = Model + Harness.

	•	The Stateless Model (Compute Engine): Standard reasoning models are stateless and lack native secure boundaries or data integration layers.
	•	The Cognitive Orchestration Harness (Scaffolding): The surrounding, sovereign integration and security layer that wraps the model inside RedBank's trust perimeter (AWS EKS, MSK, pgvector).
	•	Harness Engineering (The Discipline): The rigorous engineering practices (Domain-Driven Design, Scrum, TOGAF ADM, and CI/CD GitOps) used to construct and maintain this safe, highly governed scaffolding.
1.2 Spotify Topologies Mapping
The 5 personas of the autonomous multi-agent framework act as distinct code modules of our architectural harness:

	•	Agent 1 (Ingestion & Context Collector - Squad Tier): Code component: app/agents/agent1_ingestion.py. Automatically parses Confluence requirements and Jira stories.
	•	Agent 2 (Infrastructure & DB Topology Mapper - Squad Tier): Code component: app/agents/agent2_topographer.py. Synthesizes LLD schemas and queries GraphQL CMDB APIs.
	•	Agent 3 (Regulatory & Domain Scoper - Guild Tier): Code component: app/agents/agent3_scoper.py. Performs pgvector regulatory matching and triggers compliance hard-stops.
	•	Agent 4 (Governance Facilitator & Presenter - Tribe Tier): Code component: app/agents/agent4_governor.py. Coordinates stakeholder approvals via IdP and compiles slides.
	•	Agent 5 (SWE Intelligence & DORA Auditor - Chapter Tier): Code component: app/agents/agent5_dora.py. Reconciles running code via Semantic Triangle checks.
2. STRATEGIC ARCHITECTURAL DECISIONS (ADRs)
	•	ADR-001 (Sovereign VPC Inference): Run all LLaMA-3-70B model inferences locally on EKS GPU nodes using vLLM to comply with CBUAE data residency guidelines.
	•	ADR-002 (Event-Driven Temporal Pattern): Decouple monolithic ledger tables into distinct schemas, orchestrating states over Kafka (temporal-intent-events) with compensation rollbacks.
	•	ADR-003 (OAuth 2.1 & DPoP Token-Binding): Secure Open Banking gateways using mTLS client certificates and cryptographically bound access tokens to prevent replay hijack attacks.
	•	ADR-004 (Zero-Trust Log Sanitization): Intercept diagnostic output streams in real-time to replace plaintext PII (IBANs, Customer IDs) and PAN tokens with secure masked tags.
3. BDAT ENTERPRISE ARCHITECTURE DOMAIN MAPPINGS
3.1 Business Architecture & Scrum Execution
	•	Requirements Ingestion: Managed by app/components/confluence_ingest.py. It calls the Confluence API (via doc_connector.py) to retrieve XHTML storage formats of PRD templates.
	•	Blueprint Creator Mapping: Agent 1 (agent1_ingestion.py) parses custom metadata form tables on Confluence pages to establish BIAN logical boundaries (Consumer Account and Card Authorization).
	•	Comala Workflows Approval: Approvals are tracked via jira_connector.py.
	•	Scrum ceremonies in the SDLC:
	•	Planning: Agent 1 checks for previous capability reuse in Jama before sprint commits.
	•	Standup: Agent 3 scans active branch commits, failing builds upon compliance violations.
	•	Demo: Agent 5 executes Semantic Triangle checks to prove to architects that the running code matches specifications.
	•	Retro: Agent 5 computes anonymized DORA metrics while debt_calculator.py compiles Technical Debt Indexes (TDI).
3.2 Data Architecture
	•	Database-per-service: Migrated using scripts in data/migrations/, isolating accounts (tbl_accounts) and cards (tbl_cards) databases.
	•	Distributed State: Managed by app/services/saga_orchestrator.py over the temporal-intent-events Kafka topic.
	•	Vector Rules Database: Seeding is handled by scripts/seed_pgvector.py and data/seed_regulations.sql. Similarity matching is executed via tools/vector_search.py against pgvector.
	•	Masking & Sanitization: Implemented in app/security/pii_masker.py and app/security/log_sanitizer.py.
3.3 Application Architecture
	•	Low-Level API Specs: Handled in app/main.py and app/models.py, exposing secure endpoints:
	•	GET /api/v1/accounts/{accountId}/balance
	•	POST /api/v1/cards/authorize
	•	Agentive Containers: Java/Spring Boot equivalents are compiled and deployed using app/Dockerfile.
	•	Semantic Triangle Check: Executed by app/evaluation/triangle_validator.py on pull-requests to verify code-to-design alignment.
3.4 Technology Architecture
	•	Deployment & Isolation: Deployed on AWS EKS with network-policies isolating namespaces. Local vLLM LLaMA models are hosted on GPU nodes.
	•	Kafka MSK Event Bus: Configured via app/config.py and managed asynchronously across core topics (ingestion, lld, compliance, temporal).
	•	API Security & Token Binding: Handled by app/security/cde_verifier.py, enforcing DPoP signature validations on all CDE gateway connections.
4. SOFTWARE AG ALFABET & CMDB INTEGRATION
	•	API-driven Updates: Agent 4 (agent4_governor.py) utilizes tools/alfabet_client.py to trigger automated logical model updates in the Software AG Alfabet repository upon successful ArgoCD syncs.
	•	CMDB CDC Synchronization: The background daemon scripts/cmdb_cdc_listener.py consumes physical change events over the cmdb-cdc-updates Kafka topic, automatically triggering the Semantic Triangle validator to detect and alert architects of logical-to-physical drift.

End of Master Codex [X] Approved by RedBank Enterprise Architecture CAB [X] Approved by CISO Office and SRE Chapter Lead
