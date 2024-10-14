package flightmanagement.app.dao;

import java.io.IOException;




import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import flightmanagement.app.entities.FlightManagerRegistration;


@Repository
public class FlightManagerDaoImpl implements FlightManagerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	 
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertFlightManager(FlightManagerRegistration flightManagerRegistration)
			throws IOException, SerialException, SQLException {
		Blob profileImage = getBlob(flightManagerRegistration.getProfileImage());


		String query = "INSERT INTO admin_flightmanager " + "(first_name, last_name, email_id, mobile_no, "
				+ "date_of_birth, gender, user_name, password_salt, password_hash, " + "profile_image) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

		return jdbcTemplate.update(query, flightManagerRegistration.getFirstName(),
				flightManagerRegistration.getLastName(), flightManagerRegistration.getEmailId(),
				flightManagerRegistration.getMobileNo(), flightManagerRegistration.getDateOfBirth(),
				flightManagerRegistration.getGender(), flightManagerRegistration.getUsername(),
				flightManagerRegistration.getPasswordSalt(), flightManagerRegistration.getPasswordHash(), profileImage);

	


	}
	
	private Blob getBlob(MultipartFile image) throws IOException, SerialException, SQLException {
		byte[] byteArr = image.getBytes();
		Blob imageBlob = new SerialBlob(byteArr);
		return imageBlob;
	}

	@Override
	public FlightManagerRegistration fetchUser(String username) {
		String sql = "SELECT * FROM admin_flightmanager WHERE user_name = ?";
		return jdbcTemplate.queryForObject(sql, new FmRowMapper(), username);
	}
	
	@Override
	public List<Map<String, Object>> findAllFlightManager() {
	    String sql = "SELECT f.flightManager_id, f.first_name, f.last_name, f.user_name, a.status " +
	                 "FROM admin_flightmanager f " +
	                 "LEFT JOIN access_control a ON f.flightManager_id = a.flightManager_id"; // Assuming a JOIN based on flightManagerId

	    return jdbcTemplate.queryForList(sql);
	}




	}


