package flightmanagement.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlightTicketController {
    
    @GetMapping("/ticket")
    public String showTicket() {
        return "flightTicket"; // This maps to flightTicket.jsp
    }
    
    
}
