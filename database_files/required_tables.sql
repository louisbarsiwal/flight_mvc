
--querry for creating admin_businessowner table:

CREATE TABLE `flight_database`.`admin_businessowner` (
  `businessOwner_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `email_id` VARCHAR(30) NOT NULL,
  `mobile_no` CHAR(10) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `password_salt` VARCHAR(10) NULL,
  `password_hash` VARCHAR(64) NULL,
  `profile_image` LONGBLOB NOT NULL,
  PRIMARY KEY (`businessOwner_id`),
  UNIQUE INDEX `mobile_no_UNIQUE` (`mobile_no` ASC) VISIBLE,
  UNIQUE INDEX `businessOwner_id_UNIQUE` (`businessOwner_id` ASC) VISIBLE,
  UNIQUE INDEX `email_id_UNIQUE` (`email_id` ASC) VISIBLE,
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) VISIBLE);

--querry for creating admin_flightmanager table:
 CREATE TABLE `flight_database`.`admin_flightmanager` (
  `flightManager_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `email_id` VARCHAR(30) NOT NULL,
  `mobile_no` CHAR(10) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `password_salt` VARCHAR(10) NULL,
  `password_hash` VARCHAR(64) NULL,
  `profile_image` LONGBLOB NOT NULL,
  PRIMARY KEY (`flightManager_id`),
  UNIQUE INDEX `mobile_no_UNIQUE` (`mobile_no` ASC) VISIBLE,
  UNIQUE INDEX `flightManager_id_UNIQUE` (`flightManager_id` ASC) VISIBLE,
  UNIQUE INDEX `email_id_UNIQUE` (`email_id` ASC) VISIBLE,
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) VISIBLE);
  
 -- Modify your existing access_control table to add the foreign key
CREATE TABLE access_control (
    flightManager_id INT PRIMARY KEY,   -- This is the same as the ID in admin_flightmanager
    status VARCHAR(10) NOT NULL,
    FOREIGN KEY (flightManager_id) REFERENCES admin_flightmanager(flightManager_id) ON DELETE CASCADE
);

-- trigger for setting status as revoke after new inserting new row in admin_flightmanager
-- (i.e if new flightmanager registers)
DELIMITER $$

CREATE TRIGGER set_initial_access_control
AFTER INSERT ON admin_flightmanager
FOR EACH ROW
BEGIN
    INSERT INTO access_control (flightmanager_id, status)
    VALUES (NEW.flightmanager_id, 'REVOKE');
END$$

DELIMITER ;

--querry for creating admin_passenger table:

CREATE TABLE `flight_database`.`admin_passenger` (
  `passenger_Id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `email` VARCHAR(25) NOT NULL,
  `mobile_no` CHAR(10) NOT NULL,
  `age` INT NOT NULL,
  `gender` ENUM('Male', 'Female', 'Other') NOT NULL,
  `username` VARCHAR(10) NOT NULL,
  `password_salt` VARCHAR(45) NULL,
  `password_hash` VARCHAR(64) NULL,
  `profile_image` LONGBLOB NOT NULL,
  PRIMARY KEY (`passenger_Id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `mobile_no_UNIQUE` (`mobile_no` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);


--querry for creating added_airline table:
CREATE TABLE added_airline (
    id INT AUTO_INCREMENT PRIMARY KEY,
    airline_name VARCHAR(20) NOT NULL UNIQUE,
    airline_number VARCHAR(10) NOT NULL,
    model_number VARCHAR(15) NOT NULL
);


--querry for creating added_flights table:
CREATE TABLE added_flights (
    flight_id INT AUTO_INCREMENT PRIMARY KEY, 
    airline_name VARCHAR(20) NOT NULL,
    flight_no VARCHAR(20) NOT NULL ,
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

--querry for creating deleted_airlines table:
CREATE TABLE deleted_airlines (
    id INT AUTO_INCREMENT PRIMARY KEY,
    airline_name VARCHAR(20) NOT NULL UNIQUE,
    airline_number VARCHAR(10) NOT NULL,
    model_number VARCHAR(15) NOT NULL,
    deleted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--querry for creating deleted_flights table:
CREATE TABLE deleted_flights (
	flight_id INT AUTO_INCREMENT PRIMARY KEY, 
    airline_name VARCHAR(20) NOT NULL,
    flight_no VARCHAR(20) NOT NULL  ,
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

--querry for creating booking_flights table:
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

--querry for creating cancelled_bookings table:
CREATE TABLE cancelled_bookings (
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

--querry for creating payment_details table:
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

