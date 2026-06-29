Part 4: Integration Design - RedBank Agent-Native Architecture Framework
4.1 BIAN Service Domain Mapping
	•	Consumer Account Service Domain: Map logical service components to the BIAN Consumer Account schema, exposing GET /api/v1/accounts/{accountId}/balance.
	•	Card Authorization Service Domain: Map card authorizations to the BIAN Card Authorization schema, exposing POST /api/v1/cards/authorize.
4.2 API Security Profile & Identity Flow
	•	All communications run strictly over Mutual TLS (mTLS) and TLS 1.3.
	•	OAuth 2.1 access tokens are reinforced with DPoP (Demonstrating Proof-of-Possession).
	•	Sensitive cardholder numbers (PAN) are strictly forbidden from database storage, using SHA-256 tokens instead.
4.3 Database Entity Schemas (PostgreSQL)
-- PostgreSQL: Account Service Database

CREATE TABLE tbl_accounts (

    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    account_number VARCHAR(34) UNIQUE NOT NULL, -- IBAN format

    balance NUMERIC(18,2) NOT NULL DEFAULT 0.00,

    customer_id UUID NOT NULL,

    currency VARCHAR(3) NOT NULL DEFAULT 'AED',

    status VARCHAR(10) NOT NULL DEFAULT 'ACTIVE'

);

-- PostgreSQL: Card Service Database (CDE)

CREATE TABLE tbl_cards (

    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    card_token VARCHAR(64) UNIQUE NOT NULL, -- SHA-256 PAN token

    expiry_date VARCHAR(5) NOT NULL, -- MM/YY

    status VARCHAR(10) NOT NULL DEFAULT 'ACTIVE',

    credit_limit NUMERIC(15,2) NOT NULL DEFAULT 0.00

);

