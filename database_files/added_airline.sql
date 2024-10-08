CREATE TABLE added_airline (
    id INT AUTO_INCREMENT PRIMARY KEY,
    airline_name VARCHAR(20) NOT NULL UNIQUE,
    airline_number VARCHAR(10) NOT NULL UNIQUE,
    model_number VARCHAR(15) NOT NULL
);