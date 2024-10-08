package flightmanagement.app.controller;

import java.io.IOException;




import java.sql.SQLException;

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
	
	@GetMapping("/Bologout")
	public String Bologout() {
		return "bo_user_login";
	}


	@GetMapping("/openBoLoginPage")
	public String openBoLoginPage() {
		return "bo_user_login";
	}
	
	@GetMapping("/openforgotPasswordPage")
	public String openforgotPasswordPage() {
		return "bo_forgot_password";
	}
	

	@PostMapping("/forgotPassword")
	public String forgotPassword(@RequestParam String username, 
	                             @RequestParam String password, 
	                             RedirectAttributes attributes) 
	                             throws IOException, SerialException, SQLException {
	    
	    // Fetch the BusinessOwnerRegistration based on username
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


	
	
	@GetMapping("/openForgotPasswordPage")
	public String openForgotPasswordPage() {
		return "bo_forgot_password";
	}

	
	@GetMapping("/openBoDashboard")
	public String openBoDashboard () {
		return "bo_dashboard";
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
    public String fmforgotPassword(@RequestParam String username, 
                                 @RequestParam String password, 
                                 RedirectAttributes attributes) 
                                 throws IOException, SerialException, SQLException {
        try {
    	
    	System.out.println(username);
    	
        FlightManagerRegistration flightManagerRegistration = flightManagerDaoImpl.fetchUser(username);

        String passwordSalt = Password.generatePwdSalt(10);
        flightManagerRegistration.setPasswordSalt(passwordSalt);

        String newPassword = password + passwordSalt;
        String passwordHash = Password.generatePwdHash(newPassword);
        flightManagerRegistration.setPasswordHash(passwordHash);
        
     // Log the values for debugging
	    System.out.println("Updating password for flightManager_id: " + flightManagerRegistration.getflightManagerId());
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

    @GetMapping("/openFmViewProfilePage")
    public ModelAndView fmviewProfile(ModelAndView modelAndView) {
        modelAndView.setViewName("fm_view_profile");
        modelAndView.addObject("flightManagerRegistration", flightManagerRegistration);
        return modelAndView;
    }
    
    
    
    

    @PostMapping("/fmUpdateProfile")
    public String UpdateProfile(
            @ModelAttribute FlightManagerRegistration updatedFm,
            RedirectAttributes attributes
            ) throws SerialException, IOException, SQLException {
        
        try {
            flightManagerRegistration = flightManagerDaoImpl.modifyUser(updatedFm);
            attributes.addFlashAttribute("message", "Profile updated successfully");
        } catch (EmptyResultDataAccessException e) {
            attributes.addFlashAttribute("message", "Update failed. Please try again later.");
        }
        return "redirect:/user/openFmViewProfilePage";
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
	
	@PostMapping("/Fmlogin")
	public String loginFlightManager(@RequestParam String username, 
			@RequestParam String password, 
			Model model, RedirectAttributes attributes) {

		System.out.println("\n Flight Manager login request data: " + username + ", " + password);

		try {
			flightManagerRegistration = flightManagerDaoImpl.fetchUser(username);
			

			String pwdSalt = flightManagerRegistration .getPasswordSalt();
			String oldPwdHash = flightManagerRegistration .getPasswordHash();
			System.out.println("old Password hash: "+oldPwdHash);

			String newPassword = password + pwdSalt;
			
			System.out.println("Password: "+newPassword);
			String newPwdHash = Password.generatePwdHash(newPassword);
			System.out.println("New Password hash: "+newPwdHash);

			if (newPwdHash.equals(oldPwdHash)) {
				
				model.addAttribute("flightManagerRegistration ", flightManagerRegistration );
				 return "redirect:/user/openFmDashboard";
				
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
		return "redirect:/user/openFmLoginPage";
		
	}
	
	@PostMapping("/openFmRegistrationPage")
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

	@GetMapping("/openRegistrationPage")
	public String openRegistrationPage() {
		return "user_registration";
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
	

	@GetMapping("/openFlightPage")
	public String openFlightPage() {
		return "Flight";
	}
	@GetMapping("/openBookingHistoryPage")
	public String openBookingHistoryPage() {
		return "booking_history";
	}
	@GetMapping("/openBookingTicketPage")
	public String openBookingTicketPage() {
		return "book_tickets";
	}
	@GetMapping("/openCancelledTicketPage")
	public String openCancelledTicketPage() {
		return "cancelled_tickets";
	}

}
