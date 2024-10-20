package flightmanagement.app.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import flightmanagement.app.entities.BookingFlight;

@Controller
public class FlightTicketController {

    @GetMapping("/ticket")
    public String showTicket() {
        return "flightTicket"; // This maps to flightTicket.jsp
    }
    
    @PostMapping("/bookFlight")
    public String bookFlight(Model model) {
        // Create and set properties for BookingFlight
        BookingFlight bookingFlight = new BookingFlight();
        bookingFlight.setBookingId(1); // Example values
        bookingFlight.setAirlineName("Airline XYZ");
        bookingFlight.setFlightNo("XYZ123");
        bookingFlight.setFlightModel("Boeing 737");
        bookingFlight.setFromLocation("New York");
        bookingFlight.setToLocation("Los Angeles");
        bookingFlight.setDepartureDatetime(LocalDateTime.now().plusDays(1));
        bookingFlight.setArrivalDatetime(LocalDateTime.now().plusDays(1).plusHours(6));
        bookingFlight.setEconomySeats(50);
        bookingFlight.setEconomyPrice(150.0);
        bookingFlight.setBusinessSeats(20);
        bookingFlight.setBusinessPrice(300.0);
        bookingFlight.setTotalPrice(3000.0); // Example total price

        // Add the BookingFlight object to the model
        model.addAttribute("bookingFlight", bookingFlight);

        return "flightTicket"; // Forward to flightTicket.jsp
    }

}
