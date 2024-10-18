package flightmanagement.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class BookingController {
	
	  @Autowired
	    private JdbcTemplate jdbcTemplate;
	  
	  @PostMapping("/cancelBooking")
	  public String cancelBooking(@RequestParam("bookingId") String bookingIdStr,Model model) 
	  {
		  if (bookingIdStr == null || bookingIdStr.equals("null") || bookingIdStr.isEmpty()) {
            System.err.println("Received bookingId is null or invalid: " + bookingIdStr);
            return "redirect:/openViewBooking"; // Optionally add an error message
        }
        int bookingId;
        try {
        	bookingId = Integer.parseInt(bookingIdStr);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing airlineId: " + bookingIdStr);
            return "redirect:/openViewBooking"; // Optionally add an error message
        }
		
        String selectBooking = "SELECT * FROM booking_flights WHERE booking_id = ?";
        Map<String, Object> booking = jdbcTemplate.queryForMap(selectBooking,bookingId);
        
        String insertCancelledBooking ="INSERT INTO cancelled_flights(airline_name,flight_no,flight_model,"
        		+ "from_location, to_location, departure_datetime, arrival_datetime, economy_seats,economy_price,"
        		+ "business_seats, business_price,total_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertCancelledBooking,
        		booking.get("airline_name"), 
        		booking.get("flight_no"), 
        		booking.get("flight_model"),
        		booking.get("from_location"),
        		booking.get("to_location"),
        		booking.get("departure_datetime"),
        		booking.get("arrival_datetime"),
        		booking.get("economy_seats"),
        		booking.get("economy_price"),
        		booking.get("business_seats"),
        		booking.get("business_price"),
        		booking.get("total_price"));
        
        String cancelBooking = "DELETE FROM booking_flights WHERE booking_id = ?";
        jdbcTemplate.update(cancelBooking, bookingId);
        return "redirect:/openViewBooking";
	  }
	  
	  @GetMapping("/openCancelledTickets")
		public String openCancelledTickets(Model model) {
		    String sql = "SELECT booking_id,airline_name,flight_no,flight_model,from_location"
		    		+ ",to_location,departure_datetime,arrival_datetime,economy_seats,"
		    		+ " economy_price,business_seats,business_price,total_price FROM cancelled_flights ";
		    
		    List<Map<String, Object>> cancelledBookings = jdbcTemplate.queryForList(sql);
		    model.addAttribute("cancelledBookings", cancelledBookings);
		    return "cancelled_tickets"; // Ensure this matches the JSP file name
	  }  

}
