
package flightmanagement.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.transaction.annotation.Transactional;

import flightmanagement.app.dao.AddedFlightDaoImpl;
import flightmanagement.app.entities.AddedAirline;
import flightmanagement.app.entities.AddedFlight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class DisplayFlightController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private AddedFlight addedFlight;
    
    @Autowired
    AddedFlightDaoImpl addedFlightDaoImpl;

    @GetMapping("/openDisplayFlightPage")
    public String getAvailableFlights(Model model) {
        String sql = "SELECT flight_id, airline_name, flight_no, flight_model, from_location, to_location, "
                + "departure_datetime, arrival_datetime, total_seats, economy_seats, "
                + "economy_price, business_seats, business_price FROM added_flights";

        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);

        model.addAttribute("flights", flights);
        System.out.println(flights); 

        return "display_flight";
    }
    
    @PostMapping("/deleteFlight")
    public String deleteFlight(@RequestParam("flightId") String flightIdStr, Model model) {
        System.out.println("Attempting to delete flight with ID: " + flightIdStr);

        if (flightIdStr == null || flightIdStr.equals("null") || flightIdStr.isEmpty()) {
            System.err.println("Received flightId is null or invalid: " + flightIdStr);
            return "redirect:/openDisplayFlightPage";
        }

        int flightId;
        try {
            flightId = Integer.parseInt(flightIdStr);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing flightId: " + flightIdStr);
            return "redirect:/openDisplayFlightPage";
        }

        // Check if the flight exists
        String selectSql = "SELECT * FROM added_flights WHERE flight_id = ?";
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(selectSql, flightId);
        if (flights.isEmpty()) {
            System.err.println("No flight found with ID: " + flightId);
            return "redirect:/openDisplayFlightPage";
        }

        // Proceed with fetching and deleting the flight
        Map<String, Object> flight = flights.get(0); // Get the first (and should be only) flight

        // Insert into deleted_flights
        String insertSql = "INSERT INTO deleted_flights (airline_name, flight_no, flight_model, from_location, to_location, "
                + "departure_datetime, arrival_datetime, total_seats, economy_seats, economy_price, business_seats, business_price) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rowsInserted = jdbcTemplate.update(insertSql, flight.get("airline_name"), flight.get("flight_no"), flight.get("flight_model"),
                flight.get("from_location"), flight.get("to_location"), flight.get("departure_datetime"),
                flight.get("arrival_datetime"), flight.get("total_seats"), flight.get("economy_seats"),
                flight.get("economy_price"), flight.get("business_seats"), flight.get("business_price"));

        if (rowsInserted > 0) {
            System.out.println("Successfully inserted flight into deleted_flights.");
        } else {
            System.err.println("Failed to insert flight into deleted_flights.");
        }
        
        
        // Delete the flight
        String deleteSql = "DELETE FROM added_flights WHERE flight_id = ?";
        int rowsDeleted = jdbcTemplate.update(deleteSql, flightId);

        if (rowsDeleted > 0) {
            System.out.println("Successfully deleted flight with ID: " + flightId);
        } else {
            System.err.println("Failed to delete flight with ID: " + flightId);
        }

        return "redirect:/openDisplayFlightPage";
    }    
    @GetMapping("/openEditFlightPage")
    public ModelAndView openEditFlightPage(@RequestParam int flightId,Model model) {
    	
    	String sql = "SELECT airline_name, flight_no, flight_model FROM added_flights";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);

        List<String> airlineNames = new ArrayList<>();
        List<String> flightNos = new ArrayList<>();
        List<String> flightModels = new ArrayList<>();

        for (Map<String, Object> row : results) {
            airlineNames.add((String) row.get("airline_name"));
            flightNos.add((String) row.get("flight_no"));
            flightModels.add((String) row.get("flight_model"));
        }

        model.addAttribute("airlineNames", airlineNames);
        model.addAttribute("airlineNumbers",flightNos );
        model.addAttribute("modelNumbers",flightModels );
        
        ModelAndView modelAndView = new ModelAndView("update_flight");
        AddedFlight addedFlight = addedFlightDaoImpl.getUserById(flightId); 
        modelAndView.addObject("addedFlight", addedFlight);
        return modelAndView;
    }
    
    @PostMapping("/editFlight")
    public String editFlight(@ModelAttribute AddedFlight updatedFlight,
                               RedirectAttributes attributes) {
        try {
        	addedFlightDaoImpl.updateFlight(updatedFlight);
            attributes.addFlashAttribute("message", "Flight updated successfully");
        } catch (EmptyResultDataAccessException e) {
            attributes.addFlashAttribute("message", "No Flight found with the provided ID");
        } catch (SQLException e) {
            e.printStackTrace(); // Log the SQLException for debugging
            attributes.addFlashAttribute("message", "Updation failed: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Log any other exceptions
            attributes.addFlashAttribute("message", "An unexpected error occurred.");
        }
        
        // Redirect back to openEditAirlinePage with the airLineId
        return "redirect:/openEditFlightPage?flightId=" + updatedFlight.getFlightId();
    }
}
    

