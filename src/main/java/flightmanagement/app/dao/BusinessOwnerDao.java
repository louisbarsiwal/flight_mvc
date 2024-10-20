package flightmanagement.app.dao;

import java.io.IOException;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import flightmanagement.app.entities.BusinessOwnerRegistration;



public interface BusinessOwnerDao {
	
	
	
	int updateBusinessOwnerPassword(BusinessOwnerRegistration businessOwnerRegistration) throws IOException,SerialException,SQLException;
	BusinessOwnerRegistration fetchUser(String username) ;
	
	
	BusinessOwnerRegistration modifyUser(BusinessOwnerRegistration businessOwnerRegistration) 
			throws SerialException, IOException, SQLException;
	BusinessOwnerRegistration getUserById(int boId);

}
