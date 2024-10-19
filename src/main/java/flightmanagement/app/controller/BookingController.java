package flightmanagement.app.controller;

import flightmanagement.app.dao.BookingControllerDao;

import flightmanagement.app.dao.AddedFlightDaoImpl;
import flightmanagement.app.entities.AddedFlight;
import flightmanagement.app.entities.BookingFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class BookingController {

    @Autowired
    private BookingControllerDao bookingControllerDao;

    @Autowired
    private AddedFlightDaoImpl addedFlightImpl; // Inject FlightDao to manage seat updates

    @PostMapping("/booking")
    public String bookFlight(
            @RequestParam("flightId") int flightId,
            @RequestParam("airlineName") String airlineName,
            @RequestParam("flightNo") String flightNo,
            @RequestParam("flightModel") String flightModel,
            @RequestParam("fromLocation") String fromLocation,
            @RequestParam("toLocation") String toLocation,
            @RequestParam("departureDatetime") String departureDatetime,
            @RequestParam("arrivalDatetime") String arrivalDatetime,
            @RequestParam("economySeats") int economySeats,
            @RequestParam("businessSeats") int businessSeats,
            @RequestParam("totalBusinessPrice") double totalBusinessPrice,
            @RequestParam("totalEconomyPrice") double totalEconomyPrice,
            @RequestParam("totalFare") double totalFare,
            Model model) {

        // Parse date-time strings to LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime departure = LocalDateTime.parse(departureDatetime, formatter);
        LocalDateTime arrival = LocalDateTime.parse(arrivalDatetime, formatter);

       


        // Save the booking information
        BookingFlight booking = new BookingFlight();
        booking.setAirlineName(airlineName);
        booking.setFlightNo(flightNo);
        booking.setFlightModel(flightModel);
        booking.setFromLocation(fromLocation);
        booking.setToLocation(toLocation);
        booking.setDepartureDatetime(departure);
        booking.setArrivalDatetime(arrival);
        booking.setEconomySeats(economySeats);
        booking.setBusinessSeats(businessSeats);
        booking.setBusinessPrice(totalBusinessPrice);
        booking.setEconomyPrice(totalEconomyPrice);
        booking.setTotalPrice(totalFare);

        bookingControllerDao.saveBooking(booking); // Save the booking

        // **Reduce the available seats** in the flight
        addedFlightImpl.updateSeatCounts(flightId, economySeats, businessSeats);

        // Fetch the updated flight details after seat reduction
        AddedFlight updatedFlight = addedFlightImpl.getUserById(flightId); // Fetch updated flight details
        
        // Pass the updated flight details to the JSP page
        model.addAttribute("flight", updatedFlight);
        
        return "payment"; // Show the payment page with updated seats
    }
}
