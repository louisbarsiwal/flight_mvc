CREATE TABLE deleted_flights (
	flight_id INT AUTO_INCREMENT PRIMARY KEY,
    airline_name VARCHAR(20) NOT NULL,
    flight_no VARCHAR(20) NOT NULL,
    flight_model VARCHAR(15) NOT NULL,
    from_location VARCHAR(45) NOT NULL,
    to_location VARCHAR(45) NOT NULL,
    departure_datetime DATETIME NOT NULL,
    arrival_datetime DATETIME NOT NULL,
    total_seats INT NOT NULL,
    economy_seats INT NOT NULL,
    economy_price DECIMAL(10, 2) NOT NULL,
    business_seats INT NOT NULL,
    business_price DECIMAL(10, 2) NOT NULL,
    deleted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (airline_name) REFERENCES deleted_airlines(airline_name)ON DELETE CASCADE
);
 
 
 