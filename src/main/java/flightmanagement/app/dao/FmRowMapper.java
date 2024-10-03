package flightmanagement.app.dao;


import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.TreePath;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import flightmanagement.app.entities.PassengerRegistration;
import flightmanagement.app.entities.FlightManagerRegistration;
import flightmanagement.app.utilities.ByteArrayMultiPartFile;

public class FmRowMapper implements RowMapper<FlightManagerRegistration> {

	@Override
	public FlightManagerRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		FlightManagerRegistration flightManagerRegistration = new FlightManagerRegistration();

		flightManagerRegistration.setBoId(rs.getInt("businessOwner_id"));
		flightManagerRegistration.setFirstName(rs.getString("first_name"));
		flightManagerRegistration.setLastName(rs.getString("last_name"));
		flightManagerRegistration.setEmailId(rs.getString("email_id"));
		flightManagerRegistration.setMobileNo(rs.getString("mobile_no"));
		flightManagerRegistration.setDateOfBirth(rs.getDate("date_of_birth"));
		flightManagerRegistration.setUsername(rs.getString("username"));
		flightManagerRegistration.setPasswordSalt(rs.getString("password_salt"));
		flightManagerRegistration.setPasswordHash(rs.getString("password_hash"));
		
		Blob profileImageBlob = rs.getBlob("profile_image");
		
		// Convert blob to MultipartFile
		byte[] imageBytes = profileImageBlob.getBytes(1, (int) profileImageBlob.length());
		MultipartFile profileImage = new ByteArrayMultiPartFile(imageBytes, "profileImage.jpg", "image/jpeg");
		
		flightManagerRegistration.setProfileImage(profileImage);
		
		return flightManagerRegistration;
		
	}

	

}
