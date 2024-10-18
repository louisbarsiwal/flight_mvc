package flightmanagement.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller; // Add this import
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Add this annotation
public class DeletedAirlineController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/openDeletedAirlinePage")
	public String getDeletedAirlines(Model model) {
	    String sql = "SELECT id, airline_name, airline_number, model_number FROM deleted_airlines";
	    List<Map<String, Object>> deletedAirlines = jdbcTemplate.queryForList(sql);
	    model.addAttribute("deletedAirlines", deletedAirlines);
	    return "deleted_airline"; // Ensure this matches the JSP file name
	}
	
	
	@PostMapping("/restoreAirline") 
	public String restoreAirline(@RequestParam("airlineId") int airlineId, @RequestParam("airlineName") String airlineName) {
	    // Fetch the airline data to restore
	    String selectSql = "SELECT * FROM deleted_airlines WHERE id = ?";
	    List<Map<String, Object>> airlines = jdbcTemplate.queryForList(selectSql, airlineId);
	    if (airlines.isEmpty()) {
	        return "redirect:/openDeletedAirlinePage"; // No airline found
	    }
	    Map<String, Object> airline = airlines.get(0);
	    
	    // Insert back into the added_airline table
	    String insertSql = "INSERT INTO added_airline (airline_name, airline_number, model_number) VALUES (?, ?, ?)";
	    jdbcTemplate.update(insertSql, airline.get("airline_name"), airline.get("airline_number"), airline.get("model_number"));

	    // Restore associated flights
	    String selectSql1 = "SELECT * FROM deleted_flights WHERE airline_name = ?";
	    List<Map<String, Object>> flights = jdbcTemplate.queryForList(selectSql1, airlineName);
	    String insertSql1 = "INSERT INTO added_flights (airline_name, flight_no, flight_model, from_location, to_location, "
	        + "departure_datetime, arrival_datetime, total_seats, economy_seats, economy_price, business_seats, business_price) "
	        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    for (Map<String, Object> flight : flights) {
	        jdbcTemplate.update(insertSql1, flight.get("airline_name"), flight.get("flight_no"), flight.get("flight_model"),
	            flight.get("from_location"), flight.get("to_location"), flight.get("departure_datetime"),
	            flight.get("arrival_datetime"), flight.get("total_seats"), flight.get("economy_seats"),
	            flight.get("economy_price"), flight.get("business_seats"), flight.get("business_price"));
	    }

	    // Now delete from the deleted_airlines table
	    String deleteSql = "DELETE FROM deleted_airlines WHERE id = ?";
	    jdbcTemplate.update(deleteSql, airlineId);

	    return "redirect:/openDeletedAirlinePage"; // Redirect back to the deleted airlines list
	}

}
