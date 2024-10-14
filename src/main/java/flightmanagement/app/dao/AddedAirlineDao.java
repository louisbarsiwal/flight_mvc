package flightmanagement.app.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import flightmanagement.app.entities.AddedAirline;


public interface AddedAirlineDao {
	
	
	int insertAirline(AddedAirline addedairline) throws IOException,SerialException,SQLException;
	AddedAirline fetchAirlineByNumber(String airlineNumber);
	AddedAirline updateAirline(AddedAirline addedAirline)throws IOException,SerialException,SQLException;
	AddedAirline getUserById(int airLineId);
}
