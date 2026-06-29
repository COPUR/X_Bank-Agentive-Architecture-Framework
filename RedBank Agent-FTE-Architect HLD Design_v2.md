REDBANK AGENT-FTE-ARCHITECT V2 HIGH-LEVEL DESIGN (HLD)
Sovereign AI Harness, Cost Estimating, & Automated Architectural Approvals
Document Ref: RB-EAF-V2-HLD
Classification: RESTRICTED - ENTERPRISE ARCHITECTURE DESIGN
Author: Principal Enterprise Architect, RedBank
Date: June 28, 2026
Version: 2.0

1. EXECUTIVE SUMMARY & PHILOSOPHY
This High-Level Design (HLD) specifies the production-ready implementation of the RedBank Agent-Native Architecture Framework (Agent-FTE-Architect). Built on the "Sandwich Pattern" architecture and mapped directly to the Spotify Organizational Model, this framework serves as a secure, sovereign AI Agent Harness wrapping localized AI compute engines.

The codebase implementation is structured under the standard BDAT (Business, Data, Application, Technology) domains and features automated Cost Estimators, Project Planners, and Procedural Approval Gates to govern RedBank's cloud modernization and core banking ledger decompositions.

2. SYSTEM COST & INFRASTRUCTURE footprint ESTIMATION
Operating a secure, compliant AI architecture within a highly regulated LFI like RedBank requires completely localized, high-memory GPU node pools to satisfy CBUAE Data Residency and Sovereignty guidelines. Below is the precise monthly and annual infrastructure cost estimation for running our production-grade framework inside our private VPC:

	•	
Infrastructure Component
Configuration Specifications
Estimated Monthly Cost (USD)
Estimated Annual Cost (USD)
EKS GPU Inference Nodes
2x AWS g5.12xlarge instances (NVIDIA A10G, 96GB VRAM total) running vLLM for localized LLaMA-3-70B model execution.
$8,328.00
$99,936.00
EKS CPU Agent Nodes
3x AWS m6i.xlarge instances running our five agent microservices, entry gateways, security sanitizers, and tracers.
$552.00
$6,624.00
Event Messaging Backbone
AWS MSK (Managed Streaming for Kafka) - 3-broker kafka.m5.large cluster for transactional Temporal messaging.
$350.00
$4,200.00
Sovereign Database Tier
AWS RDS PostgreSQL Multi-AZ db.m6g.xlarge instance (handling pgvector and transaction states).
$720.00
$8,640.00
Observability & Ingress
AWS Secrets Manager, CloudWatch SRE logging targets, and Application Load Balancer gateways.
$470.00
$5,640.00
TOTAL ESTIMATED COST
Enterprise Private VPC Deployment
$10,420.00
$125,040.00
*Note: Infrastructure costs are optimized using vLLM context caching to prevent runaway model execution costs.*

3. PROJECT PLANNER & COST ESTIMATOR MODULES
To prevent runaway cloud expenditures during modular legacy core decompositions, our Cognitive Orchestration Harness implements a programmatic Cost Estimator and Project Planner engine at the API Gateway layer:

3.1 The Project Planner Engine
When a product owner proposes an architectural project in Confluence, the Project Planner parses the document structure to automatically decompose the requirements into discrete, machine-readable tasks (REQ-001 through REQ-007). It maps these tasks to standard engineering epics in Jira and schedules them into automated, risk-free sandbox run loops.

3.2 The Cost Estimator Engine
For every proposed low-level design (LLD), the Cost Estimator programmatically simulates its production footprint:

	•	1. Ingress/Egress Footprint: Estimates transactional volume and API request counts.
	•	2. Storage Footprint: Analyzes target table sizes, indexes, and historical transaction logs.
	•	3. Compute Footprint: Calculates necessary EKS pod allocations and GPU token consumptions.
	•	4. SLA Compliance: If the estimated cost of the newly proposed microservice exceeds the business vertical's monthly budget allocation by more than 15%, the estimator triggers a compliance warning, halts the build pipeline, and routes the project to the Chief FinOps officer for manual review.

