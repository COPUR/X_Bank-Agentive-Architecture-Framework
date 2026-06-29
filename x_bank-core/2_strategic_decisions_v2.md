Part 2: Strategic Decisions - X_Bank Agent-Native Architecture Framework
2.1 ADR-001: Localized AI Orchestration for Data Sovereignty
	•	Status: APPROVED
	•	Context: Under the Central Bank of the UAE (CBUAE) Cloud Computing Regulation, sensitive metadata, infrastructure IP locations, or system boundaries must not reside outside the sovereign borders of the UAE.
	•	Decision: Run all agent reasoning models locally inside X_Bank's private VPC on AWS EKS using Hugging Face pipelines and containerized vLLM endpoints. External model calls are strictly blocked.
	•	Consequences: Complete compliance with data sovereignty regulations. Requires dedicated GPU node pools on AWS EKS, increasing infrastructure costs.
2.2 ADR-002: Event-Driven Transaction Temporal Pattern
	•	Status: APPROVED
	•	Context: Decoupling the legacy cards and account ledger into distributed services breaks direct database foreign keys, introducing potential data inconsistency risks.
	•	Decision: Implement the Intent-Driven Multi-Agent Workflows (Temporal + Kafka) managed via Apache Kafka (AWS MSK). All state changes are broadcast as events, with dedicated compensating rollbacks to maintain eventual consistency.
	•	Consequences: Avoids heavyweight distributed locking (2PC), preserving database throughput. Requires strict idempotency checks on ledger tables.
2.3 ADR-003: OAuth 2.1 & FAPI2 Compliance for Open Banking
	•	Status: APPROVED
	•	Context: Under UAECB Open Finance Circular 3/2025, APIs must follow financial-grade security patterns.
	•	Decision: Enforce OAuth 2.1 authorization code flow with PKCE, coupled with mTLS and DPoP (Demonstrating Proof-of-Possession).
	•	Consequences: Complete protection against token leakage and unauthorized transit.
2.4 ADR-004: Zero-Trust Log Sanitization
	•	Status: APPROVED
	•	Context: GDPR Article 32 and local UAE data protection regulations mandate that no plaintext PII or CHD must exist in diagnostic logs.
	•	Decision: Deploy a mandatory, automated Log Sanitization Filter at the Spring Boot runtime layer.
	•	Consequences: Mitigates compliance risks. Adds a minor parsing overhead to logging targets.

