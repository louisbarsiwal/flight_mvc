package flightmanagement.app.controller;
 
import flightmanagement.app.dao.AddedFlightDaoImpl;
import flightmanagement.app.entities.AddedFlight;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
@Controller
@RequestMapping("/flight")
public class FlightController {
 
	@Autowired
    private AddedFlightDaoImpl addedflightdaoimpl;
 
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	 @GetMapping("/searchFlights")
	 
		public String searchFlights(@RequestParam(required = false) String source,
				@RequestParam(required = false) String destination, @RequestParam(required = false) String departureDate,
				@RequestParam(required = false) String tripType, Model model) {
		 
		 
			System.out.println("Searching flights from " + source + " to " + destination + " on " + departureDate
					+ " with trip type " + tripType);
			if (source == null)
				return "passenger_dashboard";
	 
			List<AddedFlight> flights = addedflightdaoimpl.searchFlights(source, destination, departureDate);
			model.addAttribute("flights", flights);
			System.out.println("Flights found: " + flights.size()); // Debugging line
			return "passenger_dashboard";
	 
		}
	 
	 @PostMapping("/openBookNowPage")
	 public String showBookNowPage(@RequestParam("flightId") int flightId, Model model) {
		    AddedFlight flight = addedflightdaoimpl.getUserById(flightId);  // Fetch flight by ID
		    model.addAttribute("flight", flight);
		    return "book_now";
		}

    @GetMapping("/openAddFlightPage")
    public String openAddFlightPage(Model model) {
    	String sql = "SELECT airline_name, airline_number, model_number FROM added_airline";
 
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
 
        List<String> airlineNames = new ArrayList<>();
        List<String> airlineNumbers = new ArrayList<>();
        List<String> modelNumbers = new ArrayList<>();
 
        for (Map<String, Object> row : results) {
            airlineNames.add((String) row.get("airline_name"));
            airlineNumbers.add((String) row.get("airline_number"));
            modelNumbers.add((String) row.get("model_number"));
        }
 
        
        model.addAttribute("airlineNames", airlineNames);
        model.addAttribute("airlineNumbers", airlineNumbers);
        model.addAttribute("modelNumbers", modelNumbers);
 
        return "add_flight"; 
    }
 
    @PostMapping("/addFlight")
    public String addFlight(@ModelAttribute AddedFlight addedFlight, RedirectAttributes redirectAttributes) {
        System.out.println(addedFlight);
    	try {
        	addedflightdaoimpl.insertFlight(addedFlight);
        	 redirectAttributes.addFlashAttribute("message", "Flight added successfully");
            System.out.println("successfully added");
        } catch (Exception e) {
        	e.printStackTrace();
        	redirectAttributes.addFlashAttribute("error", "Error adding flight: " + e.getMessage());
        }
        return "redirect:/flight/openAddFlightPage"; 
    }
   
}
