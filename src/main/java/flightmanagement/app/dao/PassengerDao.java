package flightmanagement.app.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import flightmanagement.app.entities.PassengerRegistration;



public interface PassengerDao {
	
	
	int insertPassenger(PassengerRegistration passengerRegistration) throws IOException, SerialException, SQLException;
	PassengerRegistration fetchUser(String username) ;

}
