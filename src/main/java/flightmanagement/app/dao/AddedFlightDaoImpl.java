package flightmanagement.app.dao;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import flightmanagement.app.entities.AddedFlight;




@Repository
public class AddedFlightDaoImpl implements AddedFlightDao {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertFlight(AddedFlight addedflight)
			throws IOException, SerialException, SQLException {

		String query = "INSERT INTO added_flights " +
                "(airline_name, flight_no, flight_model, from_location, to_location, " +
                "departure_datetime, arrival_datetime, total_seats, economy_seats, economy_price, " +
                "business_seats, business_price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        return jdbcTemplate.update(query,
	                addedflight.getAirlineName(),
	                addedflight.getFlightNo(),
	                addedflight.getFlightModel(),
	                addedflight.getFromLocation(),
	                addedflight.getToLocation(),
	                addedflight.getDepartureDateTime(),
	                addedflight.getArrivalDateTime(),
	                addedflight.getTotalSeats(),
	                addedflight.getEconomySeats(),
	                addedflight.getEconomyPrice(),
	                addedflight.getBusinessSeats(),
	                addedflight.getBusinessPrice());
	}
		

	
	public AddedFlight fetchFlightByFlightNo(String flightNo) {
        String sql = "SELECT * FROM added_flights WHERE flight_no = ?";
        return jdbcTemplate.queryForObject(sql, new FlightRowMapper(), flightNo);
    }


	@Override
	public AddedFlight updateFlight(AddedFlight addedFlight) throws IOException, SerialException, SQLException {
String query = "UPDATE added_flights SET airline_name = ? , flight_no = ?, flight_model = ?, from_location = ?, "
		+ "to_location = ?, departure_datetime = ?, arrival_datetime = ?, total_seats = ?, "
		+ "economy_seats = ?,economy_price = ?, business_seats = ?, business_price = ? WHERE flight_id = ?";
        
     
   
        int rowsAffected = jdbcTemplate.update(query,
        		addedFlight.getAirlineName(),
        		addedFlight.getFlightNo(),
        		addedFlight.getFlightModel(),
        		addedFlight.getFromLocation(),
        		addedFlight.getToLocation(),
        		addedFlight.getDepartureDateTime(),
        		addedFlight.getArrivalDateTime(),
        		addedFlight.getTotalSeats(),
        		addedFlight.getEconomySeats(),
        		addedFlight.getEconomyPrice(),
        		addedFlight.getBusinessSeats(),
        		addedFlight.getBusinessPrice(),
        		addedFlight.getFlightId());
        
        // Check if any rows were affected
        if (rowsAffected == 0) {
            throw new SQLException("No rows affected. Update failed for ID: " + addedFlight.getFlightId());
        }
        
        return getUserById(addedFlight.getFlightId());
    }

	@Override
	public AddedFlight getUserById(int flightId) {
		String sql = "SELECT * FROM added_flights WHERE flight_id = ?";
		return jdbcTemplate.queryForObject(sql, new FlightRowMapper(), flightId);
		
	}	

	
	public List<AddedFlight> searchFlights(String from, String to, String date) {

		String sql = "SELECT * FROM added_flights WHERE from_location = ? AND to_location = ? AND departure_datetime LIKE ?";

	    System.out.println("started searching ");
	    return jdbcTemplate.query(sql, new FlightRowMapper(), from, to, date + "%");
	}

	
	
	
}
