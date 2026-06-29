High-Level Design (HLD) Document - X_Bank Agent-Native Architecture Framework
Document Identifier: RB-EAF-2026-HLD Classification: RESTRICTED - ENTERPRISE ARCHITECTURE Target Audience: CAB/ARB Board, Solutions Architects, DevSecOps Leads Version: 1.0


## 1. Executive Summary & Core Philosophy
**Mission**: Automate E2E SDLC design, regulatory review, and code reconciliation under CBUAE, PCI-DSS, and GDPR standards.
### 1.1 The Harness Engineering Principle
**Agent = Model + Harness + Bounded Specialization**
LLMs act as stateless compute, secured by Kong, Kafka, and PostgreSQL.
### 1.2 The LFI-Sandwich Architecture Tiers
	•	The Upper Layer: Business and product requirements established in Confluence and Jira.
	•	The Middle Layer: Logical design and low-level specifications aligned with the BIAN framework.
	•	The Lower Layer: Comprehensive post-processing compliance validation, automated remediation, and code auditing.



### 1.3 Deployment Tiers & Network Flow Matrix

| Tier Zone | Network Placement | Core Technologies | Function |
| :--- | :--- | :--- | :--- |
| **Ingestion Edge** | DMZ (Public facing) | Kong Gateway, Security LLM | Validates JWTs, intercepts Prompt Injections via Streaming DLP. |
| **Orchestration Core** | Private VPC | Temporal Enterprise, AWS MSK | Maintains distributed transaction state and async worker events. |
| **Secure Compute** | EKS GPU Nodes | vLLM (LLaMA-3), gVisor/WasmEdge | Executes strict, sandboxed LLM generations isolated from internet. |
| **Distributed Data & Cache** | Active-Active StatefulSets | HA PostgreSQL (`pgvector`), HA Redis | Caches semantic similarities to bypass LLM inference (TTFT mitigation). Clustered for active-active multi-zone redundancy. |


## 2. System Context (C4 Level 1)
The System Context diagram describes how the Cognitive Orchestration Harness interacts with users (Architects, CISO, SREs) and external enterprise systems.
2.1 Context Diagram
	•	Source PUML File: c4_context.puml
	•	Rendered Diagram: diagrams/c4_context.svg

![C4 Context Diagram](c4_context_v2.svg)

[Enterprise Architect] ────► [Confluence (Approved PRDs)] ────► [Jira Backlog] ───► [Jama Traceability]

         │                                                            │

         └────────────────────────────────────────────────────────────┼──────────┐

                                                                      ▼          ▼

[Compliance/CISO] ◄───────────────────────────────────────► [Cognitive Orchestration Harness]

                                                                      │

                                                                      ▼

[DevOps / SRE] ◄────────────────────────────────────────── [GitHub / ArgoCD GitOps]


## 3. Container Architecture (C4 Level 2)
The Container diagram decomposes the Cognitive Orchestration Harness into its core container modules, event buses, databases, and localized LLM compute engines.
3.1 Container Diagram
	•	Source PUML File: c4_container.puml
	•	Rendered Diagram: diagrams/c4_container.svg

![C4 Container Diagram](c4_container_v2.svg)
3.2 Containers Description
	•	Agent Harness Core (Managed Harness): A generic orchestration runtime (`AgentHarness.java`) that drives autonomous execution via dynamic sequencing.
	•	Memory & Configuration Management: Cross-session `PersistentMemoryManager` and API-level `ConfigurationManager` to hot-swap logic.
	•	Tool Domains (Ingestion, Topology, Compliance, Governance, Quality): Formerly rigid agents, these are now dynamically callable toolsets (e.g., Jira polling, CMDB querying, `pgvector` rule evaluation).
	•	RestLlmProvider: A lightweight, provider-agnostic HTTP client integrating with local vLLM or remote models.
	•	Apache Kafka (AWS MSK): The decoupled messaging backbone routing event streams.
	•	Sovereign LLM (vLLM): Private, localized LLaMA-3-70B compute engine running on AWS EKS GPU nodes inside our trust boundary.


## 4. Component Architecture - Agent 3
The Component diagram models the internal Spring Boot and pgvector components of our regulatory gate (Agent 3).
4.1 Component Diagram
	•	Source PUML File: c4_component.puml

![C4 Component Diagram](c4_component_v2.svg)

	•	Rendered Diagram: diagrams/c4_component.svg
4.2 Component Descriptions
	•	Kafka Consumer: Consumes LLD schemas from the lld-generation-topic topic.
	•	Vector Rule Scanner: Performs cosine-similarity searches against the pgvector active rules database.
	•	Regulatory Validator Core: Orchestrates evaluations and coordinates decisions (Remediate, Hard-Stop, Pass).
	•	Log Sanitizer Remediator: Programmatically injects sanitization dependency libraries into microservice builds.
	•	Compliance Hard-Stop Manager: Halts CI/CD pipelines and locks target ArgoCD branches upon critical violations.
	•	TRA Report Generator: Automatically generates PDF Targeted Risk Analysis reports.
	•	Kafka Publisher: Emits final decisions and alerts to the compliance-alerts topic.


5. Master Data Tier Schemas (PostgreSQL)
5.1 Account Service Schema
```sql
CREATE TABLE tbl_accounts (

    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    account_number VARCHAR(34) UNIQUE NOT NULL, -- IBAN Format

    balance NUMERIC(18,2) NOT NULL DEFAULT 0.00,

    customer_id UUID NOT NULL,

    currency VARCHAR(3) NOT NULL,

    status VARCHAR(10) NOT NULL CHECK (status IN ('ACTIVE', 'SUSPENDED', 'CLOSED'))

);
```
5.2 Card Service Schema (PCI-DSS v4 Isolated CDE)
```sql
CREATE TABLE tbl_cards (

    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    card_token VARCHAR(64) UNIQUE NOT NULL, -- SHA-256 Masked PAN representation

    expiry_date VARCHAR(5) NOT NULL, -- MM/YY

    status VARCHAR(10) NOT NULL DEFAULT 'ACTIVE',

    credit_limit NUMERIC(15,2) NOT NULL DEFAULT 0.00

);
```


6. Dynamic Ralph Workflow Sequence
The system orchestrates intent via the Temporal worker pattern, adhering to the Ralph Architecture's iterative loop with isolated state. 
*For the exhaustive step-by-step API call trace, please refer to the [Temporal Workflow Sequence Diagram](file:///Users/alicopur/Downloads/RedBank%20Agentive-Architecture-Framework%20v2/x_bank-core/sequence_temporal_workflow_v2.puml).*

![Sequence Diagram](sequence_temporal_workflow_v2.svg)