4. PROCEDURAL APPROVAL PROCESS (MEETINGS & WORKFLOWS)
The system enforces a strict, gate-controlled procedural approval process mapping our standard agile ceremonies (Scrum) directly to the TOGAF ADM lifecycle:

[State 0: Ingestion] ──► [State 1: Context Assembly & Costing] ──► [State 2: Compliance Scoper]
(Confluence PRD Webhook)   (GraphQL CMDB & Cost Estimator runs)      (pgvector & local LLM Scan)
                                                                             │ (If Valid)
                                                                             ▼
[State 5: CDC Alignment] ◄── [State 4: Slides & GitOps Sync] ◄── [State 3: IdP Consensus]
(Continuous Drift Check)      (Automated Slides / ArgoCD)         (RBAC multi-factor sign-off)
	•	1. State 0: [Jira/Confluence Ingestion] (ADM Phase A - Architecture Vision):
	•	Meeting Integration: Sprint Planning.
	•	Action: Approved Confluence PRDs (Space Templates using Comala Workflows and Blueprint Creator) trigger a Jira webhook. Agent 1 parses requirements into JSON context blocks and checks Jama Software for previous capability reuse.
	•	2. State 1: [Context Assembly & Costing] (ADM Phase B-D - BDAT Architecture):
	•	Action: Agent 2 queries CMDB GraphQL APIs to map target relational database topologies and API routes. The Cost Estimator runs an infrastructure-cost simulation.
	•	3. State 2: [Regulatory & Cost Verification] (ADM Phase G - Governance):
	•	Meeting Integration: Daily Standup / CI/CD Scan.
	•	Action: Agent 3 scans LLDs against pgvector rules (CBUAE, PCI, GDPR) and cloud budgets. Minor risks are auto-remediated; critical bypasses (direct CDE database queries without mTLS FAPI2) trigger a pipeline hard-stop, halt GitOps deployment, and compile a PDF Targeted Risk Analysis (TRA) log.
	•	4. State 3: [IdP Consensus] (ADM Phase G - Implementation Governance):
	•	Meeting Integration: CAB / ARB Board Review.
	•	Action: Agent 4 verifies group memberships via Enterprise IdP RBAC (CAB, CISO, SRE). Dispatches SMS/Email notifications. Once electronic sign-offs are captured, the project transitions to "Approved".
	•	5. State 4: [Slides Generation & Sync] (ADM Phase E/F - Solutions & Migration):
	•	Meeting Integration: Sprint Review / Demo.
	•	Action: Agent 4 compiles an 8-slide PowerPoint deck visualizing the before/after architectures and compliance certifications. Approved manifests are synchronized to the ArgoCD EKS GitOps repositories.
	•	6. State 5: [CMDB CDC Reconciliation] (ADM Phase H - Change Management):
	•	Meeting Integration: Sprint Retrospective.
	•	Action: Agent 5 consumes Change Data Capture (CDC) events from the physical CMDB over the cmdb-cdc-updates Kafka topic, automatically running Semantic Triangle checks to identify and alert architects of logical-to-physical drift.

5. C4 ARCHITECTURAL ARTIFACT DIRECTORY
To support automated prompting pipelines and provide complete technical clarity, the full set of High-Level Design (HLD), Low-Level Design (LLD), PlantUML schemas, and rendered SVG vector diagrams have been compiled into our core repository and folder:

	•	Master Design Folder: RedBank EA Framework — Component Breakdown
	•	V2 Target Folder: v2
	•	V2 Codebase Folder: RedBank Agent-Native Architecture Framework v2

5.1 C4 PlantUML Source Files (V2 Codebase)
	•	System Context Source: v2_c4_context.puml
	•	Container Diagram Source: v2_c4_container.puml
	•	Component Diagram Source: v2_c4_component.puml

5.2 Rendered SVG Vector Diagrams (V2 Codebase)
	•	C4 Context Diagram Image: v2_c4_context.svg
	•	C4 Container Diagram Image: v2_c4_container.svg
	•	C4 Component Diagram Image: v2_c4_component.svg


