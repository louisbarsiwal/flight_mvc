package flightmanagement.app.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import flightmanagement.app.dao.AddedFlightDaoImpl;
import flightmanagement.app.dao.BookingControllerDao;
import flightmanagement.app.entities.AddedFlight;
import flightmanagement.app.entities.BookingFlight;

@Controller
public class ViewBookedFlightsController {

	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	 
	 @Autowired
	    private BookingControllerDao bookingControllerDao;
	 
	    @Autowired
	    private AddedFlightDaoImpl addedFlightImpl; // Inject FlightDao to manage seat updates
	    
	    @GetMapping("/openBookingHistoryPage")
		public String openBookingHistoryPage(Model model) {
			String sql = "SELECT booking_id,airline_name,flight_no,flight_model, "
					+ "from_location,to_location,departure_datetime,arrival_datetime,economy_seats,"
					+ "economy_price,business_seats,business_price,total_price FROM booking_flights";
			 
	        List<Map<String, Object>> bookings = jdbcTemplate.queryForList(sql);
	 
	        model.addAttribute("bookings", bookings);
			return "booking_history";
		}
	    
	    @PostMapping("/booking")
	    public String bookFlight(
	            @RequestParam("flightId") int flightId,
	            @RequestParam("airlineName") String airlineName,
	            @RequestParam("flightNo") String flightNo,
	            @RequestParam("flightModel") String flightModel,
	            @RequestParam("fromLocation") String fromLocation,
	            @RequestParam("toLocation") String toLocation,
	            @RequestParam("departureDatetime") String departureDatetime,
	            @RequestParam("arrivalDatetime") String arrivalDatetime,
	            @RequestParam("economySeats") int economySeats,
	            @RequestParam("businessSeats") int businessSeats,
	            @RequestParam("totalBusinessPrice") double totalBusinessPrice,
	            @RequestParam("totalEconomyPrice") double totalEconomyPrice,
	            @RequestParam("totalFare") double totalFare,
	            Model model) {
	 
	        // Parse date-time strings to LocalDateTime
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        LocalDateTime departure = LocalDateTime.parse(departureDatetime, formatter);
	        LocalDateTime arrival = LocalDateTime.parse(arrivalDatetime, formatter);
	 
	       
	        // Save the booking information
	        BookingFlight booking = new BookingFlight();
	        booking.setAirlineName(airlineName);
	        booking.setFlightNo(flightNo);
	        booking.setFlightModel(flightModel);
	        booking.setFromLocation(fromLocation);
	        booking.setToLocation(toLocation);
	        booking.setDepartureDatetime(departure);
	        booking.setArrivalDatetime(arrival);
	        booking.setEconomySeats(economySeats);
	        booking.setBusinessSeats(businessSeats);
	        booking.setBusinessPrice(totalBusinessPrice);
	        booking.setEconomyPrice(totalEconomyPrice);
	        booking.setTotalPrice(totalFare);
	 
	        bookingControllerDao.saveBooking(booking); // Save the booking
	 
	        // **Reduce the available seats** in the flight
	        addedFlightImpl.updateSeatCounts(flightId, economySeats, businessSeats);
	 
	        // Fetch the updated flight details after seat reduction
	        AddedFlight updatedFlight = addedFlightImpl.getUserById(flightId); // Fetch updated flight details
	        // Pass the updated flight details to the JSP page
	        model.addAttribute("flight", updatedFlight);
	        return "payment"; // Show the payment page with updated seats
	    }
	  
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
     
     String insertCancelledBooking ="INSERT INTO cancelled_bookings(airline_name, flight_no, flight_model,"
     		+ "from_location, to_location, departure_datetime, arrival_datetime, economy_seats,economy_price,"
     		+ "business_seats, business_price,total_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
     return "redirect:/openBookingHistoryPage";
	  }
	  
	  @GetMapping("/openCancelledTickets")
		public String openCancelledTickets(Model model) {
		    String sql = "SELECT booking_id,airline_name,flight_no,flight_model,from_location"
		    		+ ",to_location,departure_datetime,arrival_datetime,economy_seats,"
		    		+ " economy_price,business_seats,business_price,total_price FROM cancelled_bookings ";
		    
		    List<Map<String, Object>> cancelledBookings = jdbcTemplate.queryForList(sql);
		    model.addAttribute("cancelledBookings", cancelledBookings);
		    return "cancelled_bookings"; // Ensure this matches the JSP file name
	  }  
	  
	  @PostMapping("/filterCancelledBookings")
	  public String filterCancelledBookings(@RequestParam("searchTerm") String searchTerm, Model model) {
	      String sql = "SELECT booking_id, airline_name, flight_no, flight_model, "
	              + "from_location, to_location, departure_datetime, arrival_datetime, "
	              + "economy_seats, economy_price, business_seats, business_price, total_price "
	              + "FROM cancelled_bookings WHERE airline_name LIKE ? OR flight_no LIKE ?";
	      
	      List<Map<String, Object>> cancelledBookings = jdbcTemplate.queryForList(sql, "%" + searchTerm + "%", "%" + searchTerm + "%");
	      
	      model.addAttribute("cancelledBookings", cancelledBookings);
	      return "cancelled_bookings"; // Ensure this matches the JSP file name
	  }


	  @PostMapping("/filterBookingHistory")
	  public String filterBookingHistory(@RequestParam("searchTerm") String searchTerm, Model model) {
	      String sql = "SELECT booking_id, airline_name, flight_no, flight_model, "
	              + "from_location, to_location, departure_datetime, arrival_datetime, "
	              + "economy_seats, economy_price, business_seats, business_price, total_price "
	              + "FROM booking_flights WHERE airline_name LIKE ? OR flight_no LIKE ?";
	      
	      List<Map<String, Object>> bookings = jdbcTemplate.queryForList(sql, "%" + searchTerm + "%", "%" + searchTerm + "%");
	      
	      model.addAttribute("bookings", bookings);
	      return "booking_history";
	  }

}

