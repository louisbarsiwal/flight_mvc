package flightmanagement.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import flightmanagement.app.dao.AddedFlightDaoImpl;
import flightmanagement.app.entities.AddedFlight;

@Controller
public class NavigationController {

	@Autowired
	private AddedFlightDaoImpl addedflightdaoimpl;

	@GetMapping("/")

	public String searchFlights(@RequestParam(required = false) String source,
			@RequestParam(required = false) String destination, @RequestParam(required = false) String departureDate,
			@RequestParam(required = false) String tripType, Model model) {
		System.out.println("Searching flights from " + source + " to " + destination + " on " + departureDate
				+ " with trip type " + tripType);
		if (source == null)
			return "index";

		List<AddedFlight> flights = addedflightdaoimpl.searchFlights(source, destination, departureDate);
		model.addAttribute("flights", flights);
		System.out.println("Flights found: " + flights.size()); // Debugging line
		return "index";

	}
}




