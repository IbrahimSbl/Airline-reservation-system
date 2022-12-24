package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Classes.Employee;
import Classes.Flight;
import application.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

public class EmployeesHistoryController {
	@FXML
	private TableView<Employee> table;
	@FXML
	private TableColumn<Employee,String> idcol;
	@FXML
	private TableColumn<Employee,String> namecol;
	@FXML
	private TableColumn<Employee,String> jobcol;
	@FXML
	private TableColumn<Employee,String> emailcol;
	@FXML
	private TableColumn<Employee,String> addresscol;
	@FXML
	private TableColumn<Employee,String> phonecol;
	@FXML
	private TextField employeeId;
	@FXML
	private Label alert;
	private String id;
	private Model model = new Model();
	private Connection myConn = model.connect("sa", "bob1234");
	private boolean pilot = false;
	public void setScene() throws SQLException {
		ResultSet q;
		idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
		namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
		jobcol.setCellValueFactory(new PropertyValueFactory<>("job"));
		emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
		addresscol.setCellValueFactory(new PropertyValueFactory<>("address"));
		phonecol.setCellValueFactory(new PropertyValueFactory<>("phone"));
		ObservableList<Employee> list = FXCollections.observableArrayList();
		
		q= model.executeProcedure(myConn, "FLIGHT_EMPLOYEES '"+id+"'");//SELECT FROM EMPLOYEE WHERE THE ID EQUAL THAT OF OUR THE EMPLOYEE
		
		while(q.next()) {
			list.add(new Employee(q.getString("EMPLOYEE_ID"),q.getString("EMPLOYEE_NAME"),q.getString("JOB_TITLE"),q.getString("EMAIL"),q.getString("ADDRESS"),q.getString("PHONE_NUMBER")));
			if(q.getString("JOB_TITLE").equalsIgnoreCase("PILOT")) {
				pilot = true;
			}
		}
		table.setItems(list);
	}
	public void setId(String id) {
		this.id = id;
	}
	@FXML
	public void addEmployee(ActionEvent event) {
		if(employeeId.getText().trim().isEmpty()) {
			alert.setText("Enter an employee id");
			return;
		}
		ResultSet q = model.getData(myConn, "EMPLOYEE_ID,JOB_TITLE","EMPLOYEE","EMPLOYEE_ID='"+employeeId.getText().trim()+"'");
		try {
			if(q.next()) {
				if(q.getString("JOB_TITLE").trim().equalsIgnoreCase("pilot")) {//if the entered id refers to a pilot
					if(pilot) {//if we already have a pilot through an error
						alert.setText("Can't add more than one pilot");
						return;
					}
				}else {
						try {
							model.Insert(myConn,"ASSISTANT", "'"+employeeId.getText().trim()+"','"+id.trim()+"'");
							setScene();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							alert.setText("Can't add try again");
							e.printStackTrace();
						}
				}
			}else {
				alert.setText("Employee not found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
