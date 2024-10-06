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

@Controller // Add this annotation
public class DeletedAirlineController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/openDeletedAirlinePage")
	public String getDeletedAirlines(Model model) {
	    String sql = "SELECT id, airline_name, airline_number, model_number FROM deleted_airlines";
	    List<Map<String, Object>> deletedAirlines = jdbcTemplate.queryForList(sql);
	    model.addAttribute("deletedAirlines", deletedAirlines);
	    return "deleted_airline"; // Ensure this matches the JSP file name
	}
	
	@PostMapping("/restoreAirline")
	public String restoreAirline(@RequestParam("airlineId") int airlineId) {
	    // Fetch the airline data to restore
	    String selectSql = "SELECT * FROM deleted_airlines WHERE id = ?";
	    Map<String, Object> airline = jdbcTemplate.queryForMap(selectSql, airlineId);
	
	    // Insert back into the added_airline table
	    String insertSql = "INSERT INTO added_airline (airline_name, airline_number, model_number) VALUES (?, ?, ?)";
	    jdbcTemplate.update(insertSql, airline.get("airline_name"), airline.get("airline_number"), airline.get("model_number"));
	
	    // Now delete from the deleted_airlines table
	    String deleteSql = "DELETE FROM deleted_airlines WHERE id = ?";
	    jdbcTemplate.update(deleteSql, airlineId);
	
	    return "redirect:/openDeletedAirlinePage"; // Redirect back to the deleted airlines list
	}
}
