package flightmanagement.app.dao;

import java.io.IOException;
import java.sql.SQLException;

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

	

}
