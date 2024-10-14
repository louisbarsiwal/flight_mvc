CREATE TABLE added_flights (
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
    FOREIGN KEY (airline_name) REFERENCES added_airline(airline_name)
    ON DELETE CASCADE
);


ALTER TABLE added_flights
DROP FOREIGN KEY added_flights_ibfk_1;

ALTER TABLE added_flights
ADD CONSTRAINT added_flights_ibfk_1
FOREIGN KEY (airline_name)
REFERENCES added_airline(airline_name)
ON DELETE CASCADE
ON UPDATE CASCADE;
------------------------------------------------(or)----------------------------------

CREATE TABLE added_flights (
    flight_id INT AUTO_INCREMENT PRIMARY KEY, 
    airline_name VARCHAR(20) NOT NULL,
    flight_no VARCHAR(20) NOT NULL UNIQUE,
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
    FOREIGN KEY (airline_name) REFERENCES added_airline(airline_name)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
