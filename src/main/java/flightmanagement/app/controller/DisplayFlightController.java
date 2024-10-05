
package flightmanagement.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class DisplayFlightController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/openDisplayFlightPage")
	public String getAvailableFlights(Model model) {
		String sql = "SELECT airline_name, flight_no, flight_model, from_location, to_location, "
				+ "departure_datetime, arrival_datetime, total_seats, economy_seats, "
				+ "economy_price, business_seats, business_price FROM added_flights";

		List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);

		model.addAttribute("flights", flights);
		System.out.println(flights);

		return "display_flight";
	}
}
