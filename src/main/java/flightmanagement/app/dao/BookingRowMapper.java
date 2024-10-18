package flightmanagement.app.dao;

import flightmanagement.app.entities.BookingFlight;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingRowMapper implements RowMapper<BookingFlight> {

    @Override
    public BookingFlight mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookingFlight booking = new BookingFlight();
        booking.setBookingId(rs.getInt("booking_id"));
        booking.setAirlineName(rs.getString("airline_name"));
        booking.setFlightNo(rs.getString("flight_no"));
        booking.setFlightModel(rs.getString("flight_model"));
        booking.setFromLocation(rs.getString("from_location"));
        booking.setToLocation(rs.getString("to_location"));
        booking.setDepartureDatetime(rs.getTimestamp("departure_datetime").toLocalDateTime());
        booking.setArrivalDatetime(rs.getTimestamp("arrival_datetime").toLocalDateTime());
        booking.setEconomySeats(rs.getInt("economy_seats"));
        booking.setEconomyPrice(rs.getDouble("economy_price"));
        booking.setBusinessSeats(rs.getInt("business_seats"));
        booking.setBusinessPrice(rs.getDouble("business_price"));
        booking.setTotalPrice(rs.getDouble("total_price"));
        return booking;
    }
}
