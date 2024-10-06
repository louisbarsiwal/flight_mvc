CREATE TABLE deleted_airlines (
    id SERIAL PRIMARY KEY,
    airline_name VARCHAR(255),
    airline_number VARCHAR(255),
    model_number VARCHAR(255),
    deleted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);