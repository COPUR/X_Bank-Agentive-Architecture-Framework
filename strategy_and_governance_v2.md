Strategy, Governance & Roadmaps - RedBank V2 Directory Mapping
The Strategy & Governance domain governs our strategic roadmap, Architectural Decision Records (ADRs), Tech Radar, Technical Debt Index (TDI) calculations, and enterprise integrations. It maps directly to our directory structure:
1. Strategic Architectural Decision Records (ADRs)
	•	Implementation Code: docs/architecture_codex.md & app/prompts/Templates.java
	•	Mechanism:
	•	docs/architecture_codex.md documents our active ADRs (ADR-001: Localized AI, ADR-002: Temporal Orchestration, ADR-003: FAPI2, ADR-004: Zero-Trust Log Sanitization).
	•	app/prompts/Templates.java embeds these ADR boundaries directly into the system prompting parameters of our five agents, ensuring that all generated low-level code or designs automatically comply with our strategic decisions.
2. RedBank Enterprise Tech Radar
	•	Implementation Code: app/agents/Agent5Dora.java
	•	Mechanism: Agent 5 monitors development repositories and physical environments. Any newly detected technologies or libraries are evaluated and logged as "Assess", "Hold", "Trial", or "Adopt" entries inside our central portfolio, maintaining standard TOGAF Architecture Change Management.
3. Technical Debt Index & Strategic Decision Helper
	•	Implementation Code: app/observability/DebtCalculator.java
	•	Mechanism:
	•	DebtCalculator.java programmatically scans codebase complexity, coupling, and unmasked logging leaks. It computes a Technical Debt Index (TDI) to automatically generate refactoring backlogs in Jira.
	•	Our weighted alternative-assessment matrices (evaluating factors like sovereignty compliance, TCO, and API customization) are implemented inside our automated decision-maker helper, recommending optimal paths.
4. Software AG Alfabet & CMDB CDC Integration
	•	Implementation Code: app/agents/tools/AlfabetClient.java & scripts/CmdbCdcListener.java
	•	Mechanism:
	•	app/agents/tools/AlfabetClient.java automates REST POST updates to our central portfolio, keeping our Software AG Alfabet logical architectural model synchronized with actual deployments.
	•	CmdbCdcListener.java runs as a continuous background listener on EKS, consuming Change Data Capture (CDC) events from our CMDB over the cmdb-cdc-updates Kafka topic. Any physical drift instantly triggers a Semantic Triangle reconciliation.
5. Chapter Developer Performance & Psychological Safety
	•	Implementation Code: app/observability/TokenTracker.java & app/agents/Agent5Dora.java
	•	Mechanism: To prevent developer burnout and protect psychological safety, all developer-level VCS metrics are fully anonymized at the chapter/tribe layer. Summaries focus exclusively on macro bottlenecks (such as CI/CD runner wait times) to drive systematic infrastructure improvements.

