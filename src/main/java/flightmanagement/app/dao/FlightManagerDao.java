package flightmanagement.app.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import flightmanagement.app.entities.BusinessOwnerRegistration;
import flightmanagement.app.entities.FlightManagerRegistration;



public interface FlightManagerDao {
	
	
	int insertFlightManager(FlightManagerRegistration flightManagerRegistration) throws IOException,SerialException,SQLException;
	int updateFlightManagerPassword(FlightManagerRegistration flightManagerRegistration) throws IOException,SerialException,SQLException;

	FlightManagerRegistration fetchUser(String username) ;
	
	
	FlightManagerRegistration modifyUser(FlightManagerRegistration flightManagerRegistration) throws SerialException, IOException, SQLException;
	FlightManagerRegistration getUserById(int flightManagerId);

}
