X_Bank Agent-Native Architecture Framework (Version 2.0)
Production-Grade Code Directory Tree Mapping our AI Harness
This repository represents the production-ready implementation of our high-governance, autonomous architectural system inside X_Bank. It is structured around the modular directory layout of modern, enterprise-grade AI systems, completely decoupling stateless compute models from our security and integration "Harness".
1. Directory Tree Structure
x_bank-agent-native-architecture-framework-v2/

├── app/

│   ├── Application.java                    # Entrypoint for the Cognitive Orchestration Harness API

│   ├── SecurityConfig.java                  # OIDC/IdP configurations, Kafka brokers, and environment variables

│   ├── Entities.java                  # Jackson JSON schemas and Hibernate JPA entities for PostgreSQL

│   ├── Dockerfile                 # EKS container definition (gVisor/WasmEdge)
│   ├── build.wasm                 # WebAssembly build target for isolated agents

│   ├── components/                # Ingestion & Context Collectors

│   │   ├── ConfluenceIngest.java   # Confluence storage-format page parsing

│   │   └── CmdbTopographer.java    # CMDB GraphQL topology parser

│   ├── services/                  # Core Business Logic

│   │   ├── TemporalOrchestrator.java   # Distributed Temporal transaction workflow engine

│   │   ├── RulesEngine.java        # Core TOGAF-aligned rule validator

│   │   └── SlideCompiler.java      # Automated Google Slides layout compiler

│   ├── prompts/                   # Versioned Expert Prompt Templates

│   │   ├── Templates.java           # Structured prompts for Agents 1-5

│   │   └── Registry.java            # Prompt versioning & swap registry

│   ├── agents/                    # Self-Correcting Multi-Agent Intel Tiers

│   │   ├── Agent1Ingestion.java    # Squad BA & BIAN domain mapper (Agent 1)

│   │   ├── Agent2Topographer.java  # Squad DBA & LLD topology designer (Agent 2)

│   │   ├── Agent3Scoper.java       # Guild Security & Regulatory Scoper (Agent 3)

│   │   ├── Agent4Governor.java     # Tribe Stakeholder Facilitator (Agent 4)

│   │   ├── Agent5Dora.java         # Chapter SRE & Quality Auditor (Agent 5)

│   │   └── tools/                 # Connector Plugins

│   │       ├── VectorSearchService.java   # pgvector semantic rule queries

│   │       ├── JiraConnector.java  # Jira REST API client

│   │       ├── ConfluenceConnector.java   # Confluence REST API client

│   │       └── AlfabetClient.java  # Software AG Alfabet REST API client

│   ├── security/                  # Three-Layer Protection Guardrails

│   │   ├── PiiMaskerFilter.java          # Input mask for customer IBANs & IDs

│   │   ├── LogSanitizer.java       # Runtime filter for diagnostic logs (ADR-004)

│   │   └── CdeVerifier.java        # Enforces FAPI2 check boundaries on CDE access

│   ├── security/                  # Three-Layer Protection Guardrails

│   │   ├── PiiMaskerFilter.java          # Input mask for customer IBANs & IDs

│   │   ├── LogSanitizer.java       # Runtime filter for diagnostic logs (ADR-004)

│   │   └── CdeVerifier.java        # Enforces FAPI2 check boundaries on CDE access

│   ├── evaluation/                # Spec Reconciliation & Quality Gates

│   │   ├── TriangleValidator.java  # Semantic Triangle Code Reconciliation Check

│   │   └── QualityGates.java       # CI/CD automated deployment blockers

│   └── observability/             # Phase-Level Tracing & telemetry

│       ├── TraceLogger.java        # Microservice phase-level logs (Kafka)

│       ├── DebtCalculator.java     # Programmatic Technical Debt Index (TDI) calculations

│       └── TokenTracker.java       # Local LLM token consumption and cost auditor

├── data/                          # Database Migrations & Initial Seeds

│   ├── migrations/                # Flyway PostgreSQL database schemas

│   └── seed_regulations.sql       # Vector rules database seed values (pgvector)

├── scripts/                       # Maintenance & Pipeline Hooks

│   ├── SeedPgvector.java           # Script to vectorize and embed regulatory text

│   ├── CmdbCdcListener.java       # Kafka consumer listening to physical CMDB CDC updates

│   └── Healthcheck.java             # EKS container liveness & readiness checks

├── docs/                          # Specifications and Guides

│   ├── architecture_codex.md      # Enriched Master Architecture specifications

│   ├── api_reference.md           # API endpoints (balance, card auth, callback loops)

│   └── deployment_guide.md        # ArgoCD GitOps deployment guide

├── .claude/                       # AI Code Assistant Project Memory

│   └── rules/

│       ├── code-style.md          # Code standards & typing specifications

│       └── testing.md             # Temporal transaction and integration tests

├── CLAUDE.md                      # AI assistant project guidelines

├── AGENTS.md                      # Multi-agent interface rules and contracts

├── docker-compose.yml             # Local multi-agent orchestration deployment manifest

├── pom.xml                 # Package dependencies (Kong API Gateway equivalent)

└── README.md                      # This document

