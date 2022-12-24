package Controller;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gluonhq.charm.glisten.control.TextField;

import application.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ProfilePageController {
	@FXML
	private Button updateb;
	@FXML
	private FlowPane main;
	@FXML
	private TextField name;
	@FXML
	private TextField phone;
	@FXML
	private TextField birthday;
	@FXML
	private TextField address;
	private Model model = new Model();
	private Connection myConn = model.connect("sa", "bob1234");
	private String id;
	public void setScene() {
		ResultSet q = model.getData(myConn,"*","PASSENGER","PASS_ID = '"+id+"'");
		try {
			if(q.next()){
				System.out.println("setting");
				if(q.getString("BIRTHDAY") != null) {
					birthday.setText(q.getString("BIRTHDAY").trim());
				}else {
					birthday.setText("");
				}

				if(q.getString("PHONE_NUMBER") != null) {
					phone.setText(q.getString("PHONE_NUMBER").trim());
				}else {
					phone.setText("");
				}
				if(q.getString("ADDRESS") != null) {
					address.setText(q.getString("ADDRESS").trim());
				}else {
					address.setText("");
				}	
				name.setText(q.getString("NAME").trim());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		birthday.setDisable(true);
		address.setDisable(true);
		name.setDisable(true);
		phone.setDisable(true);
	}
	@FXML
	public void update(ActionEvent event) {
		String query = "";
		if(updateb.getText().equals("Update Profile")) {
			updateb.setText("Done");

			birthday.setDisable(false);
			address.setDisable(false);
			name.setDisable(false);
			phone.setDisable(false);
		}else if(updateb.getText().equals("Done")) {
			updateb.setText("Update Profile");
			birthday.setDisable(true);
			address.setDisable(true);
			name.setDisable(true);
			phone.setDisable(true);
			model.update(myConn, "PASSENGER", "NAME = '"+name.getText().trim()+"',BIRTHDAY ='"+birthday.getText().trim()+"',ADDRESS = '"+address.getText().trim()+"',PHONE_NUMBER = '"+phone.getText().trim()+"'", "PASS_ID ='"+id+"'");
			Stage stage1 = (Stage) name.getScene().getWindow();
			stage1.close();
		}
		
	}
	public void setId(String id) {
		if(Integer.parseInt(id) < 10) {
			id = "0"+id;
		}
		this.id = id;
	}
}
