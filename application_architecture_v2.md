# Application Architecture - X_Bank V2 Directory Mapping

The BDAT Application Architecture manages our low-level API endpoints, the multi-agent containers, and our automated Semantic Triangle code checks. It maps directly to our directory structure and follows the core design principle: **Agent = Model + Harness + Bounded Specialization**.

## 1. Low-Level API Specifications
*   **Implementation Code**: `app/Application.java` & `app/Entities.java`
*   **Mechanism**:
    *   `app/Application.java` defines our core REST and FAPI2-compliant API routes:
        *   `GET /api/v1/accounts/{accountId}/balance` (for account balance queries over mTLS).
        *   `POST /api/v1/cards/authorize` (for secure, token-based card authorizations inside the isolated CDE).
    *   `app/Entities.java` defines the Jackson JSON schemas representing input and output payloads (e.g., DPoP signature models, Cert-hash validations, and JSON schemas).

## 2. Kong Agent Gateway & Security (State 0)
*   **Mechanism**: Acts as the ingress controller. Webhooks hit the Kong Agent Gateway where the Security LLM layer scans for prompt injection. The Semantic Cache is checked here to bypass full execution if this is a duplicated intent.

## 3. The Dynamic Tool Domains (Managed Harness Architecture)
Our five specialized agent personas are mapped directly to X_Bank's organizational topology (Bounded Specialization) and act as Toolsets for the dynamic `AgentHarness`:

*   **Agent 1 (Ingestion & Context Collector - Squad Tier)**: Implemented in `src/main/java/com/xbank/harness/ingestion/`. Tools to poll Jira backlog streams and extract HLD/LLD specs from Confluence XHTML.
*   **Agent 2 (Infrastructure & Topology - Squad Tier)**: Implemented in `src/main/java/com/xbank/harness/topology/`. Translates logical contexts into LLD schema definitions, mapping active database topologies using `VectorSearchService` and CMDB GraphQL APIs.
*   **Agent 3 (Regulatory Compliance Gate - Guild Tier)**: Implemented in `src/main/java/com/xbank/harness/compliance/`. Intercepts schemas, executes graceful degradation over vector embeddings (pgvector/Qdrant) for CBUAE/PCI-DSS v4 rules, and employs memory write-filters.
*   **Agent 4 (HITL Governance Facilitator - Tribe Tier)**: Implemented in `src/main/java/com/xbank/harness/governance/`. Coordinates stakeholders via Enterprise IdP (RBAC) and compiles CAB/ARB board slides (`JiraConnector`).
*   **Agent 5 (Cognitive Quality Auditor - Chapter Tier)**: Implemented in `src/main/java/com/xbank/harness/quality/`. Performs Semantic Triangle checks on running code and computes anonymized DORA telemetry.

## 4. The Semantic Triangle Code Reconciliation Check
*   **Implementation Code**: `app/evaluation/TriangleValidator.java` & `app/evaluation/QualityGates.java`
*   **Mechanism**:
    *   The `TriangleValidator.java` programmatically compares the running source code (GitHub) with the approved logical architecture models (HLD/LLD) and Confluence requirements (PRD).
    *   The `QualityGates.java` component acts as a blocking check inside our CI/CD workflow, preventing ArgoCD from sync-deploying code to Kubernetes EKS if any specification drift is detected.
