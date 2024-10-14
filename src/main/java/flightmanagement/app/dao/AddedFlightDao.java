package flightmanagement.app.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import flightmanagement.app.entities.AddedAirline;
import flightmanagement.app.entities.AddedFlight;


public interface AddedFlightDao {
	
	
	int insertFlight(AddedFlight addedflight) throws IOException,SerialException,SQLException;
	AddedFlight fetchFlightByFlightNo(String flightNo);
	
	AddedFlight updateFlight(AddedFlight addedFlight)throws IOException,SerialException,SQLException;
	AddedFlight getUserById(int flightId);

}
