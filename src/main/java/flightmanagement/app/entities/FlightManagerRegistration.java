package flightmanagement.app.entities;


import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class FlightManagerRegistration 
{

	private int flightManagerId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String mobileNo;
	private Date dateOfBirth;
	private String gender;
	private String username;
	private String password;
	private String passwordSalt;
	private String passwordHash;
	private MultipartFile profileImage;
	private String confirmPassword;
	
	public FlightManagerRegistration() {
		super();
	}

	public FlightManagerRegistration(int flightManagerId, String firstName, String lastName, String emailId,
			String mobileNo, Date dateOfBirth, String gender, String username, String password, String passwordSalt,
			String passwordHash, MultipartFile profileImage) {
		super();
		this.flightManagerId = flightManagerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
		this.profileImage = profileImage;
	}

	public FlightManagerRegistration(String firstName, String lastName, String emailId, String mobileNo,
			Date dateOfBirth, String gender, String username, String password, String passwordSalt, String passwordHash,
			MultipartFile profileImage) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
		this.profileImage = profileImage;
	}

	public int getFlightManagerId() {
		return flightManagerId;
	}

	public void setFlightManagerId(int flightManagerId) {
		this.flightManagerId = flightManagerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public MultipartFile getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(MultipartFile profileImage) {
		this.profileImage = profileImage;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	@Override
	public String toString() {
		return "\n FlightManagerRegistration [flightManagerId=" + flightManagerId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", emailId=" + emailId + ", mobileNo=" + mobileNo + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", username=" + username + ", password=" + password
				+ ", passwordSalt=" + passwordSalt + ", passwordHash=" + passwordHash + ", profileImage=" + profileImage
				+ "]";
	}

	
	
}



	