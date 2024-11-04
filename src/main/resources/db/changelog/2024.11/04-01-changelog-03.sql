CREATE TABLE account
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    client_id BIGINT,
    account_type VARCHAR(6),
    balance NUMERIC(19, 2),
    CONSTRAINT fk_account_client FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE transaction
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    account_id BIGINT,
    amount NUMERIC(19, 2),
    transaction_date TIMESTAMP DEFAULT now()
);

CREATE TABLE data_source_error_log
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    stack_trace VARCHAR,
    message VARCHAR,
    method_signature VARCHAR
);

