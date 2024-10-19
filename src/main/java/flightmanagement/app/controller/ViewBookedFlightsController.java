package flightmanagement.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewBookedFlightsController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	 @GetMapping("/cancelFlight")
	 public String cancelFlight(@RequestParam("bookingId") String bookingIdStr,
	    		@RequestParam("airlineName") String airlineName, Model model) {
		 
		 
		 System.out.println("Attempting to delete flight with ID: " + bookingIdStr);

	        if (bookingIdStr == null || bookingIdStr.equals("null") || bookingIdStr.isEmpty()) {
	            System.err.println("Received bookingId is null or invalid: " + bookingIdStr);
	            return "redirect:/openViewBookingsPage";
	        }

	        int bookingId;
	        try {
	        	bookingId = Integer.parseInt(bookingIdStr);
	        } catch (NumberFormatException e) {
	            System.err.println("Error parsing flightId: " + bookingIdStr);
	            return "redirect:/openViewBookingsPage";
	        }

	        String selectSql = "SELECT * FROM booking_flights WHERE booking_id = ?";
	        Map<String, Object> bookings = jdbcTemplate.queryForMap(selectSql,bookingId);
	        
	       
	        String insertSql = "INSERT INTO cancelled_flights (airline_name, flight_no, flight_model, from_location, to_location, "
	        		+ "departure_datetime, arrival_datetime, economy_seats, economy_price,business_seats, business_price, total_price"
	        		+ ",airline_pnr_no) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        jdbcTemplate.update(insertSql, bookings.get("airline_name"),bookings.get("flight_no"),bookings.get("flight_model"),bookings.get("from_location"),
	        		bookings.get("to_location"),bookings.get("departure_datetime"),bookings.get("economy_seats"),bookings.get("economy_price"),
	        		bookings.get("business_seats"),bookings.get("business_price"),bookings.get("total_price"),bookings.get("airline_pnr_no"));
	        
	        String deleteSql = "DELETE FROM booking_flights WHERE booking_id = ?";
	        jdbcTemplate.update(deleteSql,bookingId );
	        		
	        
	        return "redirect:/openViewBookingsPage";
	 }
	
}
