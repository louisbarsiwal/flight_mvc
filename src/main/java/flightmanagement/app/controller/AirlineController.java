package flightmanagement.app.controller;

import flightmanagement.app.dao.AddedAirlineDaoImpl;
import flightmanagement.app.entities.AddedAirline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/airline")
public class AirlineController {

    @Autowired
    private AddedAirlineDaoImpl addedairlinedaoimpl;

    @GetMapping("/openAddAirlinePage")
    public String openAddAirlinePage() {
        return "add_airline"; 
    }

    @PostMapping("/addAirline") 
    public String addAirline(AddedAirline addedAirline, Model model) {
        try {
            addedairlinedaoimpl.insertAirline(addedAirline); 
            model.addAttribute("message", "Airline added successfully");
        } catch (Exception e) {
            model.addAttribute("message", "Error adding airline: Can't add same airline twice ");
        }
        return "add_airline"; 
    }

    
}
