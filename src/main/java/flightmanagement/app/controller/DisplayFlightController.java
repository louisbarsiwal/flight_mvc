
package flightmanagement.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
//    @PostMapping("/deleteFlight")
//    public String deleteAirline(@RequestParam("airlineId") String airlineIdStr, Model model) {
//        if (airlineIdStr == null || airlineIdStr.equals("null") || airlineIdStr.isEmpty()) {
//            System.err.println("Received airlineId is null or invalid: " + airlineIdStr);
//            return "redirect:/openDisplayAirlinePage"; // Optionally add an error message
//        }
//
//        int airlineId;
//        try {
//            airlineId = Integer.parseInt(airlineIdStr);
//        } catch (NumberFormatException e) {
//            System.err.println("Error parsing airlineId: " + airlineIdStr);
//            return "redirect:/openDisplayAirlinePage"; // Optionally add an error message
//        }
//
//        // Proceed with fetching and deleting the airline as before
//        String selectSql = "SELECT * FROM added_flights WHERE id = ?";
//        Map<String, Object> airline = jdbcTemplate.queryForMap(selectSql, airlineId);
//
//        String insertSql = "INSERT INTO deleted_flights (airline_name, airline_number, model_number) VALUES (?, ?, ?)";
//        jdbcTemplate.update(insertSql, airline.get("airline_name"), airline.get("airline_number"), airline.get("model_number"));
//
//        String deleteSql = "DELETE FROM added_flights WHERE id = ?";
//        jdbcTemplate.update(deleteSql, airlineId);
//
//        return "redirect:/openDisplayFlightPage";
//    }
}

