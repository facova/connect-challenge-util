CREATE TABLE keypair (
    id SERIAL,
    created_at BIGINT,
    public_key VARCHAR,
    private_key VARCHAR,
    CONSTRAINT keypar_pk PRIMARY KEY (id)
);