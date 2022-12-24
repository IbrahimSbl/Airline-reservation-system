package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Classes.Flight;
import application.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class FlightHistoryController {
	@FXML
	private TableView<Flight> table;
	@FXML
	private TableColumn<Flight,String> fnum;
	@FXML
	private TableColumn<Flight,String> arrcountry;
	@FXML
	private TableColumn<Flight,String> depcountry;
	@FXML
	private TableColumn<Flight,String> arrtime;
	@FXML
	private TableColumn<Flight,String> deptime;
	@FXML
	private TableColumn<Flight,Float> duration;
	private String id;
	private Model model = new Model();
	private Connection myConn = model.connect("sa", "bob1234");
	public void setScene(String role) throws SQLException {
		ResultSet q;
		fnum.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
		arrcountry.setCellValueFactory(new PropertyValueFactory<>("arrivalCountry"));
		depcountry.setCellValueFactory(new PropertyValueFactory<>("departureCountry"));
		arrtime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
		deptime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
		duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
		ObservableList<Flight> list = FXCollections.observableArrayList();
		if(role.equalsIgnoreCase("pilot")) {
				
			q= model.getData(myConn,"*", "FLIGHT", "PILOT = '"+id+"'");//SELECT FROM FLIGHT THE PILOT WICH IS THE ID OF THE EMPLOYEE
		}else if(role.equalsIgnoreCase("airport assistant")) {
			q= model.getData(myConn, "F.FLIGHT_NUMBER,F.ARRIVAL_COUNTRY,F.DEPARTURE_COUNTRY,F.ARRIVAL_TIME,F.DEPARTURE_TIME,F.DURATION","ASSISTANT AS A,FLIGHT AS F", "A.EMPLOYEE_ID = '"+id+"' AND F.FLIGHT_NUMBER = A.FLIGHT_NUMBER");
		}else if(role.equalsIgnoreCase("AirPlane")) {
			q = model.getData(myConn,"F.FLIGHT_NUMBER,F.ARRIVAL_COUNTRY,F.DEPARTURE_COUNTRY,F.ARRIVAL_TIME,F.DEPARTURE_TIME,F.DURATION", "FLIGHT AS F", "F.AID='"+id+"'");
		}else {
			return;
		}
		while(q.next()) {
			list.add(new Flight(q.getString("FLIGHT_NUMBER"),q.getString("ARRIVAL_COUNTRY"),q.getString("DEPARTURE_COUNTRY"),q.getString("ARRIVAL_TIME"),q.getString("DEPARTURE_TIME"),q.getFloat("DURATION")));
		}
		table.setItems(list);
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
