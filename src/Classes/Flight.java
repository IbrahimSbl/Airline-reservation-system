package Classes;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class Flight {
	private SimpleStringProperty flightNumber;
	private SimpleStringProperty arrivalCountry;
	private SimpleStringProperty departureCountry;
	private SimpleStringProperty arrivalTime;
	private SimpleStringProperty departureTime;
	private SimpleFloatProperty duration;
	public Flight(String fnum,String arrCount,String depCount,String arrTime,String depTime,Float duration) {
		this.flightNumber = new SimpleStringProperty(fnum);
		this.arrivalCountry = new SimpleStringProperty(arrCount);
		this.departureCountry = new SimpleStringProperty(depCount);
		this.arrivalTime = new SimpleStringProperty(arrTime);
		this.departureTime = new SimpleStringProperty(depTime);
		this.duration = new SimpleFloatProperty(duration);
	}
	public String getFlightNumber() {
		return flightNumber.get();
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = new SimpleStringProperty(flightNumber);
	}

	public String getArrivalCountry() {
		return arrivalCountry.get();
	}

	public void setArrivalCountry(String arrivalCountry) {
		this.arrivalCountry = new SimpleStringProperty(arrivalCountry);
	}

	public String getDepartureCountry() {
		return departureCountry.get();
	}

	public void setDepartureCountry(String departureCountry) {
		this.departureCountry = new SimpleStringProperty(departureCountry);
	}

	public String getArrivalTime() {
		return arrivalTime.get();
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = new SimpleStringProperty(arrivalTime);
	}

	public String getDepartureTime() {
		return departureTime.get();
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = new SimpleStringProperty(departureTime);
	}

	public Float getDuration() {
		return duration.get();
	}

	public void setDuration(Float duration) {
		this.duration = new SimpleFloatProperty(duration);
	}
	
}
