package flightmanagement.app.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller; // Add this import
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import flightmanagement.app.dao.AddedAirlineDaoImpl;
import flightmanagement.app.entities.AddedAirline;


@Controller 
public class DisplayAirlineController {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
    private AddedAirline addedAirline;
    
    @Autowired
    AddedAirlineDaoImpl addedAirlineDaoImpl;

    @GetMapping("/openDisplayAirlinePage")
    public String getAvailableAirline(Model model) {
        String sql = "SELECT id, airline_name, airline_number, model_number FROM added_airline";

        List<Map<String, Object>> airlines = jdbcTemplate.queryForList(sql);

        model.addAttribute("airlines", airlines);
        System.out.println(airlines);

        return "display_airline"; // Ensure the JSP file name matches
    }

    @PostMapping("/deleteAirline")
    public String deleteAirline(@RequestParam("airlineId") String airlineIdStr, 
    		@RequestParam("airlineName") String airlineName,
    		Model model) {
        if (airlineIdStr == null || airlineIdStr.equals("null") || airlineIdStr.isEmpty()) {
            System.err.println("Received airlineId is null or invalid: " + airlineIdStr);
            return "redirect:/openDisplayAirlinePage"; // Optionally add an error message
        }

        int airlineId;
        try {
            airlineId = Integer.parseInt(airlineIdStr);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing airlineId: " + airlineIdStr);
            return "redirect:/openDisplayAirlinePage"; // Optionally add an error message
        }

      
        String selectSql = "SELECT * FROM added_airline WHERE id = ?";
        Map<String, Object> airline = jdbcTemplate.queryForMap(selectSql, airlineId);
        
       

        String insertSql = "INSERT INTO deleted_airlines (airline_name, airline_number, model_number) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertSql, airline.get("airline_name"), airline.get("airline_number"), airline.get("model_number"));
        
        String selectSql1 = "SELECT * FROM added_flights WHERE airline_name = ?";
        Map<String, Object> flight = jdbcTemplate.queryForMap(selectSql1, airlineName);

        String insertSql1 ="INSERT INTO deleted_flights (airline_name, flight_no, flight_model, from_location, to_location, "
                + "departure_datetime, arrival_datetime, total_seats, economy_seats, economy_price, business_seats, business_price) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertSql1, flight.get("airline_name"), flight.get("flight_no"), flight.get("flight_model"),
                flight.get("from_location"), flight.get("to_location"), flight.get("departure_datetime"),
                flight.get("arrival_datetime"), flight.get("total_seats"), flight.get("economy_seats"),
                flight.get("economy_price"), flight.get("business_seats"), flight.get("business_price"));

        
        

        String deleteSql = "DELETE FROM added_airline WHERE id = ?";
        jdbcTemplate.update(deleteSql, airlineId);

        return "redirect:/openDisplayAirlinePage";
    }
    
    
    @GetMapping("/openEditAirlinePage")
    public ModelAndView openEditAirlinePage(@RequestParam int airLineId) {
        ModelAndView modelAndView = new ModelAndView("update_airline");
        AddedAirline addedAirline = addedAirlineDaoImpl.getUserById(airLineId); 
        modelAndView.addObject("addedAirline", addedAirline);
        return modelAndView;
    }




    @PostMapping("/editAirline")
    public String editAirline(@ModelAttribute AddedAirline updatedAirline,
                               RedirectAttributes attributes) {
        try {
            addedAirlineDaoImpl.updateAirline(updatedAirline);
            attributes.addFlashAttribute("message", "Airline updated successfully");
        } catch (EmptyResultDataAccessException e) {
            attributes.addFlashAttribute("message", "No airline found with the provided ID");
        } catch (SQLException e) {
            e.printStackTrace(); // Log the SQLException for debugging
            attributes.addFlashAttribute("message", "Updation failed: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Log any other exceptions
            attributes.addFlashAttribute("message", "An unexpected error occurred.");
        }
        
        // Redirect back to openEditAirlinePage with the airLineId
        return "redirect:/openEditAirlinePage?airLineId=" + updatedAirline.getAirLineId();
    }
}
