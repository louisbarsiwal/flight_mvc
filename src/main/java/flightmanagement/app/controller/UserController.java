package flightmanagement.app.controller;

import java.io.IOException;




import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import flightmanagement.app.dao.AddedFlightDaoImpl;
import flightmanagement.app.dao.BusinessOwnerDaoImpl;
import flightmanagement.app.dao.FlightManagerDaoImpl;
import flightmanagement.app.entities.AddedFlight;
import flightmanagement.app.entities.BusinessOwnerRegistration;
import flightmanagement.app.entities.FlightManagerRegistration;

import flightmanagement.app.dao.PassengerDaoImpl;
import flightmanagement.app.entities.PassengerRegistration;
import flightmanagement.app.utilities.Password;
import flightmanagement.app.utilities.PermissionService;

import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
	

    private BusinessOwnerRegistration businessOwnerRegistration;
	private PassengerRegistration  passengerRegistration;
	private FlightManagerRegistration flightManagerRegistration;
	
	
	@Autowired
	BusinessOwnerDaoImpl businessOwnerDaoImpl;

	@Autowired	
	PassengerDaoImpl passengerdaoImpl;


	@Autowired
	FlightManagerDaoImpl flightManagerDaoImpl;
	
	@Autowired
	AddedFlightDaoImpl addedFlightDaoImpl;

	@Autowired
    private PermissionService permissionService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/Bologout")
	public String Bologout() {
		return "bo_user_login";
	}
	@GetMapping("/passengerlogout")
	public String passenger_logout() {
		return "passenger_login";
	}
  

	@GetMapping("/openBoLoginPage")
	public String openBoLoginPage() {
		return "bo_user_login";
	}
	
	@GetMapping("/openBoRegistrationPage")
	public ModelAndView openBoRegistrationPage(ModelAndView modelAndView) {
		 
		System.out.println("\n openBoRegistrationPage is called");
		modelAndView.setViewName("bo_user_registration");
		return modelAndView;
	}
	

	@PostMapping("/forgotPassword")
	public String forgotPassword(@RequestParam String username, 
	                             @RequestParam String password, 
	                             @RequestParam String confirmPassword, 
	                             RedirectAttributes attributes) 
	                             throws IOException, SerialException, SQLException {
	    
		//String password = businessOwnerRegistration.getPassword();
	    //String confirmPassword = businessOwnerRegistration.getConfirmPassword();
		 if (!password.equals(confirmPassword)) {
		        attributes.addFlashAttribute("message", "Passwords do not match.");
		        return "redirect:/user/openForgotPasswordPage";
		 }
		 
		 try {
			 
		
	    BusinessOwnerRegistration businessOwnerRegistration = businessOwnerDaoImpl.fetchUser(username);
	    
	    
	    // Proceed with password hashing
	    String passwordSalt = Password.generatePwdSalt(10);
	    businessOwnerRegistration.setPasswordSalt(passwordSalt);
	    
	    String newPassword = password + passwordSalt; // Combine password and salt
	    String passwordHash = Password.generatePwdHash(newPassword);
	    businessOwnerRegistration.setPasswordHash(passwordHash);
	    
	    // Log the values for debugging
	    System.out.println("Updating password for businessOwner_id: " + businessOwnerRegistration.getBoId());
	    System.out.println("New password salt: " + passwordSalt);
	    System.out.println("New password hash: " + passwordHash);

	    // Update the password
	    int result = businessOwnerDaoImpl.updateBusinessOwnerPassword(businessOwnerRegistration);
	    
	    // Check the result of the update operation
	    if (result > 0) {
	        attributes.addFlashAttribute("message", "New Password updated successfully");
	        return "redirect:/user/openBoLoginPage";
	    } else {
	        attributes.addFlashAttribute("message", "New Password not updated succesfully");
	        return "redirect:/user/openBoForgotPasswordPage";
	    }
		}
		 catch (EmptyResultDataAccessException e) {
				attributes.addFlashAttribute("message", "Incorrect Username!Please Enter the Correct Username");
				 return "redirect:/user/openBoForgotPasswordPage";
	}
	}
	@PostMapping("/openPassengerForgotPage")
	public String openPassengerForgotPage(@RequestParam String username, 
	                             @RequestParam String password, 
	                             @RequestParam String confirmPassword, 
	                             RedirectAttributes attributes) 
	                             throws IOException, SerialException, SQLException {
	    
		 if (!password.equals(confirmPassword)) {
		        attributes.addFlashAttribute("message", "Passwords do not match.");
		        return "redirect:/user/PassengerForgotPage";
		 }
		 
		 try {
		 
		PassengerRegistration passengeRegistration = passengerdaoImpl.fetchUser(username);
	    
	    
	    // Proceed with password hashing
	    String passwordSalt = Password.generatePwdSalt(10);
	    passengeRegistration.setPasswordSalt(passwordSalt);
	    
	    String newPassword = password + passwordSalt; // Combine password and salt
	    String passwordHash = Password.generatePwdHash(newPassword);
	    passengeRegistration.setPasswordHash(passwordHash);
	    
	    // Log the values for debugging
	    System.out.println("Updating password for passenger_id: " + passengeRegistration.getPassenger_Id());
	    System.out.println("New password salt: " + passwordSalt);
	    System.out.println("New password hash: " + passwordHash);

	    // Update the password
	    int result = passengerdaoImpl.updatePassengerPassword(passengeRegistration);
	    
	    // Check the result of the update operation
	    if (result > 0) {
	        attributes.addFlashAttribute("message", "New Password updated successfully");
	        return "redirect:/user/openPassengerLogin";
	    } else {
	        attributes.addFlashAttribute("message", "New Password not updated succesfully");
	        return "redirect:/user/PassengerForgotPage";
	    }
		 }
		 catch (EmptyResultDataAccessException e) {
				attributes.addFlashAttribute("message", "Incorrect Username!Please Enter the Correct Username");
				 return "redirect:/user/PassengerForgotPage";
	}
	}

	@GetMapping("/openBoForgotPasswordPage")
	public String openForgotPasswordPage() {
		return "bo_forgot_password";
	}
	@GetMapping("/PassengerForgotPage")
	public String PassengerForgotPage() {
		return "passenger_forgot_password";
	}
	@GetMapping("/openBoDashboard")
	public String openBoDashboard () {
		return "bo_dashboard";
	}
	
	@GetMapping("/openBoViewProfilePage")
	public ModelAndView openBoViewProfilePage(ModelAndView modelAndView) throws IOException {
		businessOwnerRegistration.setImage(businessOwnerRegistration.getProfileImage().getInputStream());
		modelAndView.setViewName("bo_view_profile");
		modelAndView.addObject("businessOwnerRegistration", businessOwnerRegistration);
		return modelAndView;
	}
	
	
	@PostMapping("/boUpdateProfile")
	public String boupdateProfile(
			@ModelAttribute BusinessOwnerRegistration updatedBo,
			RedirectAttributes attributes
			) throws SerialException, IOException, SQLException {
		// Update user information in the database
		
		String firstName = updatedBo.getFirstName();
	    String lastName = updatedBo.getLastName();
	    String email = updatedBo.getEmailId();
	    String mobileNo = updatedBo.getMobileNo();
	    
	    if (!firstName.matches("^[a-zA-Z]{3,20}$")) {
	        attributes.addFlashAttribute("message", "First name must be between 3-20 characters and contain only alphabets.");
	        return "redirect:/user/openViewProfilePage";
	    }
	    if (!lastName.matches("^[a-zA-Z]{3,20}$")) {
	        attributes.addFlashAttribute("message", "Last name must be between 3-20 characters and contain only alphabets.");
	        return "redirect:/user/openViewProfilePage";
	    }
	    if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
	        attributes.addFlashAttribute("message", "Email must be in the format of example@gmail.com.");
	        return "redirect:/user/openViewProfilePage";
	    }
	    if (!mobileNo.matches("^\\d{10}$")) {
	        attributes.addFlashAttribute("message", "Phone number must be 10 digits.");
	        return "redirect:/user/openViewProfilePage";
	    }
	    
		try {
			
			businessOwnerRegistration = businessOwnerDaoImpl.modifyUser(updatedBo); 
			
			businessOwnerRegistration.setImage(businessOwnerRegistration.getProfileImage().getInputStream());
			attributes.addFlashAttribute("message", "Profile updated successfully");
		} catch(EmptyResultDataAccessException e) {
			attributes.addFlashAttribute("message", "Updation failed. Please try again later");
		}
		return "redirect:/user/openViewProfilePage"; // Redirect back to view profile
	}



	@GetMapping("/openPassengerProfilePage")
	public ModelAndView openPassengerProfilePage(ModelAndView modelAndView) {
		//PassengerRegistration passengerRegistration = new PassengerRegistration();
		
		modelAndView.setViewName("passenger_profile");
		modelAndView.addObject("passengerRegistration", passengerRegistration);
		return modelAndView;
	}
	@PostMapping("/passengerUpdateProfile")
	public String passengerUpdateProfile(
			@ModelAttribute PassengerRegistration updatedPassenger,
			RedirectAttributes attributes
			) throws SerialException, IOException, SQLException {
		// Update user information in the database
		
		try {
			passengerRegistration = passengerdaoImpl.modifyPassengerProfile(updatedPassenger); // Simulate updating the user object
			attributes.addAttribute("message", "Profile updated successfully");
		} catch(EmptyResultDataAccessException e) {
			attributes.addAttribute("message", "Updation failed. Please try again later");
		}
		return "redirect:/user/openPassengerProfilePage"; // Redirect back to view profile
	}
	
	@GetMapping("/openFmViewProfilePage")
	public ModelAndView openFmViewProfilePage(ModelAndView modelAndView) {
		
		
		modelAndView.setViewName("fm_view_profile");
		modelAndView.addObject("flightManagerRegistration", flightManagerRegistration);
		return modelAndView;
	}
	
	@PostMapping("/FmEditProfile")
	public String FmEditProfile(
			@ModelAttribute FlightManagerRegistration updatedFm,
			RedirectAttributes attributes
			) throws SerialException, IOException, SQLException {
		// Update user information in the database
		
		try {
			flightManagerRegistration = flightManagerDaoImpl.modifyUser(flightManagerRegistration); // Simulate updating the user object
			attributes.addAttribute("message", "Profile updated successfully");
		} catch(EmptyResultDataAccessException e) {
			attributes.addAttribute("message", "Updation failed. Please try again later");
		}
		return "redirect:/user/openFmViewProfilePage"; // Redirect back to view profile
	}



	@PostMapping("/Bologin")
	public String login(@RequestParam String username, 
			@RequestParam String password, 
			ModelMap modelMap, RedirectAttributes attributes) {

		System.out.println("\n login request data: " + username + ", " + password);

		try {
			businessOwnerRegistration = businessOwnerDaoImpl.fetchUser(username);
			businessOwnerRegistration.setImage(businessOwnerRegistration.getProfileImage().getInputStream());

			String pwdSalt = businessOwnerRegistration .getPasswordSalt();
			String oldPwdHash = businessOwnerRegistration .getPasswordHash();
			System.out.println("old Password hash: "+oldPwdHash);

			String newPassword = password + pwdSalt;
			
			System.out.println("Password: "+newPassword);
			String newPwdHash = Password.generatePwdHash(newPassword);
			System.out.println("New Password hash: "+newPwdHash);

			if (newPwdHash.equals(oldPwdHash)) {
					
				modelMap.addAttribute("businessOwnerRegistration ", businessOwnerRegistration );
				 return "redirect:/user/openBoDashboard";
				
			}
			else
			{
				attributes.addFlashAttribute("message", "Invalid password");
				System.out.println("Invalid username or password");
			}
			
		}

			 catch (EmptyResultDataAccessException e) {
			attributes.addFlashAttribute("message", "Incorrect Username!Incorrect Username!Please Enter the Correct Username");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			attributes.addFlashAttribute("message", " Oops something went wrong");
		}
		return "redirect:/user/openBoLoginPage";
		
	}
		
	
	@PostMapping("/Boregister")
	public String register(@ModelAttribute BusinessOwnerRegistration businessOwnerRegistration, RedirectAttributes attributes)
	        throws IOException, SerialException, SQLException {

	    // Validation checks
	    String firstName = businessOwnerRegistration.getFirstName();
	    String lastName = businessOwnerRegistration.getLastName();
	    String email = businessOwnerRegistration.getEmailId();
	    String mobileNo = businessOwnerRegistration.getMobileNo();
	    String username = businessOwnerRegistration.getUsername();
	    String password = businessOwnerRegistration.getPassword();
	    String confirmPassword = businessOwnerRegistration.getConfirmPassword();

	    if (!firstName.matches("^[a-zA-Z]{3,20}$")) {
	        attributes.addFlashAttribute("message", "First name must be between 3-20 characters and contain only alphabets.");
	        return "redirect:/user/openBoRegistrationPage";
	    }
	    if (!lastName.matches("^[a-zA-Z]{3,20}$")) {
	        attributes.addFlashAttribute("message", "Last name must be between 3-20 characters and contain only alphabets.");
	        return "redirect:/user/openBoRegistrationPage";
	    }
	    if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
	        attributes.addFlashAttribute("message", "Email must be in the format of example@gmail.com.");
	        return "redirect:/user/openBoRegistrationPage";
	    }
	    if (!mobileNo.matches("^\\d{10}$")) {
	        attributes.addFlashAttribute("message", "Phone number must be 10 digits.");
	        return "redirect:/user/openBoRegistrationPage";
	    }
	    if (!username.matches("^[a-zA-Z0-9_]{6,15}$")) {
	        attributes.addFlashAttribute("message", "Username must be between 6-15 characters and contain only alphabets, numbers, and underscores.");
	        return "redirect:/user/openBoRegistrationPage";
	    }

	    if (!password.equals(confirmPassword)) {
	        attributes.addFlashAttribute("message", "Passwords do not match.");
	        return "redirect:/user/openBoRegistrationPage";
	    }

	    // Password Encryption starts
	    String passwordSalt = Password.generatePwdSalt(10);
	    businessOwnerRegistration.setPasswordSalt(passwordSalt);

	    String newPassword = password + passwordSalt;
	    String passwordHash = Password.generatePwdHash(newPassword);
	    businessOwnerRegistration.setPasswordHash(passwordHash);

	    int result = businessOwnerDaoImpl.insertBusinessOwner(businessOwnerRegistration);
	  

	    if (result > 0) {
	        attributes.addFlashAttribute("message", "Registration Successful");
	        return "redirect:/user/openBoLoginPage";
	    } else {
	        attributes.addFlashAttribute("message", "Registration Failed");
	        return "redirect:/user/openBoRegistrationPage";
	    }
	}



	@GetMapping("/Fmlogout")
	public String Fmlogout() {
		return "fm_user_login";
	}

	@GetMapping("/openFmLoginPage")
	public String openFMLoginPage(){
		return "fm_user_login";
	}
	
	@GetMapping("/openFmForgotPasswordPage")
    public String openFmForgotPasswordPage() {
        return "fm_forgot_password";
    }
	
    @PostMapping("/fmforgotPassword")
    public String fmforgotPassword(@RequestParam("username") String username, 
                                   @RequestParam("password") String password,
                                   @RequestParam("confirmPassword") String confirmPassword,
                                   
                                   
                                    RedirectAttributes attributes) 
                                 throws IOException, SerialException, SQLException {
    	if (!password.equals(confirmPassword)) {
	        attributes.addFlashAttribute("message", "Passwords do not match.");
	        return "redirect:/user/openFmForgotPasswordPage";
    		}
        try {
    	
    	System.out.println(username);
    	
        FlightManagerRegistration flightManagerRegistration = flightManagerDaoImpl.fetchUser(username);
        System.out.println(flightManagerRegistration);

        String passwordSalt = Password.generatePwdSalt(10);
        flightManagerRegistration.setPasswordSalt(passwordSalt);

        String newPassword = password + passwordSalt;
        String passwordHash = Password.generatePwdHash(newPassword);
        flightManagerRegistration.setPasswordHash(passwordHash);
        
     // Log the values for debugging
	    System.out.println("Updating password for flightManager_id: " + flightManagerRegistration.getFlightManagerId());
	    System.out.println("New password salt: " + passwordSalt);
	    System.out.println("New password hash: " + passwordHash);

	    // Update the password
	    

        int result = flightManagerDaoImpl.updateFlightManagerPassword(flightManagerRegistration);

        if (result > 0) {
            attributes.addFlashAttribute("message", "New Password updated successfully");
            return "redirect:/user/openFmLoginPage";
        } else {
            attributes.addFlashAttribute("message", "Password update failed");
            return "redirect:/user/openFmForgotPasswordPage";
        }
        }catch (EmptyResultDataAccessException e) {
			attributes.addFlashAttribute("message", "Incorrect");
			return "redirect:/user/openFmForgotPasswordPage";
		}
    }

    

	
	@GetMapping("/openFmDashboard")
	public String openFmDashboard () {
		return "fm_dashboard";
	}
 
	@GetMapping("/openFmRegistrationPage")
	public ModelAndView openFmManagerRegistrationPage(ModelAndView modelAndView) {
 
		System.out.println("\n openFmRegistrationPage is called");
		modelAndView.setViewName("fm_user_registration");
		return modelAndView;
	}
	
	@PostMapping("/fmregister")
	public String register(@ModelAttribute FlightManagerRegistration flightManagerRegistration, RedirectAttributes attributes)
	        throws IOException, SerialException, SQLException {

	    // Validation checks
	    String firstName = flightManagerRegistration.getFirstName();
	    String lastName = flightManagerRegistration.getLastName();
	    String email = flightManagerRegistration.getEmailId();
	    String mobileNo = flightManagerRegistration.getMobileNo();
	    String username = flightManagerRegistration.getUsername();
	    String password = flightManagerRegistration.getPassword();
	    String confirmPassword = flightManagerRegistration.getConfirmPassword();

	    if (!firstName.matches("^[a-zA-Z]{3,20}$")) {
	        attributes.addFlashAttribute("message", "First name must be between 3-20 characters and contain only alphabets.");
	        return "redirect:/user/openFmRegistrationPage";
	    }
	    if (!lastName.matches("^[a-zA-Z]{3,20}$")) {
	        attributes.addFlashAttribute("message", "Last name must be between 3-20 characters and contain only alphabets.");
	        return "redirect:/user/openFmRegistrationPage";
	    }
	    if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
	        attributes.addFlashAttribute("message", "Email must be in the format of example@gmail.com.");
	        return "redirect:/user/openFmRegistrationPage";
	    }
	    if (!mobileNo.matches("^\\d{10}$")) {
	        attributes.addFlashAttribute("message", "Phone number must be 10 digits.");
	        return "redirect:/user/openFmRegistrationPage";
	    }
	    if (!username.matches("^[a-zA-Z0-9_]{6,15}$")) {
	        attributes.addFlashAttribute("message", "Username must be between 6-15 characters and contain only alphabets, numbers, and underscores.");
	        return "redirect:/user/openFmRegistrationPage";
	    }

	    if (!password.equals(confirmPassword)) {
	        attributes.addFlashAttribute("message", "Passwords do not match.");
	        return "redirect:/user/openFmRegistrationPage";
	    }

	    // Password Encryption starts
	    String passwordSalt = Password.generatePwdSalt(10);
	    flightManagerRegistration.setPasswordSalt(passwordSalt);

	    String newPassword = password + passwordSalt;
	    String passwordHash = Password.generatePwdHash(newPassword);
	    flightManagerRegistration.setPasswordHash(passwordHash);

	    int result = flightManagerDaoImpl.insertFlightManager(flightManagerRegistration);
	  
	    if (result > 0) {
	        attributes.addFlashAttribute("message", "Registration Successful");
	        return "redirect:/user/openFmLoginPage";
	    } else {
	        attributes.addFlashAttribute("message", "Registration Failed");
	        return "redirect:/user/openFmRegistrationPage";
	    }
	}

	 


	@GetMapping("/openPassengerRegistrationPage")
	public ModelAndView openPassengerRegistrationPage(ModelAndView modelAndView) {

		System.out.println("\n passengerRegistrationPage is called");
		modelAndView.setViewName("passenger_registration");
		return modelAndView;
	}
	
	@GetMapping("/openPassengerDashboard")
	public String openPassengerDashboard () {
		return "passenger_dashboard";
	}

	@PostMapping("/passengerregister")
	public String  passengerRegister(@ModelAttribute PassengerRegistration passengerRegistration, RedirectAttributes attributes)
			throws IOException, SerialException, SQLException {


		// Password Encryption starts
		String passwordSalt = Password.generatePwdSalt(10);
		passengerRegistration.setPasswordSalt(passwordSalt);

		// temporary data => password+salt
		String newPassword = passengerRegistration.getPassword() + passwordSalt; // 1234rdvyjtftyf
		
		System.out.println("Password: "+newPassword);

		String passwordHash = Password.generatePwdHash(newPassword);
		
		passengerRegistration.setPasswordHash(passwordHash);
		// Password Encryption completes
		System.out.println("Password hash: "+passwordHash);

		int result = passengerdaoImpl.insertPassenger(passengerRegistration);

		if (result > 0) {
			attributes.addFlashAttribute("message", "Registration Successful");
			return "redirect:/user/openPassengerLogin";
		} else {
			attributes.addFlashAttribute("message", "Registration Failed");
			return "redirect:/user/openRegistrationPage";
		}

	}
	@PostMapping("/passengerlogin")
	public String passengerlogin(@RequestParam String username, 
			@RequestParam String password, 
			Model model, RedirectAttributes attributes) {

		System.out.println("\n login request data: " + username + ", " + password);

		try {
			passengerRegistration =  passengerdaoImpl.fetchUser(username);
			

			String pwdSalt = passengerRegistration.getPasswordSalt();
			String oldPwdHash = passengerRegistration.getPasswordHash();
			System.out.println("old Password hash: "+oldPwdHash);

			String newPassword = password + pwdSalt;
			
			System.out.println("Password: "+newPassword);
			String newPwdHash = Password.generatePwdHash(newPassword);
			System.out.println("New Password hash: "+newPwdHash);

			if (newPwdHash.equals(oldPwdHash)) {
					
				model.addAttribute("passengerRegistration ",passengerRegistration);
				 return "redirect:/user/openPassengerDashboard";
				
			}
			else
			{
				attributes.addFlashAttribute("message", "Invalid password");
				System.out.println("Invalid username or password");
			}
			
		}

			 catch (EmptyResultDataAccessException e) {
			attributes.addFlashAttribute("message", "Incorrect Username");
		}
		return "redirect:/user/openPassengerLogin";
		
	}

	@GetMapping("/openPassengerLogin")
	public String openPassengerLogin() {
		return "passenger_login";
	}


	@PostMapping("/Fmlogin")
	public String loginFlightManager(@RequestParam("username") String username, 
	                                 @RequestParam("password") String password, 
	                                 Model model, RedirectAttributes attributes) {

	    System.out.println("\nFlight Manager login request data: " + username + ", " + password);
	    FlightManagerRegistration flightManagerRegistration;

	    try {
	        // Fetch the flight manager registration based on the username
	        flightManagerRegistration = flightManagerDaoImpl.fetchUser(username);
	        
	        Integer flightManagerId = flightManagerRegistration.getFlightManagerId(); // Get the flight manager ID

	        // Check if the flight manager's access is revoked
	        if (permissionService.isRevoked(flightManagerId)) {
	            // Pop-up style message indicating access is not granted
	            attributes.addFlashAttribute("errorMessage", "Your access is not granted. Please wait for access.");
	            return "redirect:/user/openFmLoginPage"; // Redirect to login page with error message
	        }

	        String pwdSalt = flightManagerRegistration.getPasswordSalt();
	        String oldPwdHash = flightManagerRegistration.getPasswordHash();

	        // Combine the provided password with the salt and hash it
	        String newPassword = password + pwdSalt;
	        String newPwdHash = Password.generatePwdHash(newPassword);

	        // Compare hashed passwords
	        if (newPwdHash.equals(oldPwdHash)) {
	            model.addAttribute("flightManagerRegistration", flightManagerRegistration);
	            return "redirect:/user/openFmDashboard"; // Redirect to the flight manager dashboard
	        } else {
	            attributes.addFlashAttribute("message", "Invalid password");
	        }
	    } catch (EmptyResultDataAccessException e) {
	        attributes.addFlashAttribute("message", "Incorrect Username");
	    } catch (Exception e) {
	        attributes.addFlashAttribute("message", "An error occurred. Please try again.");
	    }

	    return "redirect:/user/openFmLoginPage"; // Redirect back to the login page
	}


	@GetMapping("/openAccessControlPage")
	public String openAccessControlPage(Model model) {
	    List<Map<String, Object>> flightManagers = flightManagerDaoImpl.findAllFlightManager();
	    model.addAttribute("flightManagerRegistration", flightManagers);
	    return "access_control"; // Ensure this matches the name of your JSP file without extension
	}

	 @PostMapping("/grantAccess")
	 public String grantAccess(@RequestParam("flightManagerId") Integer flightManagerId, 
	                           RedirectAttributes attributes) {
	     try {
	         permissionService.grantPermissions(flightManagerId); // Call service to grant access
	         attributes.addFlashAttribute("successMessage", "Access granted successfully.");
	     } catch (Exception e) {
	         attributes.addFlashAttribute("errorMessage", "Failed to grant access.");
	     }
	     return "redirect:/user/openAccessControlPage"; // Redirect back to the access control page
	 }
	 @PostMapping("/revokeAccess")
	 public String revokeAccess(@RequestParam("flightManagerId") Integer flightManagerId, 
	                            RedirectAttributes attributes) {
	     try {
	         permissionService.revokePermissions(flightManagerId); // Call service to revoke access
	         attributes.addFlashAttribute("successMessage", "Access revoked successfully.");
	     } catch (Exception e) {
	         attributes.addFlashAttribute("errorMessage", "Failed to revoke access.");
	     }
	     return "redirect:/user/openAccessControlPage"; // Redirect back to the access control page
	 }
	
	}
	
	



