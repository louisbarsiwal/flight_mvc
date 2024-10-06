package flightmanagement.app.dao;

import java.io.IOException;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import flightmanagement.app.entities.BusinessOwnerRegistration;
<<<<<<< HEAD
=======

>>>>>>> c28785d7b0456f9dc0f71b3af0bfb672df4b1680





public interface BusinessOwnerDao {
	
	
	int insertBusinessOwner(BusinessOwnerRegistration businessOwnerRegistration) throws IOException,SerialException,SQLException;
	int updateBusinessOwnerPassword(BusinessOwnerRegistration businessOwnerRegistration) throws IOException,SerialException,SQLException;
	BusinessOwnerRegistration fetchUser(String username) ;
	
	
	BusinessOwnerRegistration modifyUser(BusinessOwnerRegistration businessOwnerRegistration) 
			throws SerialException, IOException, SQLException;
	BusinessOwnerRegistration getUserById(int boId);

}
