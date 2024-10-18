package flightmanagement.app.dao;

import flightmanagement.app.dao.BookingControllerDao;

import flightmanagement.app.entities.BookingFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookingControllerDaoImpl implements BookingControllerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_BOOKING_SQL = 
        "INSERT INTO booking_flights (airline_name, flight_no, flight_model, from_location, to_location, " +
        "departure_datetime, arrival_datetime, economy_seats, economy_price, business_seats, business_price, total_price) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void saveBooking(BookingFlight bookingFlight) {
        jdbcTemplate.update(INSERT_BOOKING_SQL,
            bookingFlight.getAirlineName(),
            bookingFlight.getFlightNo(),
            bookingFlight.getFlightModel(),
            bookingFlight.getFromLocation(),
            bookingFlight.getToLocation(),
            bookingFlight.getDepartureDatetime(),
            bookingFlight.getArrivalDatetime(),
            bookingFlight.getEconomySeats(),
            bookingFlight.getEconomyPrice(),
            bookingFlight.getBusinessSeats(),
            bookingFlight.getBusinessPrice(),
            bookingFlight.getTotalPrice()
        );
    }
}
