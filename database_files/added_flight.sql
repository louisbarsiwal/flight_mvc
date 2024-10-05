CREATE TABLE added_flights (
    flight_id INT PRIMARY KEY,
    airline_name VARCHAR(255),
    flight_no VARCHAR(100),
    flight_model VARCHAR(100),
    from_location VARCHAR(255),
    to_location VARCHAR(255),
    departure_datetime DATETIME,
    arrival_datetime DATETIME,
    total_seats INT,
    economy_seats INT,
    economy_price DECIMAL(10, 2),
    business_seats INT,
    business_price DECIMAL(10, 2)
);