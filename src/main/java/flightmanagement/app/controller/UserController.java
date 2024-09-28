package flightmanagement.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController 
{
	
	
	@GetMapping("/openLoginPage")
	public String openLoginPage() {
		return "user_login";
	}
	
	@GetMapping("/openRegistrationPage")
	public String openRegistrationPage() {
		return "user_registration";
	}
	@GetMapping("/openFlightPage")
	public String openFlightPage()
	{
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
