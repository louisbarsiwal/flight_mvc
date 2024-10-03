package flightmanagement.app.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import flightmanagement.app.entities.BusinessOwnerRegistration;
import flightmanagement.app.entities.PassengerRegistration;



public interface BusinessOwnerDao {
	
	
	int insertBusinessOwner(BusinessOwnerRegistration businessOwnerRegistration) throws IOException,SerialException,SQLException;
	BusinessOwnerRegistration fetchUser(String username) ;

}
