Application Architecture - X_Bank V2 Directory Mapping
The BDAT Application Architecture manages our low-level API endpoints, the 5 multi-agent containers, and our automated Semantic Triangle code checks. It maps directly to our directory structure:
1. Low-Level API Specifications
	•	Implementation Code: app/Application.java & app/Entities.java
	•	Mechanism:
	•	app/Application.java defines our core REST and FAPI2-compliant API routes:
	•	GET /api/v1/accounts/{accountId}/balance (for account balance queries over mTLS).
	•	POST /api/v1/cards/authorize (for secure, token-based card authorizations inside the isolated CDE).
	•	app/Entities.java defines the Jackson JSON schemas representing input and output payloads (e.g., DPoP signature models, Cert-hash validations, and JSON schemas).
2. The Dynamic Tool Domains (Managed Harness Architecture)
Our five specialized agent personas have been refactored into distinct Domain-Driven Design (DDD) bounded contexts, acting as Toolsets for the dynamic `AgentHarness`:

	•	Ingestion Domain Context: Implemented in src/main/java/com/xbank/harness/ingestion/. Tools to poll Jira and process Confluence XHTML into structured requirements.
	•	Topology Domain Context: Implemented in src/main/java/com/xbank/harness/topology/. Tools like `VectorSearchService` and CMDB integrators to synthesize low-level design schemas.
	•	Compliance Domain Context: Implemented in src/main/java/com/xbank/harness/compliance/. Tools to intercept designs, audit against pgvector rules, and trigger remediations or hard-stops.
	•	Governance Domain Context: Implemented in src/main/java/com/xbank/harness/governance/. Tools to connect to Enterprise IdP groups and compile CAB/ARB board slides (`JiraConnector`).
	•	Quality Domain Context: Implemented in src/main/java/com/xbank/harness/quality/. Tools to perform Semantic Triangle audits and calculate anonymized DORA metrics.
3. The Semantic Triangle Code Reconciliation Check
	•	Implementation Code: app/evaluation/TriangleValidator.java & app/evaluation/QualityGates.java
	•	Mechanism:
	•	The TriangleValidator.java component programmatically compares the running source code (GitHub) with the approved logical architecture models (HLD/LLD) and Confluence requirements (PRD).
	•	The QualityGates.java component acts as a blocking check inside our CI/CD workflow, preventing ArgoCD from sync-deploying code to Kubernetes EKS if any specification drift is detected.

