package flightmanagement.app.dao;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

<<<<<<< HEAD


=======
>>>>>>> c28785d7b0456f9dc0f71b3af0bfb672df4b1680
import flightmanagement.app.entities.FlightManagerRegistration;



public interface FlightManagerDao {
	
	
	int insertFlightManager(FlightManagerRegistration flightManagerRegistration) throws IOException,SerialException,SQLException;
	FlightManagerRegistration fetchUser(String username) ;
	List<FlightManagerRegistration> findAllFlightManagers();
	
	
}
