package flightmanagement.app.controller;

import java.io.IOException;




import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import flightmanagement.app.dao.BusinessOwnerDaoImpl;
import flightmanagement.app.dao.FlightManagerDaoImpl;
import flightmanagement.app.entities.BusinessOwnerRegistration;
import flightmanagement.app.entities.FlightManagerRegistration;

import flightmanagement.app.dao.PassengerDaoImpl;
import flightmanagement.app.entities.PassengerRegistration;


import flightmanagement.app.dao.FlightManagerDaoImpl;

import flightmanagement.app.entities.FlightManagerRegistration;


import flightmanagement.app.utilities.Password;
import flightmanagement.app.utilities.PermissionService;

import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
	


	

	private BusinessOwnerRegistration businessOwnerRegistration;

	private PassengerRegistration  passengerRegistration;
	private FlightManagerRegistration flightManagerRegistration;
	
<<<<<<< HEAD
	@Autowired
	BusinessOwnerDaoImpl businessOwnerDaoImpl;
	
	@Autowired
	PassengerDaoImpl passengerdaoImpl;

	private FlightManagerRegistration flightManagerRegistration;
=======

	private FlightManagerRegistration flightManagerRegistration;
	private PassengerRegistration  passengerRegistration;

	
	@Autowired
	BusinessOwnerDaoImpl businessOwnerDaoImpl;

	
	@Autowired	
	PassengerDaoImpl passengerdaoImpl;

	@Autowired
	BusinessOwnerDaoImpl businessOwnerDaoImpl;

>>>>>>> c28785d7b0456f9dc0f71b3af0bfb672df4b1680

	@Autowired
	FlightManagerDaoImpl flightManagerDaoImpl;
	
<<<<<<< HEAD
	@Autowired
    private PermissionService permissionService;
=======
	@GetMapping("/Bologout")
	public String Bologout() {
		return "bo_user_login";
	}
>>>>>>> c28785d7b0456f9dc0f71b3af0bfb672df4b1680


	@GetMapping("/openBoLoginPage")
	public String openBoLoginPage() {
		return "bo_user_login";
	}
	
	@GetMapping("/openforgotPasswordPage")
	public String openforgotPasswordPage() {
		return "bo_forgot_password";
	}
	

<<<<<<< HEAD

=======
	@PostMapping("/forgotPassword")
	public String forgotPassword(@RequestParam String username, 
	                             @RequestParam String password, 
	                             RedirectAttributes attributes) 
	                             throws IOException, SerialException, SQLException {
	    
	    // Fetch the BusinessOwnerRegistration based on email ID
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
	        return "redirect:/user/openForgotPasswordPage";
	    }
	}
