package flightmanagement.app.entities;

import java.time.LocalDateTime;

public class BookingFlight {

    private int bookingId;
    private String airlineName;
    private String flightNo;
    private String flightModel;
    private String fromLocation;
    private String toLocation;
    private LocalDateTime departureDatetime;
    private LocalDateTime arrivalDatetime;
    private int economySeats;
    private double economyPrice;
    private int businessSeats;
    private double businessPrice;
    private double totalPrice;

    // Default Constructor
    public BookingFlight() {
        super();
    }

    // Parameterized Constructor
    public BookingFlight(int bookingId, String airlineName, String flightNo, String flightModel,
                         String fromLocation, String toLocation, LocalDateTime departureDatetime,
                         LocalDateTime arrivalDatetime, int economySeats, double economyPrice,
                         int businessSeats, double businessPrice, double totalPrice) {
        super();
        this.bookingId = bookingId;
        this.airlineName = airlineName;
        this.flightNo = flightNo;
        this.flightModel = flightModel;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.departureDatetime = departureDatetime;
        this.arrivalDatetime = arrivalDatetime;
        this.economySeats = economySeats;
        this.economyPrice = economyPrice;
        this.businessSeats = businessSeats;
        this.businessPrice = businessPrice;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getFlightModel() {
        return flightModel;
    }

    public void setFlightModel(String flightModel) {
        this.flightModel = flightModel;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public LocalDateTime getDepartureDatetime() {
        return departureDatetime;
    }

    public void setDepartureDatetime(LocalDateTime departureDatetime) {
        this.departureDatetime = departureDatetime;
    }

    public LocalDateTime getArrivalDatetime() {
        return arrivalDatetime;
    }

    public void setArrivalDatetime(LocalDateTime arrivalDatetime) {
        this.arrivalDatetime = arrivalDatetime;
    }

    public int getEconomySeats() {
        return economySeats;
    }

    public void setEconomySeats(int economySeats) {
        this.economySeats = economySeats;
    }

    public double getEconomyPrice() {
        return economyPrice;
    }

    public void setEconomyPrice(double economyPrice) {
        this.economyPrice = economyPrice;
    }

    public int getBusinessSeats() {
        return businessSeats;
    }

    public void setBusinessSeats(int businessSeats) {
        this.businessSeats = businessSeats;
    }

    public double getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(double businessPrice) {
        this.businessPrice = businessPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "BookingFlight [bookingId=" + bookingId + ", airlineName=" + airlineName + ", flightNo=" + flightNo +
               ", flightModel=" + flightModel + ", fromLocation=" + fromLocation + ", toLocation=" + toLocation +
               ", departureDatetime=" + departureDatetime + ", arrivalDatetime=" + arrivalDatetime +
               ", economySeats=" + economySeats + ", economyPrice=" + economyPrice +
               ", businessSeats=" + businessSeats + ", businessPrice=" + businessPrice +
               ", totalPrice=" + totalPrice + "]";
    }
}
