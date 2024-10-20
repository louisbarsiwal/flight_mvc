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



@Repository
public class BusinessOwnerDaoImpl implements BusinessOwnerDao {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	

	private Blob getBlob(MultipartFile image) throws IOException, SerialException, SQLException {
		byte[] byteArr = image.getBytes();
		Blob imageBlob = new SerialBlob(byteArr);
		return imageBlob;
	}

	@Override
	public BusinessOwnerRegistration fetchUser(String username) {

		String sql = "SELECT * FROM admin_businessowner WHERE user_name = ?";

		return jdbcTemplate.queryForObject(sql, new BoRowMapper(), username);
	}


	@Override
	public BusinessOwnerRegistration modifyUser(BusinessOwnerRegistration businessOwnerRegistration)
			throws SerialException, IOException, SQLException {
		Blob profileImage = getBlob(businessOwnerRegistration.getProfileImage());

		String query = "UPDATE admin_businessowner SET first_name = ?, last_name = ?, email_id = ?, "
				+ "mobile_no = ?, date_of_birth = ?, gender = ?, profile_image = ? WHERE businessOwner_id = ?";

		jdbcTemplate.update(query, businessOwnerRegistration.getFirstName(), businessOwnerRegistration.getLastName(), businessOwnerRegistration.getEmailId(),
				businessOwnerRegistration.getMobileNo(),
				businessOwnerRegistration.getDateOfBirth(),businessOwnerRegistration.getGender(), profileImage, businessOwnerRegistration.getBoId());
		
		return getUserById(businessOwnerRegistration.getBoId());
		
		
	}
	

	@Override
	public BusinessOwnerRegistration getUserById(int boId) {
		String sql = "SELECT * FROM admin_businessowner WHERE businessOwner_id = ?";
		return jdbcTemplate.queryForObject(sql, new BoRowMapper(), boId);
		
	}
	


	@Override
	public int updateBusinessOwnerPassword(BusinessOwnerRegistration businessOwnerRegistration)
			throws IOException, SerialException, SQLException {
		String query = "UPDATE admin_businessowner SET password_salt = ?, password_hash = ? WHERE businessOwner_id= ?";

		return jdbcTemplate.update(query,businessOwnerRegistration.getPasswordSalt(),businessOwnerRegistration.getPasswordHash(),
				businessOwnerRegistration.getBoId() );	
	}
	
	


	

}
	
	



