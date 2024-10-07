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

@Controller 
public class DisplayAirlineController {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/openDisplayAirlinePage")
    public String getAvailableAirline(Model model) {
        String sql = "SELECT id, airline_name, airline_number, model_number FROM added_airline";

        List<Map<String, Object>> airlines = jdbcTemplate.queryForList(sql);

        model.addAttribute("airlines", airlines);
        System.out.println(airlines);

        return "display_airline"; // Ensure the JSP file name matches
    }

    @PostMapping("/deleteAirline")
    public String deleteAirline(@RequestParam("airlineId") String airlineIdStr, Model model) {
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

        // Proceed with fetching and deleting the airline as before
        String selectSql = "SELECT * FROM added_airline WHERE id = ?";
        Map<String, Object> airline = jdbcTemplate.queryForMap(selectSql, airlineId);

        String insertSql = "INSERT INTO deleted_airlines (airline_name, airline_number, model_number) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertSql, airline.get("airline_name"), airline.get("airline_number"), airline.get("model_number"));

        String deleteSql = "DELETE FROM added_airline WHERE id = ?";
        jdbcTemplate.update(deleteSql, airlineId);

        return "redirect:/openDisplayAirlinePage";
    }

}
