Project Requirement Document (PRD)
Project Name: RedBank Agent-Native Architecture Framework (Agent-FTE-Architect) Document Owner: Lead Enterprise Architect, RedBank Version: 1.0
1. Executive Overview
1.1 Objective
The RedBank Agent-Native Architecture Framework (Agent-FTE-Architect) is an enterprise-grade AI-agentive orchestration system. Its objective is to automate the end-to-end (E2E) Software Development Life Cycle (SDLC) architectural design, review, regulatory verification, post-development code reconciliation, and engineering velocity metrics collection under strict regulatory standards (PCI-DSS v4, UAECB Open Finance Circular 3/2025, and GDPR).

It acts as a secure, highly governed "Architectural Harness" wrapping localized, stateless AI compute models, transforming regulatory controls into programmatic deployment gates.
1.2 Target Audience / Users
	•	Enterprise & Solutions Architects: Maintain and query the global blueprint metadata, establish Bounded Specialization (Role Boundaries), and evaluate system integrations.
	•	Security & Compliance Officers (CISO Office): Configure compliance boundaries, inspect Targeted Risk Analysis (TRA) logs, and audit cryptographic data-masking policies.
	•	DevOps & SRE Leads: Monitor deployment velocity, verify source code alignments via semantic triangle checks, and analyze chapter-level friction without compromising developer psychological safety.
	•	Autonomous Agentic Runners: Consume structured, machine-readable task listings to execute automated, verified GitOps sync deployments.
1.3 Scope Definition
In-Scope:

	•	Atlassian Ingestion (State 0): Ingesting approved Confluence PRDs (Space Templates with Comala Workflows and Blueprint Creator) and generating corresponding Jira backlogs.
	•	Logical Mapping & Topology (State 1): Extracting HLD/LLD features, querying GraphQL CMDB APIs, and mapping logical components to BIAN service domain boundaries.
	•	Regulatory Validation & Hard-Stops (State 2): Performing pgvector semantic audit scans; executing auto-remediation (cryptographic log masking); triggering build failures and TRAs for CDE isolation bypasses.
	•	Stakeholder Facilitation (State 3): Coordinating voting and sign-offs via mTLS, OAuth 2.1 + DPoP, and IdP RBAC roles.
	•	Workspace Compilation & GitOps Sync (State 4): Generating board-ready slides and pushing validated configurations to ArgoCD Git repositories.
	•	Code Reconciliation (State 5): Running "Semantic Triangle Checks" between running code and specifications, alongside anonymized DORA telemetry.

Out-of-Scope (Non-Goals):

	•	Complete, high-risk, "big bang" rewrite of RedBank's legacy monolithic core banking databases (handled safely via the Strangler Fig Pattern).
	•	Direct routing of any internal system metadata, IP topologies, or PII outside the bank's sovereign private VPC borders (prohibiting the use of public SaaS APIs).
2. Functional Requirements
Detail the exact behaviors, features, and workflows the system must support.
2.1 Core Features
	•	Feature 1: Autonomous Ingestion (Agent 1): Continuously monitors the approved Jira stream, pulls Confluence storage-format HLD/LLD documents, and aligns requirements with BIAN Service Domains.
	•	Specification: Must parse incoming Jira ticketing payloads and Confluence XHTML pages into structured, cleansed JSON context blocks.
	•	Feature 2: Low-Level Topology Synthesis (Agent 2): Query the CMDB GraphQL API to generate live operational application topologies and relational database schemas.
	•	Specification: Must output highly technical LLD specifications defining precise API paths (e.g., balance and card auth routes), entity schemas, and event-driven Temporal orchestrations.
	•	Feature 3: Compliance Validation & Remediation (Agent 3): Serves as the post-processing compliance validator. Intercepts LLD outputs and evaluates payload metadata against localized vector databases (pgvector/Qdrant) holding compliance rules.
	•	Specification: Must trigger automated Spring Boot logging sanitization filters for minor PII risks and execute a pipeline hard-stop with automated TRA generation for CDE boundary bypasses.
	•	Feature 4: Stakeholder Sign-Off Facilitation (Agent 4): Coordinate approvals and generate governance presentations.
	•	Specification: Must authenticate voter groups (CAB, CISO, SRE) via OIDC RBAC and dynamically generate Google Slides representations for board review.
	•	Feature 5: Code Audit & SRE Chapter Telemetry (Agent 5): Audits implementation source code against designs and computes engineering velocity metrics.
	•	Specification: Must execute a "Semantic Triangle Check" to reconcile running code against HLDs, and calculate anonymized, team-level DORA metrics (Deployment Frequency, Lead Time, MTTR, Change Failure Rate).
