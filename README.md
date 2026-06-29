# X_Bank Agent-Native Architecture Framework (Version 2.0)

<p align="center">
  <img src="https://img.shields.io/badge/Architecture-TOGAF_ADM-blue" />
  <img src="https://img.shields.io/badge/Engine-Java%20%7C%20Spring_Boot-green" />
  <img src="https://img.shields.io/badge/Compliance-CBUAE_3%2F2025%20%7C%20PCI__DSS_v4-red" />
  <img src="https://img.shields.io/badge/AI-Agentive%20Multi--Agent-purple" />
</p>

Welcome to the **X_Bank Agent-Native Architecture Framework**, a production-grade, highly governed, autonomous architectural system. This repository maps the implementation of an AI-driven Software Development Life Cycle (SDLC), utilizing specialized autonomous agents to ingest product requirements, map logical topologies, enforce strict regulatory guardrails, and generate deployment artifacts.

---

## 🏛 The Core Paradigm: Agent = Model + Harness
At X_Bank, we do not deploy raw Large Language Models (LLMs) into production. Instead, we adhere to the **LFI-Sandwich Architecture**, wrapping stateless LLM compute inside a highly secure and deterministic **Cognitive Orchestration Harness**. 
The equation is simple: **`Agent = Model + Harness + Bounded Specialization`**

![C4 Context Diagram](x_bank-core/c4_context.png)

This ensures that the AI cannot hallucinate outside of strict regulatory boundaries, specifically conforming to:
- **CBUAE Circular 3/2025 (Open Finance)**
- **PCI-DSS v4 (Cardholder Data Environments)**
- **GDPR (Data Privacy & PII)**
- **FAPI2 (Financial-grade API Security)**

---

## 🤖 The 5 Agent Personas
The framework decouples E2E execution into 5 highly bounded, specialized agents. Each agent handles a specific tier of the architectural lifecycle, orchestrated via **Temporal**:

![C4 Container Diagram](x_bank-core/c4_container.png)

1. **Agent 1 (Ingestion / Squad Tier)**: Polls Jira and Confluence Webhooks, mapping product requirements into structured BIAN service domain boundaries.
2. **Agent 2 (Topology / Squad Tier)**: Queries live CMDB GraphQL APIs to map physical infrastructure and generate Low-Level Design (LLD) schemas.
3. **Agent 3 (Compliance Gate / Guild Tier)**: Intercepts generated LLDs, performing RAG against `pgvector` regulatory rules. It acts as a **Cognitive Circuit Breaker**, triggering hard-stops if violations (e.g., unauthenticated CDE ingress) are detected.
4. **Agent 4 (Governance / Tribe Tier)**: Manages Enterprise IdP Role-Based Access Control (RBAC), facilitating mandatory Human-in-the-Loop (HITL) CAB/ARB board approvals.
5. **Agent 5 (Quality & DORA / Chapter Tier)**: Analyzes source code to ensure it matches the generated logical diagrams via **Semantic Triangle Checks**, tracking Workload Amplification and automated Technical Debt Index (TDI) scores.

---

## 🏗 Enterprise Technology Stack
This repository defines the following enterprise infrastructure:
- **Java / Spring Boot**: The core runtime for the Cognitive Orchestration Harness.
- **Kong Agent Gateway**: The ingress routing layer, embedded with a **Security LLM** to block prompt injections before they reach the orchestration layer.
- **Temporal & Kafka (MSK)**: The stateful multi-agent workflow engine orchestrating deterministic event loops between the agents.
- **Redis / Qdrant (Semantic Vector Cache)**: A high-speed cache that maps exact topological intent to bypass costly LLM inference, mitigating Time-To-First-Token (TTFT) latency.
- **gVisor / WasmEdge**: The WebAssembly (WASM) execution environment providing strict memory sandboxing for the agents.

---

## 📂 Code Directory Tree
This repository models the standard Maven/Spring Boot project layout of our autonomous framework.

