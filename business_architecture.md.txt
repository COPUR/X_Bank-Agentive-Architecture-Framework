Business Architecture & Scrum Execution - RedBank V2 Directory Mapping
Within the decoupled Business Architecture of RedBank, the agile planning lifecycle, BIAN domain alignments, and Scrum ceremonies map directly to the codebase components of our redbank-agentive-architecture-framework-v2 structure:
1. Requirements Ingestion & Mapping (The Scrum Backlog)
	•	Confluence Space Templates & PRDs: Handled by app/components/confluence_ingest.py. It calls the Confluence API (via app/agents/tools/doc_connector.py) to fetch XHTML storage formats.
	•	Blueprint Creator App Metadata: Read by app/agents/agent1_ingestion.py. It parses custom field tables in the PRD, converting structural metadata into a machine-readable JSON schema.
	•	Comala Workflows Approvals: Listened to by app/agents/tools/jira_connector.py. It captures transition webhook triggers indicating a page has moved to Approved with appropriate signatures.
	•	Jira Backlog Generation: Handled by app/agents/tools/jira_connector.py. It programmatically generates stories, epics, and tasks based on Confluence tables, putting them directly into the engineering backlog.
	•	BIAN Domain Alignment: Executed by app/agents/agent1_ingestion.py. It maps logical requirements to standard BIAN Consumer Account and Card Authorization boundaries.
2. Scrum Ceremonies in the SDLC & ADM Cycle
	•	Sprint Planning (ADM Phase A/B/C/D):
	•	Implementation Code: app/agents/agent1_ingestion.py.
	•	Mechanism: Agent 1 checks for previous capability reuse (via app/agents/tools/doc_connector.py querying our central portfolio) before sprint commitments to prevent redundant engineering.
	•	Daily Standup (ADM Phase G - Governance):
	•	Implementation Code: app/agents/agent3_scoper.py.
	•	Mechanism: Scans active developer commits in the CI/CD pipeline. Any unauthenticated route to the CDE triggers a hard-stop via app/evaluation/quality_gates.py, instantly notifying developers of a compliance breach.
	•	Sprint Review & Demo (ADM Phase E/F - Solutions & Migration):
	•	Implementation Code: app/evaluation/triangle_validator.py.
	•	Mechanism: Evaluates whether the physical running code aligns perfectly with our Confluence specifications (Semantic Triangle Check) before demonstrating it to business verticals.
	•	Sprint Retrospective (ADM Phase H - Change Management):
	•	Implementation Code: app/agents/agent5_dora.py & app/observability/debt_calculator.py.
	•	Mechanism: Agent 5 computes anonymized DORA metrics and engineering bottlenecks (wait times) while the debt_calculator.py compiles Technical Debt Indexes (TDI) to feed refactoring cards into the next sprint backlog.