2.2 User Stories & Workflows
	•	User Story 1: As an Enterprise Architect, I need to establish a live, data-driven "digital twin" of our technology landscape in Confluence, so that I can prevent third-party engineering vendors from building redundant or tightly coupled services.
	•	User Story 2: As the CISO Compliance Officer, I need the architectural pipeline to automatically trigger a compliance hard-stop on any CDE direct database access bypass, so that we prevent unauthorized lateral transit and satisfy PCI-DSS v4 requirement 1.2.
	•	User Story 3: As an Autonomous Agent Runner, I must programmatically parse the requirements task decomposition schema, before executing localized terraform scripts in isolated sandbox runners.
3. Technical & System Architecture
3.1 Infrastructure Constraints
	•	Execution Environment: Containerized microservices deployed on AWS EKS, utilizing localized LLMs (Llama-3-70B) on GPU nodes to preserve data residency.
	•	Performance Requirements: Sub-200ms latency on core Open Banking REST endpoints; Kafka event-delivery queues must support a throughput of at least 10k concurrent messages.
	•	Security & Compliance: End-to-end encryption in transit (TLS 1.3 / mTLS) and rest. All tokens must be bound to DPoP headers. Absolute data isolation inside our sovereign VPC.
3.2 Data Processing & State Management
	•	Inputs: XHTML storage format from Confluence pages, GraphQL payloads from CMDB APIs, git commit triggers from GitHub webhooks, and REST JSON schemas.
	•	Outputs: Sanitized JSON design schemas, automated PR review comments, PDF TRA reports, and Google Slides presentations.
	•	Dependencies: Apache Kafka (AWS MSK) for Intent-Driven Multi-Agent Workflows (Temporal + Kafka); pgvector PostgreSQL instances; Enterprise OIDC Identity Providers; Google Workspace APIs.
4. Task Decomposition (Machine-Readable Mapping)
For use in loop-based architectures or automated task runners. Break down requirements into discrete, trackable JSON-ready tasks.

	•	Task ID: REQ-001
	•	Action: Poll approved Jira ticketing events and pull XHTML storage format from Confluence Page IDs.
	•	Required Context: confluence_page_data.json and OAuth OIDC authentication token.
	•	Task ID: REQ-002
	•	Action: Extract logical database tables, API routes, and schema relations.
	•	Required Context: XHTML page payload and BIAN Service Domain reference files.
	•	Task ID: REQ-003
	•	Action: Validate design schemas against the pgvector active regulation database.
	•	Required Context: Generated LLD schema and pgvector connection metadata.
	•	Task ID: REQ-004
	•	Action: Execute log sanitization auto-remediation by injecting the Spring Boot logging filter.
	•	Required Context: Microservice build file (pom.xml) and repository dependency maps.
	•	Task ID: REQ-005
	•	Action: Generate a Targeted Risk Analysis (TRA) report for CDE boundary bypasses and halt the GitOps release pipeline.
	•	Required Context: Identified non-compliance vectors, build log variables, and the standard TRA template.
	•	Task ID: REQ-006
	•	Action: Verify RBAC group memberships via Enterprise IdP and dispatch SMS/Email approvals.
	•	Required Context: IdP voter registries, phone/email contact directories, and active Kafka approval streams.
	•	Task ID: REQ-007
	•	Action: Execute Semantic Triangle checks between running code, HLDs, and BRDs, and calculate team-level DORA metrics.
	•	Required Context: GitHub source tree, Confluence page specs, Jira history logs, and ArgoCD deploy logs.
5. Verification & Acceptance Criteria
Define the precise conditions required to consider the requirements fully satisfied.
5.1 Success Metrics
	•	[ ] BIAN mapping accuracy meets 100% agreement when checked against standard service domains.
	•	[ ] Auto-remediations successfully inject sanitization classes into the build file without compile-time errors.
	•	[ ] Anonymized DORA metrics accurately calculate averages without exposing individual developer names, satisfying psychological safety rules.
	•	[ ] The Requirements Traceability Matrix (RTM) successfully traces 100% of Confluence PRD requirements down to Jama verification runs and running code endpoints.
5.2 Fallback & Error Handling
	•	Failure State: If any agent validation step fails, experiences a network timeout (exceeding 10s), or encounters a critical compliance violation...
	•	Required Resolution: ...the Cognitive Orchestration Harness must halt the GitOps pipeline, trigger a webhook to lock the ArgoCD branch, write an error trace to the Apache Kafka audit stream (arch-governance-audit), and restore the target environment state to the last verified stable baseline.

