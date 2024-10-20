CREATE TABLE payment_details (
    transaction_id VARCHAR(8) PRIMARY KEY,
    payment_method VARCHAR(50) NOT NULL,
    bank_name VARCHAR(100),
    card_number VARCHAR(20),
    expiry_date VARCHAR(7),
    card_holder_name VARCHAR(100),
    upi_id VARCHAR(100),
    wallet_name VARCHAR(100),
    amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL
);