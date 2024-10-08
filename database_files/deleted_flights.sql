CREATE TABLE deleted_flights (
    id INT AUTO_INCREMENT PRIMARY KEY,
    airline_name VARCHAR(100) NOT NULL,
    flight_no VARCHAR(50) NOT NULL,
    flight_model VARCHAR(50) NOT NULL,
    from_location VARCHAR(100) NOT NULL,
    to_location VARCHAR(100) NOT NULL,
    departure_datetime DATETIME NOT NULL,
    arrival_datetime DATETIME NOT NULL,
    total_seats INT NOT NULL,
    economy_seats INT NOT NULL,
    economy_price DECIMAL(10, 2) NOT NULL,
    business_seats INT NOT NULL,
    business_price DECIMAL(10, 2) NOT NULL,
    deleted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);