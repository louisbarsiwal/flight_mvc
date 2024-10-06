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
