package flightmanagement.app.dao;

import java.io.IOException;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;


import flightmanagement.app.entities.FlightManagerRegistration;



public interface FlightManagerDao {
	
	
	int insertFlightManager(FlightManagerRegistration flightManagerRegistration) throws IOException,SerialException,SQLException;
	FlightManagerRegistration fetchUser(String username) ;
	//List<FlightManagerRegistration> findAllFlightManagers();
	 //List<Map<String, Object>> findAllFlightManagers();
	List<Map<String, Object>> findAllFlightManager();
	
	
	
}
