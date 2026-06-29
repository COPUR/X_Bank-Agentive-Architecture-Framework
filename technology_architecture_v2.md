Technology Architecture - RedBank V2 Directory Mapping
The BDAT Technology Architecture establishes the physical infrastructure boundaries, the MSK Kafka event backbone, containerization parameters, and localized GPU AI pipelines. It maps directly to our directory structure:
1. Containerization & EKS Deployment
	•	Implementation Code: app/Dockerfile & docker-compose.yml
	•	Mechanism:
	•	app/Dockerfile containerizes our core Cognitive Orchestration Harness API, allowing it to run as a microservice pod on AWS EKS.
	•	docker-compose.yml provides a local development manifest, spin-up configuration, and environment networking to orchestrate all 5 agent containers and our local pgvector PostgreSQL database during technical sandbox PoT evaluations.
2. Apache Kafka (AWS MSK) Messaging Backbone
	•	Implementation Code: app/config.py & app/services/
	•	Mechanism:
	•	app/config.py defines the bootstrap servers and credential mappings for our AWS MSK Kafka cluster.
	•	Our core services publish and consume messages over distinct event-driven topics:
	•	arch-ingestion-topic: Maintained by Agent 1 to broadcast ingested context.
	•	lld-generation-topic: Maintained by Agent 2 to broadcast LLD designs.
	•	compliance-alerts: Maintained by Agent 3 to broadcast audit decisions.
	•	temporal-intent-events: Managed by Temporal orchestrator for distributed transaction state.
3. Local Stateless Compute Cascades (Mixture of Experts) (vLLM on private EKS GPU Nodes)
	•	Model: Llama-3-70B-Instruct.
	•	Deployment: Running on private GPU nodes on AWS EKS. Inbound prompts and payloads are routed to the localized model via vLLM container ports.
	•	Sovereignty Boundary: Meets strict CBUAE data residency guidelines. The local compute engine runs in isolation within our sovereign VPC boundary, completely blocked from the outbound public internet.
	•	Semantic Vector Caching: Deployed alongside the vLLM engine, a Redis/Qdrant memory store serves as a high-speed semantic cache to mitigate TTFT bottlenecks.
4. API Security, mTLS & DPoP Token Binding
	•	All communication channels use Mutual TLS (mTLS) client certificates running over TLS 1.3.
	•	app/security/cde_verifier.py acts as an active gate, enforcing that all ingress requests to the CDE contain cryptographically bound access tokens with valid DPoP signatures, preventing token hijacking.

