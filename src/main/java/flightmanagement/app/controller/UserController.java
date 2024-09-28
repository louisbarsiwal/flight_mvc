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

}
