package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Classes.Employee;
import Classes.Passenger;
import application.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class PassengersHistoryController {
	@FXML
	private TableView<Passenger> table;
	@FXML
	private TableColumn<Passenger,String> idcol;
	@FXML
	private TableColumn<Passenger,String> namecol;
	@FXML
	private TableColumn<Passenger,String> birthcol;
	@FXML
	private TableColumn<Passenger,String> addresscol;
	@FXML
	private TableColumn<Passenger,String> phonecol;

	private String id;
	private Model model = new Model();
	private Connection myConn = model.connect("sa", "bob1234");
	public void setScene() throws SQLException {
		ResultSet q;
		idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
		namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
		birthcol.setCellValueFactory(new PropertyValueFactory<>("birth"));
		addresscol.setCellValueFactory(new PropertyValueFactory<>("address"));
		phonecol.setCellValueFactory(new PropertyValueFactory<>("phone"));
		ObservableList<Passenger> list = FXCollections.observableArrayList();
		
		q= model.executeProcedure(myConn, "FLIGHT_PASSENGERS '"+id+"'");//SELECT FROM PASSENGER WHERE THE ID EQUAL THAT FOUND IN THE RESERVATIONS OF A CERTIAN FLIGHT
		
		while(q.next()) {
			list.add(new Passenger(q.getString("PASS_ID"),q.getString("NAME"),q.getString("BIRTHDAY"),q.getString("ADDRESS"),q.getString("PHONE_NUMBER")));
		}
		table.setItems(list);
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
