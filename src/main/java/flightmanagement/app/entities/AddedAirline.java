package flightmanagement.app.entities;

public class AddedAirline {
	private int airLineId;
    private String airlineName;
    private String airlineNumber;
    private String modelNumber;
    
    
    

  
    public AddedAirline() {
		super();
		
	}

	public AddedAirline(int airLineId, String airlineName, String airlineNumber, String modelNumber) {
		super();
		this.airLineId = airLineId;
		this.airlineName = airlineName;
		this.airlineNumber = airlineNumber;
		this.modelNumber = modelNumber;
	}
	
	

	public AddedAirline(String airlineName, String airlineNumber, String modelNumber) {
		super();
		this.airlineName = airlineName;
		this.airlineNumber = airlineNumber;
		this.modelNumber = modelNumber;
	}

	public int getAirLineId() {
		return airLineId;
	}

	public void setAirLineId(int airLineId) {
		this.airLineId = airLineId;
	}

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
		return "\n AddedAirline [airLineId=" + airLineId + ", airlineName=" + airlineName + ", airlineNumber="
				+ airlineNumber + ", modelNumber=" + modelNumber + "]";
	}

	
    
    
}