>>>>>>> c28785d7b0456f9dc0f71b3af0bfb672df4b1680


	
	
	@GetMapping("/openForgotPasswordPage")
	public String openForgotPasswordPage() {
		return "bo_forgot_password";
	}

	
	@GetMapping("/openBoDashboard")
	public String openBoDashboard () {
		return "bo_dashboard";
	}
	@GetMapping("/openPassengerDashboard")
	public String openPassengerDashboard () {
		return "passenger_dashboard";
	}

	@GetMapping("/openBoRegistrationPage")
	public ModelAndView openRegistrationPage(ModelAndView modelAndView) {

		System.out.println("\n openBoRegistrationPage is called");
		modelAndView.setViewName("bo_user_registration");
		return modelAndView;
	}
	
	@GetMapping("/openViewProfilePage")
	public ModelAndView viewProfile(ModelAndView modelAndView) {
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
		
		try {
			businessOwnerRegistration = businessOwnerDaoImpl.modifyUser(updatedBo); // Simulate updating the user object
			attributes.addFlashAttribute("message", "Profile updated successfully");
		} catch(EmptyResultDataAccessException e) {
			attributes.addFlashAttribute("message", "Updation failed. Please try again later");
		}
		return "redirect:/user/openViewProfilePage"; // Redirect back to view profile
	}

	@GetMapping("/openPassengerProfilePage")
	public ModelAndView passengerProfile(ModelAndView modelAndView) {
		//PassengerRegistration passengerRegistration = new PassengerRegistration();
		modelAndView.setViewName("passenger_profile");
		modelAndView.addObject("passengerRegistration", passengerRegistration);
		return modelAndView;
	}
	@PostMapping("/passengerUpdateProfile")
	public String updatePassengerProfile(
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
	@GetMapping("/openPassengerRegistrationPage")
	public ModelAndView openPassengerRegistrationPage(ModelAndView modelAndView) {

		System.out.println("\n passengerRegistrationPage is called");
		modelAndView.setViewName("passenger_registration");
		return modelAndView;
	}

	@PostMapping("/Bologin")
	public String login(@RequestParam String username, 
			@RequestParam String password, 
			Model model, RedirectAttributes attributes) {

		System.out.println("\n login request data: " + username + ", " + password);

		try {
			businessOwnerRegistration = businessOwnerDaoImpl.fetchUser(username);
			

			String pwdSalt = businessOwnerRegistration .getPasswordSalt();
			String oldPwdHash = businessOwnerRegistration .getPasswordHash();
			System.out.println("old Password hash: "+oldPwdHash);

			String newPassword = password + pwdSalt;
			
			System.out.println("Password: "+newPassword);
			String newPwdHash = Password.generatePwdHash(newPassword);
			System.out.println("New Password hash: "+newPwdHash);

			if (newPwdHash.equals(oldPwdHash)) {
					
				model.addAttribute("businessOwnerRegistration ", businessOwnerRegistration );
				 return "redirect:/user/openBoDashboard";
				
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
		return "redirect:/user/openBoLoginPage";
		
	}
	
	@PostMapping("/Boregister")
	public String register(@ModelAttribute BusinessOwnerRegistration businessOwnerRegistration, RedirectAttributes attributes)
			throws IOException, SerialException, SQLException {


		// Password Encryption starts
		String passwordSalt = Password.generatePwdSalt(10);
		businessOwnerRegistration.setPasswordSalt(passwordSalt);

		// temporary data => password+salt
		String newPassword = businessOwnerRegistration.getPassword() + passwordSalt; // 1234rdvyjtftyf
		
		System.out.println("Password: "+newPassword);

		String passwordHash = Password.generatePwdHash(newPassword);
		
		businessOwnerRegistration.setPasswordHash(passwordHash);
		// Password Encryption completes
		System.out.println("Password hash: "+passwordHash);

		int result = businessOwnerDaoImpl.insertBusinessOwner(businessOwnerRegistration);

		if (result > 0) {
			attributes.addFlashAttribute("message", "Registration Successful");
			return "redirect:/user/openBoLoginPage";
		} else {
			attributes.addFlashAttribute("message", "Registration Failed");
			return "redirect:/user/openBoRegistrationPage";
		}

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

	@GetMapping("/openRegistrationPage")
	public String openRegistrationPage() {
		return "user_registration";
	}
	

	@GetMapping("/openFmLoginPage")
	public String openFMLoginPage()
	{

		return "fm_user_login";
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
	
//	@PostMapping("/Fmlogin")
//	public String loginFlightManager(@RequestParam String username, 
//			@RequestParam String password, 
//			Model model, RedirectAttributes attributes) {
//
//		System.out.println("\n Flight Manager login request data: " + username + ", " + password);
//
//		try {
//			flightManagerRegistration = flightManagerDaoImpl.fetchUser(username);
//			
//
//			String pwdSalt = flightManagerRegistration .getPasswordSalt();
//			String oldPwdHash = flightManagerRegistration .getPasswordHash();
//			System.out.println("old Password hash: "+oldPwdHash);
//
//			String newPassword = password + pwdSalt;
//			
//			System.out.println("Password: "+newPassword);
//			String newPwdHash = Password.generatePwdHash(newPassword);
//			System.out.println("New Password hash: "+newPwdHash);
//
//			if (newPwdHash.equals(oldPwdHash)) {
//				
//				model.addAttribute("flightManagerRegistration ", flightManagerRegistration );
//				 return "redirect:/user/openFmDashboard";
//				
//			}
//			else
//			{
//				attributes.addFlashAttribute("message", "Invalid password");
//				System.out.println("Invalid username or password");
//			}
//			
//		}
//
//			 catch (EmptyResultDataAccessException e) {
//			attributes.addFlashAttribute("message", "Incorrect Username");
//		}
//		return "redirect:/user/openFmLoginPage";
//		
//	}
//	
	@PostMapping("/Fmregister")
	public String registerFlightManager(@ModelAttribute FlightManagerRegistration flightManagerRegistration, RedirectAttributes attributes)
			throws IOException, SerialException, SQLException {


		// Password Encryption starts
		String passwordSalt = Password.generatePwdSalt(10);
		flightManagerRegistration.setPasswordSalt(passwordSalt);

		// temporary data => password+salt
		String newPassword = flightManagerRegistration.getPassword() + passwordSalt; // 1234rdvyjtftyf
		
		System.out.println("Password: "+newPassword);

		String passwordHash = Password.generatePwdHash(newPassword);
		
		flightManagerRegistration.setPasswordHash(passwordHash);
		// Password Encryption completes
		System.out.println("Password hash: "+passwordHash);

		int result = flightManagerDaoImpl.insertFlightManager(flightManagerRegistration);

		if (result > 0) {
			attributes.addFlashAttribute("message", "Registration Successful");
			return "redirect:/user/openFmLoginPage";
		} else {
			attributes.addFlashAttribute("message", "Registration Failed");
			return "redirect:/user/openFmRegistrationPage";
		}

	}
	
	
	@GetMapping("/openuserprofile")
	public String openUserProfilePage()
	{
		return "userprofile";
	}
	@GetMapping("/openusereditprofile")
	public String openusereditprofile() {
		return "user_editprofile";
	}
	@GetMapping("/openupdateflight")
	public String openupdateflight() {
		return "update_flight";
	}
	@GetMapping("/opencancelflight")
	public String opencancelflight() {
		return "cancel_flight";
	}
	@GetMapping("/openPassengerRegistration")
	public String openPassengerRegistration() {
		return "passenger_registration";
	}
	@GetMapping("/openPassengerLogin")
	public String openPassengerLogin() {
		return "passenger_login";
	}
<<<<<<< HEAD
	@GetMapping("/openRegistrationPage")
	public String openRegistrationPage() {
		return "user_registration";
	}
   @GetMapping("/openAccessControlPage") // Adjust the mapping as necessary
   public String showAccessControlPage(Model model) {
       List<FlightManagerRegistration> flightManagerRegistrations = flightManagerDaoImpl.findAllFlightManagers(); // Fetch the list from your DAO
       model.addAttribute("flightManagerRegistration", flightManagerRegistrations); // Set it as a model attribute
       return "access_control"; // This should match the name of your JSP file without the extension
       
   }
 
=======
	

>>>>>>> c28785d7b0456f9dc0f71b3af0bfb672df4b1680
	@GetMapping("/openFlightPage")
	public String openFlightPage() {
		return "Flight";
	}
	
	@PostMapping("/submitAccess")
	public String submitAccess(@RequestParam("flightManager") Integer flightManagerId, 
	                           @RequestParam("access") String access, 
	                           Model model) 
	{
		if ("grant".equals(access)) {
	        permissionService.grantPermissions(flightManagerId); // Updates the DB
	        model.addAttribute("successMessage", "Access granted successfully.");
	    } else if ("revoke".equals(access)) {
	        permissionService.revokePermissions(flightManagerId); // Updates the DB
	        model.addAttribute("successMessage", "Access revoked successfully.");
	    }

	    return "access_control"; // Return to the same JSP or redirect as needed
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
	        
	        Integer flightManagerId = flightManagerRegistration.getFmId(); // Get the flight manager ID

	        // Check if the flight manager is revoked
	        if (permissionService.isRevoked(flightManagerId)) {
	            attributes.addFlashAttribute("errorMessage", "Your access is not granted. Please wait for access.");
	            return "redirect:/user/openFmLoginPage"; // Redirect to the login page with an error message
	        }

	        String pwdSalt = flightManagerRegistration.getPasswordSalt();
	        String oldPwdHash = flightManagerRegistration.getPasswordHash();

	        // Combine the provided password with the salt and hash it
	        String newPassword = password + pwdSalt;
	        String newPwdHash = Password.generatePwdHash(newPassword);

	        // Compare hashed passwords
	        if (newPwdHash.equals(oldPwdHash)) {
	            model.addAttribute("flightManagerRegistration", flightManagerRegistration);
	            return "redirect:/user/openFmDashboard"; // Redirect to the dashboard
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

	}



