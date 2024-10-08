package flightmanagement.app.dao;

import flightmanagement.app.entities.AddedFlight;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightRowMapper implements RowMapper<AddedFlight> {
    @Override
    public AddedFlight mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        AddedFlight addedFlight = new AddedFlight();
        
        addedFlight.setFlightId(rs.getInt("flight_id")); 
        addedFlight.setAirlineName(rs.getString("airline_name"));
        addedFlight.setFlightNo(rs.getString("flight_no"));
        addedFlight.setFlightModel(rs.getString("flight_model"));
        addedFlight.setFromLocation(rs.getString("from_location"));
        addedFlight.setToLocation(rs.getString("to_location"));
        addedFlight.setDepartureDateTime(rs.getString("departure_datetime"));
        addedFlight.setArrivalDateTime(rs.getString("arrival_datetime"));
        addedFlight.setTotalSeats(rs.getInt("total_seats"));
        addedFlight.setEconomySeats(rs.getInt("economy_seats"));
        addedFlight.setEconomyPrice(rs.getDouble("economy_price"));
        addedFlight.setBusinessSeats(rs.getInt("business_seats"));
        addedFlight.setBusinessPrice(rs.getDouble("business_price"));
        
        return addedFlight;
    }
}
