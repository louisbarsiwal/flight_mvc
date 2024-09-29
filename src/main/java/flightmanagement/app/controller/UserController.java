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

import flightmanagement.app.entities.BusinessOwnerRegistration;

import flightmanagement.app.utilities.Password;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private BusinessOwnerRegistration businessOwnerRegistration;
	
	@Autowired
	BusinessOwnerDaoImpl businessOwnerDaoImpl;

	@GetMapping("/openBoLoginPage")
	public String openLoginPage() {
		return "bo_user_login";
	}

	@GetMapping("/openBoRegistrationPage")
	public ModelAndView openRegistrationPage(ModelAndView modelAndView) {

		System.out.println("\n openBoRegistrationPage is called");
		modelAndView.setViewName("bo_user_registration");
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

			String newPassword = password + pwdSalt;
			
			System.out.println("Password: "+newPassword);
			String newPasswordHash = Password.generatePwdHash(newPassword);
			System.out.println("Password hash: "+newPasswordHash);

			if (newPasswordHash.equals(oldPwdHash)) {
				
				model.addAttribute("businessOwnerRegistration ", businessOwnerRegistration );
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


	
	
	@GetMapping("/openFlightPage")
	public String openFlightPage() {
		return "Flight";
	}
	@GetMapping("/openFM_loginPage")
	public String openFMLoginPage()
	{
		return "FM_login";
	}
	@GetMapping("/openFM_registrationPage")
	public String openFMRegistrationPage()
	{
		return "FM_registration";
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

}
