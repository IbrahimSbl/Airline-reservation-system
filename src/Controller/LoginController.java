package Controller;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.gluonhq.charm.glisten.control.TextField;

//import Customer.Customer;
import application.Model;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.*;

public class LoginController implements Initializable {
	@FXML
	private Label errorMessage; 
	@FXML
	private Button loginButton;
	@FXML
	private TextField emailTextField;
	@FXML
	private PasswordField pass;

	private Model model=new Model();
	private Connection myConn = model.connect("sa", "bob1234");//jo70872589
	
	public void loginOnAction(ActionEvent event) throws SQLException{
		if((emailTextField.getText().isEmpty() == false) && (pass.getText().isEmpty() == false)) {
			validLogin();
            
        }
		else {
			errorMessage.setText("Invalid Login. Fill the empty fields.");
		}
	}

	public void validLogin() throws SQLException{
		ResultSet q = model.getData(myConn,"PASS_ID,COUNT(*) AS COUNTNB", "PASSENGER","EMAIL='"+emailTextField.getText()+"' AND PASSWORD='"+pass.getText()+"' GROUP BY PASS_ID");
		
		FXMLLoader loader;
		Parent root = null;
		Stage stage = new Stage();
		Scene scene = null;
		String emailaddress = "ibrahim@gmail.com";
		String password = "admin100";
		
		if((emailaddress).equals(emailTextField.getText()) && (password).equals(pass.getText())){
			errorMessage.setText("");			
			try {
				//AnchorPane root;
				loader = new FXMLLoader(getClass().getResource("/References/MainPage.fxml"));/*
				MainController controller = new MainController();
				loader.setController(controller);*/
				stage = (Stage)errorMessage.getScene().getWindow();
				stage.close();//close current window
				root = loader.load();
				scene = new Scene(root);
				stage.setTitle("Welcome Admin");
				stage.setScene(scene);
				stage.setResizable(false);//to disable the maximize button 
				stage.show();
				
			} 
			
			catch(Exception e) {
				e.printStackTrace();
			}
		}else if(q.next()) {
			if(q.getInt("COUNTNB") == 1){
				errorMessage.setText("");
				try {
					loader = new FXMLLoader(getClass().getResource("/References/PassengerPage.fxml"));
					PassengerController controller = new PassengerController();
					controller.setId(q.getString("PASS_ID").trim());
					loader.setController(controller);
					stage = (Stage)errorMessage.getScene().getWindow();
					stage.close();//close current window
					root = loader.load();
					controller.setScene(null);
					scene = new Scene(root);
					stage.setResizable(false);//to disable the maximize button 
					stage.setTitle("Welcome Passenger");
					stage.setScene(scene);
					stage.show();	
				} 
				
				catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		
		else {
			errorMessage.setText("Invalid login, try another one");
		}
		
	}
	
	public void createAccount(ActionEvent event) {
		try {
			AnchorPane root;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/RegisterPage.fxml"));
			Stage stage = (Stage)errorMessage.getScene().getWindow();
			stage.close();//close current window
			root = loader.load();
			Stage registerStage = new Stage();
			Scene scene = new Scene(root,520,434);
			registerStage.setResizable(false);//to disable the maximize button 
			registerStage.setTitle("Registration");
			registerStage.setScene(scene);
			registerStage.show();
			
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
		


}