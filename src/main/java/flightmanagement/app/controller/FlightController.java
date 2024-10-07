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

@Controller
@RequestMapping("/flight")
public class FlightController {

	@Autowired
    private AddedFlightDaoImpl addedflightdaoimpl; 
	
	private AddedFlight addedFlight;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
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
    public String addFlight(@ModelAttribute AddedFlight addedFlight, Model model) {
        System.out.println(addedFlight);
    	try {
        	addedflightdaoimpl.insertFlight(addedFlight);
            model.addAttribute("message", "Flight added successfully");
            System.out.println("successfully added");
        } catch (Exception e) {
        	e.printStackTrace();
            model.addAttribute("error", "Error adding flight: " + e.getMessage());
        }
        return "redirect:/flight/openAddFlightPage"; 
    }
}
    
    
//    @PostMapping("/addFlight")
//    public String addFlight(@ModelAttribute AddedFlight addedFlight, Model model) {
//        try {
//            if (addedFlight.getFlightId() != null) {
//                // Logic for updating an existing flight
//                addedflightdaoimpl.updateFlight(addedFlight);
//                model.addAttribute("message", "Flight updated successfully");
//            } else {
//                // Logic for adding a new flight
//                addedflightdaoimpl.insertFlight(addedFlight);
//                model.addAttribute("message", "Flight added successfully");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("error", "Error adding flight: " + e.getMessage());
//        }
//        return "redirect:/openAddFlightPage"; 
//    }
//
//  }

