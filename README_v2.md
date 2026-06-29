RedBank Agent-Native Architecture Framework (Version 2.0)
Production-Grade Code Directory Tree Mapping our AI Harness
This repository represents the production-ready implementation of our high-governance, autonomous architectural system inside RedBank. It is structured around the modular directory layout of modern, enterprise-grade AI systems, completely decoupling stateless compute models from our security and integration "Harness".
1. Directory Tree Structure
redbank-agent-native-architecture-framework-v2/

├── app/

│   ├── main.py                    # Entrypoint for the Cognitive Orchestration Harness API

│   ├── config.py                  # OIDC/IdP configurations, Kafka brokers, and environment variables

│   ├── models.py                  # Pydantic schemas and SQLAlchemy models for PostgreSQL

│   ├── Dockerfile                 # EKS container definition

│   ├── components/                # Ingestion & Context Collectors

│   │   ├── confluence_ingest.py   # Confluence storage-format page parsing

│   │   └── cmdb_topographer.py    # CMDB GraphQL topology parser

│   ├── services/                  # Core Business Logic

│   │   ├── saga_orchestrator.py   # Distributed Temporal transaction workflow engine

│   │   ├── rules_engine.py        # Core TOGAF-aligned rule validator

│   │   └── slide_compiler.py      # Automated Google Slides layout compiler

│   ├── prompts/                   # Versioned Expert Prompt Templates

│   │   ├── templates.py           # Structured prompts for Agents 1-5

│   │   └── registry.py            # Prompt versioning & swap registry

│   ├── agents/                    # Self-Correcting Multi-Agent Intel Tiers

│   │   ├── agent1_ingestion.py    # Squad BA & BIAN domain mapper (Agent 1)

│   │   ├── agent2_topographer.py  # Squad DBA & LLD topology designer (Agent 2)

│   │   ├── agent3_scoper.py       # Guild Security & Regulatory Scoper (Agent 3)

│   │   ├── agent4_governor.py     # Tribe Stakeholder Facilitator (Agent 4)

│   │   ├── agent5_dora.py         # Chapter SRE & Quality Auditor (Agent 5)

│   │   └── tools/                 # Connector Plugins

│   │       ├── vector_search.py   # pgvector semantic rule queries

│   │       ├── jira_connector.py  # Jira REST API client

│   │       ├── doc_connector.py   # Confluence REST API client

│   │       └── alfabet_client.py  # Software AG Alfabet REST API client

│   ├── security/                  # Three-Layer Protection Guardrails

│   │   ├── pii_masker.py          # Input mask for customer IBANs & IDs

│   │   ├── log_sanitizer.py       # Runtime filter for diagnostic logs (ADR-004)

│   │   └── cde_verifier.py        # Enforces FAPI2 check boundaries on CDE access

│   ├── security/                  # Three-Layer Protection Guardrails

│   │   ├── pii_masker.py          # Input mask for customer IBANs & IDs

│   │   ├── log_sanitizer.py       # Runtime filter for diagnostic logs (ADR-004)

│   │   └── cde_verifier.py        # Enforces FAPI2 check boundaries on CDE access

│   ├── evaluation/                # Spec Reconciliation & Quality Gates

│   │   ├── triangle_validator.py  # Semantic Triangle Code Reconciliation Check

│   │   └── quality_gates.py       # CI/CD automated deployment blockers

│   └── observability/             # Phase-Level Tracing & telemetry

│       ├── trace_logger.py        # Microservice phase-level logs (Kafka)

│       ├── debt_calculator.py     # Programmatic Technical Debt Index (TDI) calculations

│       └── token_tracker.py       # Local LLM token consumption and cost auditor

├── data/                          # Database Migrations & Initial Seeds

│   ├── migrations/                # Alembic PostgreSQL database schemas

│   └── seed_regulations.sql       # Vector rules database seed values (pgvector)

├── scripts/                       # Maintenance & Pipeline Hooks

│   ├── seed_pgvector.py           # Script to vectorize and embed regulatory text

│   ├── cmdb_cdc_listener.py       # Kafka consumer listening to physical CMDB CDC updates

│   └── healthcheck.py             # EKS container liveness & readiness checks

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

├── pyproject.toml                 # Package dependencies (Spring Cloud equivalent)

└── README.md                      # This document

