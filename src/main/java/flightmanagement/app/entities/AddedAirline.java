package flightmanagement.app.entities;

public class AddedAirline {
    private String airlineName;
    private String airlineNumber;
    private String modelNumber;

  
    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineNumber() {
        return airlineNumber;
    }

    public void setAirlineNumber(String airlineNumber) {
        this.airlineNumber = airlineNumber;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

	@Override
	public String toString() {
		return "Airline [airlineName=" + airlineName + ", airlineNumber=" + airlineNumber + ", modelNumber="
				+ modelNumber + "]";
	}
    
    
}
