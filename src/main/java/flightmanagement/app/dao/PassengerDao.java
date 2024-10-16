package flightmanagement.app.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import flightmanagement.app.entities.BusinessOwnerRegistration;
import flightmanagement.app.entities.PassengerRegistration;



public interface PassengerDao {
	
	
	int insertPassenger(PassengerRegistration passengerRegistration) throws IOException, SerialException, SQLException;
	PassengerRegistration fetchUser(String username) ;
	PassengerRegistration getUserById(int passenger_Id);
	PassengerRegistration modifyPassengerProfile(PassengerRegistration passengerRegistration)
			throws SerialException, IOException, SQLException;
	int updatePassengerPassword(PassengerRegistration passengerRegistration)
			throws IOException, SerialException, SQLException;

}
