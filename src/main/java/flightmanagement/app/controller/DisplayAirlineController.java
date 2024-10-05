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

@Controller // Ensure your class is annotated with @Controller
public class DisplayAirlineController {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/openDisplayAirlinePage")
    public String getAvailableAirline(Model model) {
        String sql = "SELECT airline_name, airline_number, model_number FROM added_airline";

        List<Map<String, Object>> airlines = jdbcTemplate.queryForList(sql);

        model.addAttribute("airlines", airlines);
        System.out.println(airlines);

        return "display_airline"; // Ensure the JSP file name matches
    }

//    @PostMapping("/deleteAirline")
//    public String deleteAirline(@RequestParam("airlineId") int airlineId, Model model) {
//        // Fetch the airline data to store in the deleted_airlines table
//        String selectSql = "SELECT * FROM added_airline WHERE id = ?";
//        Map<String, Object> airline = jdbcTemplate.queryForMap(selectSql, airlineId);
//
//        // Insert into deleted_airlines table
//        String insertSql = "INSERT INTO deleted_airlines (airline_name, airline_number, model_number) VALUES (?, ?, ?)";
//        jdbcTemplate.update(insertSql, airline.get("airline_name"), airline.get("airline_number"), airline.get("model_number"));
//
//        // Now delete from added_airline table
//        String deleteSql = "DELETE FROM added_airline WHERE id = ?";
//        jdbcTemplate.update(deleteSql, airlineId);
//
//        // Redirect to display page
//        return "redirect:/openDisplayAirlinePage"; // Ensure the redirect is to the correct mapping
//    }
}