```text
x_bank-agent-native-architecture-framework-v2/
├── app/
│   ├── Application.java                    # Entrypoint for the Cognitive Orchestration Harness API
│   ├── SecurityConfig.java                 # OIDC/IdP configurations, Kafka brokers
│   ├── Entities.java                       # Jackson JSON schemas and Hibernate JPA entities
│   ├── Dockerfile                          # EKS container definition (gVisor/WasmEdge)
│   ├── build.wasm                          # WebAssembly build target
│   ├── components/                         # Ingestion & Context Collectors
│   │   ├── ConfluenceIngest.java 
│   │   └── CmdbTopographer.java 
│   ├── services/                           # Core Business Logic
│   │   ├── TemporalOrchestrator.java       # Distributed Temporal workflow engine
│   │   ├── RulesEngine.java                # Core TOGAF-aligned rule validator
│   │   └── SlideCompiler.java              # Automated Google Slides layout compiler
│   ├── prompts/                            # Versioned Expert Prompt Templates
│   │   ├── Templates.java                  # Structured prompts for Agents 1-5
│   │   └── Registry.java 
│   ├── agents/                             # Multi-Agent Intel Tiers
│   │   ├── Agent1Ingestion.java 
│   │   ├── Agent2Topographer.java 
│   │   ├── Agent3Scoper.java 
│   │   ├── Agent4Governor.java 
│   │   ├── Agent5Dora.java 
│   │   └── tools/                          # Connector Plugins
│   │       ├── VectorSearchService.java    # pgvector semantic rule queries
│   │       ├── JiraConnector.java 
│   │       ├── ConfluenceConnector.java 
│   │       └── AlfabetClient.java 
│   ├── security/                           # Three-Layer Protection Guardrails
│   │   ├── PiiMaskerFilter.java            # Input mask for customer IBANs & IDs
│   │   ├── LogSanitizer.java               # Runtime filter for diagnostic logs
│   │   └── CdeVerifier.java                # Enforces FAPI2 check boundaries
│   ├── evaluation/                         # Spec Reconciliation & Quality Gates
│   │   ├── TriangleValidator.java          # Semantic Triangle Code Reconciliation Check
│   │   └── QualityGates.java               # CI/CD deployment blockers
│   └── observability/                      # Phase-Level Tracing & telemetry
│       ├── TraceLogger.java 
│       ├── DebtCalculator.java 
│       └── TokenTracker.java 
├── data/                                   # Database Migrations
│   ├── migrations/                         # Flyway schemas
│   └── seed_regulations.sql                # Vector rules database seed values
├── scripts/                                # Maintenance & Pipeline Hooks
│   ├── SeedPgvector.java                   
│   ├── CmdbCdcListener.java                
│   └── Healthcheck.java                    
└── pom.xml                                 # Package dependencies
```

---

## 🚀 Getting Started

### 1. Prerequisites
- **Java 17+ / Maven**
- **Docker & Docker Compose** (For testing local Kafka and PostgreSQL pgvector)
- **Temporal Server** (Running locally or via Cloud)

### 2. Local Spin Up
You can launch the mock architectural environment locally.
```bash
# Start the supporting infrastructure (Kafka, Redis Cache, PostgreSQL pgvector)
docker-compose up -d

# Build the Agentive Harness
mvn clean install

# Run the Application
mvn spring-boot:run
```

---

## 📖 Architectural Diagrams & Specs
For exhaustive technical details regarding our C4 topologies, UML workflows, and state-machine fallbacks, please navigate into the `x_bank-core/` directory:
- **[System Codex](x_bank-core/1_system_codex_v2.md)**
- **[High Level Design (HLD)](x_bank-core/hld_document_v2.md)**
- **[Low Level Design (LLD)](x_bank-core/lld_document_v2.md)**
- **[TOGAF Agile Governance](x_bank-core/8_togaf_agile_radar_and_enterprise_governance_v2.md)**

All generated `.png` and `.svg` architectural diagrams are embedded within these documents for immediate visual reference.
