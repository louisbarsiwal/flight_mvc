package flightmanagement.app.dao;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import flightmanagement.app.entities.AddedAirline;

@Repository
public class AddedAirlineDaoImpl implements AddedAirlineDao {
    
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

    @Override
    public int insertAirline(AddedAirline addedAirline) throws IOException, SQLException {
        String query = "INSERT INTO added_airline (airline_name, airline_number, model_number) VALUES (?, ?, ?)";
        return jdbcTemplate.update(query, 
                addedAirline.getAirlineName(),
                addedAirline.getAirlineNumber(),
                addedAirline.getModelNumber());
    }

    @Override
    public AddedAirline fetchAirlineByNumber(String airlineNumber) {
        String sql = "SELECT * FROM added_airline WHERE airline_number = ?";
        return jdbcTemplate.queryForObject(sql, new AirlineRowMapper(), airlineNumber);
    }
}
