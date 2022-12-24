package application;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

import Controller.AddUpdateFlightController;
import Controller.PassengerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage)  throws ClassNotFoundException {
		// TODO Auto-generated method stub/
		try {
			FlowPane root = (FlowPane)FXMLLoader.load(getClass().getResource("/References/LoginPage.fxml"));
			Scene scene = new Scene(root,400,600);
			primaryStage.setResizable(false);//to disable the maximize button 
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		/*
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/MainPage.fxml"));
		Parent root = null;
		root = loader.load();
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Main");
		stage.setResizable(false);//to disable the maximize button 
		stage.show();*/
		/*
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/PassengerPage.fxml"));
		PassengerController controller = new PassengerController();
		loader.setController(controller);
		Parent root = null;
		root = loader.load();
		controller.setScene(null);
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Main");
		stage.setResizable(false);//to disable the maximize button 
		stage.show();*/
		/*
		Model m = new Model();
		Connection c = m.connect("sa", "bob1234");
		int f,b,e;
		double fp,bp,ep;
	      //Preparing a CallableStatement to call a procedure
	      CallableStatement cstmt = c.prepareCall("{call REMAINED_SEAT(?, ? ,?, ?, ?, ?,?)}");
	      //Setting the value for the IN parameter
	      cstmt.setString(1, "TG331");
	      //Registering the type of the OUT parameters
	      cstmt.registerOutParameter(2, Types.INTEGER);
	      cstmt.registerOutParameter(3, Types.FLOAT);
	      cstmt.registerOutParameter(4, Types.INTEGER);
	      cstmt.registerOutParameter(5, Types.FLOAT);
	      cstmt.registerOutParameter(6, Types.INTEGER);
	      cstmt.registerOutParameter(7, Types.FLOAT);
	      //Executing the CallableStatement
	      cstmt.executeUpdate();
	      //Retrieving the values for product name, customer name and, price
	      f = cstmt.getInt(2);
	      fp = cstmt.getFloat(3);
	      b = cstmt.getInt(4);
	      bp = cstmt.getFloat(5);
	      e = cstmt.getInt(6);
	      ep = cstmt.getFloat(7);
		System.out.println(f +"      "+fp+"\n"+b+"      "+bp+"\n"+e+"      "+ep+"\n");*/
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	//jdbc:sqlserver://hostname\\instance:port;databaseName=dbName
		//to set run configurations
		//arguments
		//run Arguments
		//--module-path "D:\javaFX\javafx-sdk-16\lib" --add-modules javafx.controls,javafx.fxml
	/*
	 In SQL Server Configuration Manager, in the console pane, expand SQL Server Network Configuration, 
	 select Protocols for <instance name>, and then in the right pane double-click TCP/IP.
	 Then restart the sqlExpress server from services
	 */
		/*
		 Then go to services and start the sql server browser if it is stoped
		 */


}
