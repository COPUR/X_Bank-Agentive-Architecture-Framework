Application Architecture - X_Bank V2 Directory Mapping
The BDAT Application Architecture manages our low-level API endpoints, the 5 multi-agent containers, and our automated Semantic Triangle code checks. It maps directly to our directory structure:
1. Low-Level API Specifications
	•	Implementation Code: app/Application.java & app/Entities.java
	•	Mechanism:
	•	app/Application.java defines our core REST and FAPI2-compliant API routes:
	•	GET /api/v1/accounts/{accountId}/balance (for account balance queries over mTLS).
	•	POST /api/v1/cards/authorize (for secure, token-based card authorizations inside the isolated CDE).
	•	app/Entities.java defines the Jackson JSON schemas representing input and output payloads (e.g., DPoP signature models, Cert-hash validations, and JSON schemas).
2. The 5 Multi-Agent Container Services
Our five specialized agent personas correspond to microservice container definitions running on AWS EKS:

	•	Agent 1 (Ingestion & Context Collector): Implemented in app/agents/Agent1Ingestion.java. Polls Jira and processes Confluence XHTML.
	•	Agent 2 (Infrastructure & DB Topology Mapper): Implemented in app/agents/Agent2Topographer.java. Queries CMDB GraphQL and synthesizes low-level design schemas.
	•	Agent 3 (Compliance Scoper Gate): Implemented in app/agents/Agent3Scoper.java. Intercepts designs, audits them against pgvector rules, and triggers remediations or hard-stops.
	•	Agent 4 (Governance Facilitator): Implemented in app/agents/Agent4Governor.java. Connects to Enterprise IdP groups and compiles CAB/ARB board slides.
	•	Agent 5 (SWE Chapter Auditor): Implemented in app/agents/Agent5Dora.java. Performs Semantic Triangle audits and calculates anonymized DORA metrics.
3. The Semantic Triangle Code Reconciliation Check
	•	Implementation Code: app/evaluation/TriangleValidator.java & app/evaluation/QualityGates.java
	•	Mechanism:
	•	The TriangleValidator.java component programmatically compares the running source code (GitHub) with the approved logical architecture models (HLD/LLD) and Confluence requirements (PRD).
	•	The QualityGates.java component acts as a blocking check inside our CI/CD workflow, preventing ArgoCD from sync-deploying code to Kubernetes EKS if any specification drift is detected.

