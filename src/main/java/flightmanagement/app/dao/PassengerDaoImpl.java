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

import flightmanagement.app.entities.BusinessOwnerRegistration;
import flightmanagement.app.entities.PassengerRegistration;

@Repository
public class PassengerDaoImpl implements PassengerDao {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertPassenger(PassengerRegistration passengerRegistration)
			throws IOException, SerialException, SQLException {
		Blob profileImage = getBlob(passengerRegistration.getProfileImage());

		String query = "INSERT INTO admin_passenger" + "(`first_name`, `last_name`, `email`, `mobile_no`, "
				+ "`age`, `gender`,`username`, `password_salt`, `password_hash`, "
				+ "`profile_image`) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

		return jdbcTemplate.update(query, passengerRegistration.getFirstName(), passengerRegistration.getLastName(), passengerRegistration.getEmailId(),
				passengerRegistration.getMobileNo(), passengerRegistration.getAge(),passengerRegistration.getGender(), passengerRegistration.getUsername(),
				passengerRegistration.getPasswordSalt(),passengerRegistration.getPasswordHash(), profileImage);
		
	}
	

	private Blob getBlob(MultipartFile image) throws IOException, SerialException, SQLException {
		byte[] byteArr = image.getBytes();
		Blob imageBlob = new SerialBlob(byteArr);
		return imageBlob;
	}
	
	

	@Override
	public PassengerRegistration fetchUser(String username) {
		String sql = "SELECT * FROM admin_passenger WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new PassengerRowMapper(), username);
	}

	@Override
	public PassengerRegistration modifyPassengerProfile(PassengerRegistration passengerRegistration)
			throws SerialException, IOException, SQLException {
		Blob profileImage = getBlob(passengerRegistration.getProfileImage());

		String query = "UPDATE admin_passenger SET first_name = ?, last_name = ?, email = ?, "
				+ "mobile_no = ?, age = ?, gender = ?, profile_image = ? WHERE passenger_Id = ?";

		jdbcTemplate.update(query, passengerRegistration.getFirstName(), passengerRegistration.getLastName(), passengerRegistration.getEmailId(), passengerRegistration.getMobileNo(),
				passengerRegistration.getAge(),passengerRegistration.getGender(), profileImage, passengerRegistration.getPassenger_Id());
		
		return getUserById(passengerRegistration.getPassenger_Id());
	}

	
	@Override
	public PassengerRegistration getUserById(int passenger_Id) {
		String sql = "SELECT * FROM admin_passenger WHERE passenger_Id = ?";
		return jdbcTemplate.queryForObject(sql, new PassengerRowMapper(), passenger_Id);
		
	}	

}
