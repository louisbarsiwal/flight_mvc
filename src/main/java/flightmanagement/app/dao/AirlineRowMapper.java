package flightmanagement.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import flightmanagement.app.entities.AddedAirline;

public class AirlineRowMapper implements RowMapper<AddedAirline> {
	@Override
	public AddedAirline mapRow(ResultSet rs, int rowNum) throws SQLException {
		AddedAirline addedAirline = new AddedAirline();
		addedAirline.setAirlineName(rs.getString("airline_name"));
		addedAirline.setAirlineNumber(rs.getString("airline_number"));
		addedAirline.setModelNumber(rs.getString("model_number"));
		return addedAirline;
	}
}
