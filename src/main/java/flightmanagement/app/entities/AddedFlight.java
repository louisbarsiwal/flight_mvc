package flightmanagement.app.entities;



public class AddedFlight {

	private Long flightId;

	private String airlineName;
	private String flightNo;
	private String flightModel;
	private String fromLocation;
	private String toLocation;
	private String departureDateTime;
	private String arrivalDateTime;
	private int totalSeats;
	private int economySeats;
	private double economyPrice;
	private int businessSeats;
	private double businessPrice;

	public AddedFlight() {
		super();
	}

	public AddedFlight(Long flightId, String airlineName, String flightNo, String flightModel, String fromLocation,
			String toLocation, String departureDateTime, String arrivalDateTime, int totalSeats,
			int economySeats, double economyPrice, int businessSeats, double businessPrice) {
		super();
		this.flightId = flightId;
		this.airlineName = airlineName;
		this.flightNo = flightNo;
		this.flightModel = flightModel;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.departureDateTime = departureDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.totalSeats = totalSeats;
		this.economySeats = economySeats;
		this.economyPrice = economyPrice;
		this.businessSeats = businessSeats;
		this.businessPrice = businessPrice;
	}

	public AddedFlight(String airlineName, String flightNo, String flightModel, String fromLocation, String toLocation,
			String departureDateTime, String arrivalDateTime, int totalSeats, int economySeats,
			double economyPrice, int businessSeats, double businessPrice) {
		super();
		this.airlineName = airlineName;
		this.flightNo = flightNo;
		this.flightModel = flightModel;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.departureDateTime = departureDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.totalSeats = totalSeats;
		this.economySeats = economySeats;
		this.economyPrice = economyPrice;
		this.businessSeats = businessSeats;
		this.businessPrice = businessPrice;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
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

	public String getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(String departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public String getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(String arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
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

	@Override
	public String toString() {
		return "AddedFlight [flightId=" + flightId + ", airlineName=" + airlineName + ", flightNo=" + flightNo
				+ ", flightModel=" + flightModel + ", fromLocation=" + fromLocation + ", toLocation=" + toLocation
				+ ", departureDateTime=" + departureDateTime + ", arrivalDateTime=" + arrivalDateTime + ", totalSeats="
				+ totalSeats + ", economySeats=" + economySeats + ", economyPrice=" + economyPrice + ", businessSeats="
				+ businessSeats + ", businessPrice=" + businessPrice + "]";
	}

	

}
