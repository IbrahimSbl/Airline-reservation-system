package Controller;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.sql.ResultSet;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class RegisterController implements Initializable{
	@FXML
	private Label successMessage;
	@FXML
	private PasswordField pass;
	@FXML
	private PasswordField confirmPass;
	@FXML
	private Label passError;
	@FXML
	private TextField fnTextField;
	@FXML
	private TextField lnTextField;
	@FXML
	private TextField emailTextField;

	private Model model = new Model();
	private Connection myConn = model.connect("sa", "bob1234");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}		

	
	public void registerButtonOnAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException{
		
		
		if(fnTextField.getText().isEmpty() || lnTextField.getText().isEmpty() ) {
			passError.setText("Please enter your full name");
			successMessage.setText("");
            return;
        }
        if(emailTextField.getText().isEmpty()) {
        	passError.setText("Please enter your email address");
			successMessage.setText("");
            return;
        }
		else if(!emailTextField.getText().isEmpty()){//check if entered email is in the database
			ResultSet q = model.getData(myConn,"PASS_ID,COUNT(*) AS COUNTNB", "PASSENGER","EMAIL='"+emailTextField.getText()+"' AND PASSWORD='"+pass.getText()+"' GROUP BY PASS_ID");
			try {
				while(q.next()) {
					if(q.getInt("COUNTNB") == 1){
						passError.setText("This email address already exits, try another one");
						return;
					}
				}
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
    
        if(pass.getText().isEmpty()) {
        	passError.setText("Please enter a password");
			successMessage.setText("");
            return;
        }
        if(pass.getText().equals(confirmPass.getText())) {
			registerUser();
		}
		else {
			passError.setText("Password does not match");
			successMessage.setText("");
		}
		
	}
	
	public void registerUser() throws SQLException, ClassNotFoundException, IOException  {
		try {
			ResultSet q=model.getData(myConn,"MAX(PASS_ID)","PASSENGER","");
			int id=1;
			if(q.next()) {
				id = q.getInt(1) + 1;
			}
			//model.Insert(myConn, "PASSENGER", "'"+fnTextField.getText().trim()+"','"+ "NULL" +"','"+"','"+ "NULL" +"','"+"','"+ "NULL" +"','"+emailTextField.getText().trim()+"','"+pass.getText().trim()+"'");
			model.Insert(myConn, "PASSENGER (pass_id,name, email, password)","'"+id+ "','"+fnTextField.getText().trim()+"','"+emailTextField.getText().trim()+"','"+pass.getText().trim()+"'");
			passError.setText("");
			successMessage.setText("User has been registered successfuly");
			Stage stage = (Stage)fnTextField.getScene().getWindow();
			stage.close();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/LoginPage.fxml"));
			Parent root = null;
			root = loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Login");
			stage.setResizable(false);//to disable the maximize button 
			stage.show();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			passError.setText("Can't add try agian");
			e.printStackTrace();
			}
		}
	
}
