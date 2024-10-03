package flightmanagement.app.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;


import flightmanagement.app.entities.PassengerRegistration;

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

		String query = "INSERT INTO admin_flightmanager " + "(`first_name`, `last_name`, `email_id`, `mobile_no`, "
				+ "`date_of_birth`, `username`, `password_salt`, `password_hash`, "
				+ "`profile_image`) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		return jdbcTemplate.update(query, flightManagerRegistration.getFirstName(), flightManagerRegistration.getLastName(), flightManagerRegistration.getEmailId(),
				flightManagerRegistration.getMobileNo(), flightManagerRegistration.getDateOfBirth(), flightManagerRegistration.getUsername(), flightManagerRegistration.getPasswordSalt(),
				flightManagerRegistration.getPasswordHash(), profileImage);
		
	}
	

	private Blob getBlob(MultipartFile image) throws IOException, SerialException, SQLException {
		byte[] byteArr = image.getBytes();
		Blob imageBlob = new SerialBlob(byteArr);
		return imageBlob;
	}
	
	

	@Override
	public FlightManagerRegistration fetchUser(String username) {
		String sql = "SELECT * FROM admin_flightmanager WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new FmRowMapper(), username);
	}

	

	
	

}
