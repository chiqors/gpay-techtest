DROP SEQUENCE IF EXISTS t_customer_id_seq;
DROP SEQUENCE IF EXISTS t_transaction_id_seq;

CREATE TABLE t_customer (
    id BIGINT,
    name CHARACTER VARYING(255) NOT NULL,
    balance NUMERIC
);

CREATE SEQUENCE t_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE t_customer_id_seq OWNED BY t_customer.id;

ALTER TABLE t_customer ALTER COLUMN id SET DEFAULT nextval('t_customer_id_seq'::regclass);

ALTER TABLE t_customer ADD CONSTRAINT t_customer_pkey PRIMARY KEY (id);

CREATE TABLE t_transaction (
    id BIGINT,
    customer_id BIGINT NOT NULL,
    type VARCHAR(10) NOT NULL CHECK (type IN ('DEBIT', 'CREDIT')),
    amount NUMERIC NOT NULL,
    timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(10) NOT NULL CHECK (status IN ('SUCCESS', 'FAILED')),
    reference VARCHAR(255) UNIQUE NOT NULL
);

CREATE SEQUENCE t_transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE t_transaction_id_seq OWNED BY t_transaction.id;

ALTER TABLE t_transaction ALTER COLUMN id SET DEFAULT nextval('t_transaction_id_seq'::regclass);

ALTER TABLE t_transaction ADD CONSTRAINT t_transaction_pkey PRIMARY KEY (id);

ALTER TABLE t_transaction ADD CONSTRAINT t_transaction_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES t_customer(id);