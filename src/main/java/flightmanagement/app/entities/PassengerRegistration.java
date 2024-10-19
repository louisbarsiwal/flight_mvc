package flightmanagement.app.entities;


import org.springframework.web.multipart.MultipartFile;

public class PassengerRegistration 
{
	
	private int passenger_Id;
	private String firstName;
	private String lastName;
	private String emailId;
	private String mobileNo;
	private int age;
	private String gender;
	private String username;
	private String password;
	private String passwordSalt;
	private String passwordHash;
	private MultipartFile profileImage;
	private String confirmPassword;
	
	
	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public PassengerRegistration() {
		super();
		
	}


	public PassengerRegistration(int passenger_Id, String firstName, String lastName, String emailId, String mobileNo,
			int age, String gender, String username, String password, String passwordSalt, String passwordHash,
			MultipartFile profileImage) {
		super();
		this.passenger_Id = passenger_Id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.age = age;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
		this.profileImage = profileImage;
	}


	public int getPassenger_Id() {
		return passenger_Id;
	}


	public void setPassenger_Id(int passenger_Id) {
		this.passenger_Id = passenger_Id;
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


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
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


	@Override
	public String toString() {
		return "PassengerRegistration [passenger_Id=" + passenger_Id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailId=" + emailId + ", mobileNo=" + mobileNo + ", age=" + age + ", gender=" + gender
				+ ", username=" + username + ", password=" + password + ", passwordSalt=" + passwordSalt
				+ ", passwordHash=" + passwordHash + ", profileImage=" + profileImage + "]";
	}


	


}