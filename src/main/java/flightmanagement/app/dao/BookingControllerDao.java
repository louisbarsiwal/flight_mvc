package flightmanagement.app.dao;

import flightmanagement.app.entities.BookingFlight;

public interface BookingControllerDao {
    void saveBooking(BookingFlight bookingFlight);
}
