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
