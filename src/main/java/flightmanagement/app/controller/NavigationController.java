package flightmanagement.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NavigationController {

	@GetMapping("/")
	public String openHomePage() {
		return "index";
	}
	
	
}
