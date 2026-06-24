CREATE TABLE invoices (
    id UUID NOT NULL,
    creation_date TIMESTAMP(6) WITH TIME ZONE,
    product_amount INTEGER,
    product_id VARCHAR(255),
    PRIMARY KEY (id)
);