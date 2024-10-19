package flightmanagement.app.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import flightmanagement.app.entities.AddedAirline;
import flightmanagement.app.entities.BusinessOwnerRegistration;

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

    @Override
    public AddedAirline updateAirline(AddedAirline addedAirline) throws IOException, SerialException, SQLException {
        String query = "UPDATE added_airline SET airline_name = ?, airline_number = ?, model_number = ? WHERE id= ?";
        
        // Log the airline ID and values to debug
        System.out.println("Updating airline with ID: " + addedAirline.getAirLineId());
        System.out.println("New values: Name = " + addedAirline.getAirlineName() +
                           ", Number = " + addedAirline.getAirlineNumber() + 
                           ", Model = " + addedAirline.getModelNumber());
        
        // Execute the update
        int rowsAffected = jdbcTemplate.update(query,
            addedAirline.getAirlineName(),
            addedAirline.getAirlineNumber(),
            addedAirline.getModelNumber(),
            addedAirline.getAirLineId());
        
        // Check if any rows were affected
        if (rowsAffected == 0) {
            throw new SQLException("No rows affected. Update failed for ID: " + addedAirline.getAirLineId());
        }
        
        return getUserById(addedAirline.getAirLineId());
    }

	@Override
	public AddedAirline getUserById(int airLineId) {
		String sql = "SELECT * FROM added_airline WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new AirlineRowMapper(),airLineId );
		
	}
	
	

}
