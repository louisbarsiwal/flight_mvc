CREATE TABLE booking_flights (
    booking_id INT NOT NULL AUTO_INCREMENT,
    airline_name VARCHAR(20) NOT NULL,
    flight_no VARCHAR(20) NOT NULL,
    flight_model VARCHAR(15) NOT NULL,
    from_location VARCHAR(45) NOT NULL,
    to_location VARCHAR(45) NOT NULL,
    departure_datetime DATETIME NOT NULL,
    arrival_datetime DATETIME NOT NULL,
    economy_seats INT NOT NULL,
    economy_price DECIMAL(15,3) NOT NULL,
    business_seats INT NOT NULL,
    business_price DECIMAL(15,3) NOT NULL,
    total_price DECIMAL(15,3) NOT NULL,
    PRIMARY KEY (booking_id)
);

CREATE TABLE cancelled_flights (
    booking_id INT NOT NULL AUTO_INCREMENT,
    airline_name VARCHAR(20) NOT NULL,
    flight_no VARCHAR(20) NOT NULL,
    flight_model VARCHAR(15) NOT NULL,
    from_location VARCHAR(45) NOT NULL,
    to_location VARCHAR(45) NOT NULL,
    departure_datetime DATETIME NOT NULL,
    arrival_datetime DATETIME NOT NULL,
    economy_seats INT NOT NULL,
    economy_price DECIMAL(15,3) NOT NULL,
    business_seats INT NOT NULL,
    business_price DECIMAL(15,3) NOT NULL,
    total_price DECIMAL(15,3) NOT NULL,
    PRIMARY KEY (booking_id)
);
