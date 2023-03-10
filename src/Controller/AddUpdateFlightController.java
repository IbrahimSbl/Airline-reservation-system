package Controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import application.Model;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.DatePicker;

public class AddUpdateFlightController {
	@FXML
	private TextField id;
	@FXML
	private TextField name;
	@FXML
	private TextField pid;
	@FXML
	private TextField seats;
	@FXML
	private TextField depC;
	@FXML
	private TextField arrC;
	@FXML
	private TextField depHour;
	@FXML
	private TextField depMin;
	@FXML
	private TextField depYear;
	@FXML
	private TextField depMonth;
	@FXML
	private TextField depDay;
	@FXML
	private TextField arrHour;
	@FXML
	private TextField arrMin;
	@FXML
	private TextField arrYear;
	@FXML
	private TextField arrMonth;
	@FXML
	private TextField arrDay;
	@FXML
	private TextField duration;
	@FXML
	private Button update;
	@FXML
	private Label alert;
	private String fid;
	private Model model = new Model();
	private Connection myConn = model.connect("sa", "bob1234");
	public void setScene(String option) throws SQLException {
		if(option.equalsIgnoreCase("update")) {
			update.setText("Update");
			ResultSet q = model.getData(myConn, "*","FLIGHT","FLIGHT_NUMBER = '"+fid+"'");
			if(q.next()) {
				id.setDisable(true);
				id.setText(q.getString("FLIGHT_NUMBER").trim());
				name.setText(q.getString("PILOT").trim());
				ResultSet r = model.getData(myConn,"NUMBER_OF_SEATS","AIRPLANE", "AIRPLANE_ID = '"+q.getString("AID").trim()+"'");
				String nbSeat = "0";
				if(r.next()) {
					nbSeat = r.getString("NUMBER_OF_SEATS");
				}
				pid.setText(q.getString("AID").trim());
				seats.setText(nbSeat.trim());
				depC.setText(q.getString("DEPARTURE_COUNTRY").trim());
				arrC.setText(q.getString("ARRIVAL_COUNTRY").trim());
				duration.setText(q.getString("DURATION"));
				String dateI[] = q.getString("ARRIVAL_TIME").trim().split(" ");
				String date[] = dateI[0].split("-");
				String time[] = dateI[1].split(":");
				arrHour.setText(time[0]);
				arrMin.setText(time[1]);
				arrYear.setText(date[0]);
				arrMonth.setText(date[1]);
				arrDay.setText(date[2]);
				

				dateI = q.getString("DEPARTURE_TIME").trim().split(" ");
				date = dateI[0].split("-");
				time = dateI[1].split(":");
				depHour.setText(time[0]);
				depMin.setText(time[1]);
				depYear.setText(date[0]);
				depMonth.setText(date[1]);
				depDay.setText(date[2]);
			}
		}else if(option.equalsIgnoreCase("add")) {
			update.setText("ADD");
		}
	}
	// Event Listener on Button[#update].onAction
	@FXML
	public void update(ActionEvent event){
		// TODO Autogenerated
		ResultSet q;
		if(id.getText().trim().isEmpty() || name.getText().trim().isEmpty() || pid.getText().trim().isEmpty() || depC.getText().trim().isEmpty() || arrC.getText().trim().isEmpty() || depHour.getText().trim().isEmpty() || depMin.getText().trim().isEmpty() || depYear.getText().trim().isEmpty() || depMonth.getText().trim().isEmpty() || depDay.getText().trim().isEmpty() ||  arrHour.getText().trim().isEmpty() || arrMin.getText().trim().isEmpty() || arrYear.getText().trim().isEmpty() || arrMonth.getText().trim().isEmpty() || arrDay.getText().trim().isEmpty() || duration.getText().trim().isEmpty()) {
			alert.setText("Fill empty field");
			return;
		}else if(Integer.parseInt(arrDay.getText())>31 || Integer.parseInt(arrDay.getText())<=0) {
			alert.setText("Non valid arrival day");
			return;
		}else if(Integer.parseInt(depDay.getText())>31 || Integer.parseInt(depDay.getText())<=0) {
			alert.setText("Non valid departure day");
			return;
		}else if(Integer.parseInt(arrMonth.getText())>12 || Integer.parseInt(arrMonth.getText())<=0) {
			alert.setText("Non valid arrival month");
			return;
		}else if(Integer.parseInt(depMonth.getText())>12 || Integer.parseInt(depMonth.getText())<=0) {
			alert.setText("Non valid departure month");
			return;
		}else if(Integer.parseInt(depHour.getText())>24 || Integer.parseInt(depHour.getText())<0 || Integer.parseInt(arrHour.getText())>24 || Integer.parseInt(arrHour.getText())<0) {
			alert.setText("Non valid hour");
			return;
		}else if(Integer.parseInt(depMonth.getText())>12 || Integer.parseInt(depMonth.getText())<=0 || Integer.parseInt(arrMonth.getText())>12 || Integer.parseInt(arrMonth.getText())<=0) {
			alert.setText("Non valid month");
			return;
		}else {//check if entered plane is invalid 
			q = model.getData(myConn, "*", "AIRPLANE", "AIRPLANE_ID = '"+pid.getText().trim()+"'"); 
			try {
				if(q.next()) {
					seats.setText(q.getString("NUMBER_OF_SEATS").trim());
				}else {
					alert.setText("Plane not found");
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//check if the arrival date is less than the departure date
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date arr = null ;
			Date dep = null;
			try {
				arr = format.parse(arrYear.getText().trim()+"-"+arrMonth.getText().trim()+"-"+arrDay.getText().trim()+" "+arrHour.getText().trim()+":"+arrMin.getText().trim()+":00");
				dep = format.parse(depYear.getText().trim()+"-"+depMonth.getText().trim()+"-"+depDay.getText().trim()+" "+depHour.getText().trim()+":"+depMin.getText().trim()+":00");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(format.format(arr));
			System.out.println(arr.toString());
			System.out.println(format.format(dep));
			System.out.println(dep.toString());
			if(dep.compareTo(arr)>0) {
				alert.setText("Departure date is greater than arrival!!");
				return;
			}
		}
		if(update.getText().equals("Update")) {
			model.update(myConn,"FLIGHT","FLIGHT_NUMBER ='"+id.getText().trim()+"',PILOT='"+name.getText().trim()+"',AID = '"+pid.getText().trim()+"',ARRIVAL_COUNTRY ='"+arrC.getText().trim()+"',DEPARTURE_COUNTRY ='"+depC.getText().trim()+"',ARRIVAL_TIME='"+arrYear.getText().trim()+"-"+arrMonth.getText().trim()+"-"+arrDay.getText().trim()+" "+arrHour.getText().trim()+":"+arrMin.getText().trim()+":00',DEPARTURE_TIME='"+depYear.getText().trim()+"-"+depMonth.getText().trim()+"-"+depDay.getText().trim()+" "+depHour.getText().trim()+":"+depMin.getText().trim()+":00',DURATION='"+duration.getText().trim()+"'", "FLIGHT_NUMBER = '"+fid+"'");
			alert.setText("Updated successfully");
		}else if(update.getText().equals("ADD")) {
			try {
				model.Insert(myConn, "FLIGHT", "'"+id.getText().trim()+"','"+name.getText().trim()+"','"+pid.getText().trim()+"','"+arrC.getText().trim()+"','"+depC.getText().trim()+"','"+arrYear.getText().trim()+"-"+arrMonth.getText().trim()+"-"+arrDay.getText().trim()+" "+arrHour.getText().trim()+":"+arrMin.getText().trim()+":00','"+depYear.getText().trim()+"-"+depMonth.getText().trim()+"-"+depDay.getText().trim()+" "+depHour.getText().trim()+":"+depMin.getText().trim()+":00','"+duration.getText().trim()+"'");
				alert.setText("Added successfully");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				alert.setText("Can't add try agian");
				e.printStackTrace();
			}
		}
	}
	public void setId(String id) {
		this.fid = id;
	}
	
}
