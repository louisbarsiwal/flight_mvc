package flightmanagement.app.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import flightmanagement.app.dao.FlightRowMapper;
import flightmanagement.app.entities.AddedFlight;

@Controller
public class DeletedFlightController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/openDeletedFlightPage")
	public String getDeletedFlights(Model model) {
	    String sql = "SELECT flight_id, airline_name, flight_no, flight_model, from_location, to_location, "
	            + "departure_datetime, arrival_datetime, total_seats, economy_seats, economy_price, "
	            + "business_seats, business_price FROM deleted_flights";

	    List<Map<String, Object>> deletedFlights = jdbcTemplate.queryForList(sql);
	    model.addAttribute("deletedFlights", deletedFlights);
	    return "deleted_flight"; 
	}
	
	@PostMapping("/restoreFlight")
	public String addBackFlight(@RequestParam("flightId") String flightIdStr,
			@RequestParam("airlineName") String airlineName ) {
	    if (flightIdStr == null || flightIdStr.equals("null") || flightIdStr.isEmpty()) {
	        System.err.println("Received flightId is null or invalid: " + flightIdStr);
	        return "redirect:/openDeletedFlightPage"; 
	    }

	    int flightId;
	    try {
	        flightId = Integer.parseInt(flightIdStr);
	    } catch (NumberFormatException e) {
	        System.err.println("Error parsing flightId: " + flightIdStr);
	        return "redirect:/openDeletedFlightPage"; 
	    }

	    // Fetch the deleted flight data
	    String selectSql = "SELECT * FROM deleted_flights WHERE flight_id = ?";
	    List<Map<String, Object>> flights = jdbcTemplate.queryForList(selectSql, flightId);
	    
	    
        
	    
	    if (!flights.isEmpty()) {
	        Map<String, Object> flight = flights.get(0);
	        String insertSql = "INSERT INTO added_flights (airline_name, flight_no, flight_model, from_location, to_location, "
	                + "departure_datetime, arrival_datetime, total_seats, economy_seats, economy_price, "
	                + "business_seats, business_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        jdbcTemplate.update(insertSql,flight.get("airline_name"),flight.get("flight_no"), flight.get("flight_model"),
	                flight.get("from_location"), flight.get("to_location"), flight.get("departure_datetime"),
	                flight.get("arrival_datetime"), flight.get("total_seats"), flight.get("economy_seats"),
	                flight.get("economy_price"), flight.get("business_seats"), flight.get("business_price"));

	        // Optionally, delete the flight from deleted_flights
	        String deleteSql = "DELETE FROM deleted_flights WHERE flight_id = ?";
	        jdbcTemplate.update(deleteSql, flightId);  
	        
	        String deleteSql1 = "DELETE FROM deleted_airlines WHERE airline_name = ?";
	        jdbcTemplate.update(deleteSql1,airlineName);
	        
	    }
	    
	   
	    
	   

	    return "redirect:/openDisplayFlightPage";
     }
	


}
