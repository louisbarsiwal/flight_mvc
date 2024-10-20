package flightmanagement.app.dao;


import java.sql.Blob;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.TreePath;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import flightmanagement.app.entities.PassengerRegistration;
import flightmanagement.app.utilities.ByteArrayMultiPartFile;

public class PassengerRowMapper implements RowMapper<PassengerRegistration> {

	@Override
	public PassengerRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PassengerRegistration passengerRegistration = new PassengerRegistration();

		passengerRegistration.setPassenger_Id(rs.getInt("passenger_Id"));
		passengerRegistration.setFirstName(rs.getString("first_name"));
		passengerRegistration.setLastName(rs.getString("last_name"));
		passengerRegistration.setEmailId(rs.getString("email"));
		passengerRegistration.setMobileNo(rs.getString("mobile_no"));
		passengerRegistration.setAge(rs.getInt("age"));
		passengerRegistration.setGender(rs.getString("gender"));
        passengerRegistration.setUsername(rs.getString("username"));
		passengerRegistration.setPasswordSalt(rs.getString("password_salt"));
		passengerRegistration.setPasswordHash(rs.getString("password_hash"));		
		return passengerRegistration;
		
	}

	

}
