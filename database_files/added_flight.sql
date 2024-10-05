CREATE TABLE added_flight (
    flight_id INT AUTO_INCREMENT PRIMARY KEY, -- Auto-incrementing primary key
    airline_name VARCHAR(100) NOT NULL,
    flight_no VARCHAR(20) NOT NULL,
    flight_model VARCHAR(50) NOT NULL,
    from_location VARCHAR(100) NOT NULL,
    to_location VARCHAR(100) NOT NULL,
    flight_date DATE NOT NULL, -- Assuming this is just a date without time
    departure_datetime DATETIME NOT NULL,
    arrival_datetime DATETIME NOT NULL,
    total_seats INT NOT NULL,
    economy_seats INT NOT NULL,
    economy_price DECIMAL(10, 2) NOT NULL, -- Assuming price format
    business_seats INT NOT NULL,
    business_price DECIMAL(10, 2) NOT NULL, -- Assuming price format
    class_id INT NOT NULL, -- Assuming this relates to a Class entity
    fm_id INT NOT NULL -- Assuming this relates to a Flight Manager entity
);