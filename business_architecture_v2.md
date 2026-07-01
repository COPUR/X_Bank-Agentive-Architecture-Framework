# Business Architecture & Scrum Execution - X_Bank V2 Directory Mapping

Within the decoupled Business Architecture of X_Bank, the agile planning lifecycle, BIAN domain alignments, and Scrum ceremonies map directly to the codebase components of our `x_bank-agent-native-architecture-framework-v2` structure:

## 1. Requirements Ingestion & Mapping (The Scrum Backlog)
*   **Confluence Space Templates & PRDs**: Handled by `src/main/java/com/xbank/harness/ingestion/adapter/out/ConfluenceAdapter.java`.
*   **Blueprint Creator App Metadata**: Read by `src/main/java/com/xbank/harness/ingestion/application/service/IngestionService.java`.
*   **Comala Workflows Approvals**: Listened to by `src/main/java/com/xbank/harness/governance/adapter/in/JiraWebhookAdapter.java`.
*   **Jira Backlog Generation**: Handled by `src/main/java/com/xbank/harness/ingestion/adapter/out/JiraAdapter.java`.
*   **BIAN Domain Alignment**: Executed by `src/main/java/com/xbank/harness/ingestion/application/service/IngestionService.java`. Target success metric is 100% agreement when checked against standard BIAN service domains.

## 2. Scrum Ceremonies in the SDLC & ADM Cycle
*   **Sprint Planning (ADM Phase A/B/C/D)**:
    *   **Mechanism**: The Ingestion Domain checks for previous capability reuse before sprint commitments. Enterprise Architects oversee this to ensure third-party engineering avoids building redundant, tightly-coupled services.
*   **Daily Standup (ADM Phase G - Governance)**:
    *   **Mechanism**: Agent 3 scans active developer commits in the CI/CD pipeline. Any unauthenticated route to the CDE triggers a hard-stop, instantly notifying developers and satisfying PCI-DSS v4 req 1.2 for Security & Compliance Officers (CISO).
*   **Sprint Review & Demo (ADM Phase E/F - Solutions & Migration)**:
    *   **Mechanism**: Agent 5 evaluates whether the physical running code aligns perfectly with our Confluence specifications (Semantic Triangle Check).
*   **Stakeholder Sign-Off Facilitation (Agent 4)**:
    *   **Mechanism**: Agent 4 maps OIDC RBAC roles via the Enterprise IdP and pauses the Temporal workflow. It orchestrates voting and generates Google Slides for mandatory CAB, CISO, and SRE approvals.
*   **Sprint Retrospective (ADM Phase H - Change Management)**:
    *   **Mechanism**: The Quality Domain (Agent 5) computes anonymized DORA metrics and engineering bottlenecks (wait times) to protect developer psychological safety while measuring velocity.
