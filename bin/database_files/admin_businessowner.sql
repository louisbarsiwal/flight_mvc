
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
