package flightmanagement.app.dao;


import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.TreePath;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import flightmanagement.app.entities.BusinessOwnerRegistration;
import flightmanagement.app.utilities.ByteArrayMultiPartFile;

public class BoRowMapper implements RowMapper<BusinessOwnerRegistration> {

	@Override
	public BusinessOwnerRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		BusinessOwnerRegistration businessOwnerRegistration = new BusinessOwnerRegistration();

		businessOwnerRegistration.setBoId(rs.getInt("businessOwner_id"));
		businessOwnerRegistration.setFirstName(rs.getString("first_name"));
		businessOwnerRegistration.setLastName(rs.getString("last_name"));
		businessOwnerRegistration.setEmailId(rs.getString("email_id"));
		businessOwnerRegistration.setMobileNo(rs.getString("mobile_no"));
		businessOwnerRegistration.setDateOfBirth(rs.getDate("date_of_birth"));
		businessOwnerRegistration.setGender(rs.getString("gender"));
		businessOwnerRegistration.setUsername(rs.getString("user_name"));
		businessOwnerRegistration.setPasswordSalt(rs.getString("password_salt"));
		businessOwnerRegistration.setPasswordHash(rs.getString("password_hash"));
		
		Blob profileImageBlob = rs.getBlob("profile_image");
		
		// Convert blob to MultipartFile
		byte[] imageBytes = profileImageBlob.getBytes(1, (int) profileImageBlob.length());
		MultipartFile profileImage = new ByteArrayMultiPartFile(imageBytes, "profileImage.jpg", "image/jpeg");
		
		businessOwnerRegistration.setProfileImage(profileImage);
		
		return businessOwnerRegistration;
		
	}

	

}
