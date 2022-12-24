package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

import application.Model;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PassengerController {
	@FXML
	private TextField depCountry;
	@FXML
	private TextField arrCountry;
	@FXML
	private TextField year;
	@FXML
	private TextField month;
	@FXML
	private TextField day;
	@FXML
	private Button search;
	@FXML
	private Button reset;
	@FXML
	private Label alert;
	@FXML
	private ScrollPane scroll;
	@FXML
	private FlowPane view;
	private Model model = new Model();
	private Connection myConn = model.connect("sa", "bob1234");
	private String id;
	public void setScene(ResultSet r) throws FileNotFoundException, SQLException {
		Date date = new Date();
		ResultSet q = null;
		if(r==null) {
			q = model.getData(myConn,"*","FLIGHT","DEPARTURE_TIME >= '"+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+(date.getHours())+":"+(date.getMinutes()+1)+"'");
		}else {
			q = r;
		}
		view.getChildren().clear();
		while(q.next()) {
			FlowPane main = new FlowPane();
			main.setPrefHeight(201);
			main.setPrefWidth(754);
			
			Image img = new Image(new FileInputStream("images/passengerLogo.png"));
			ImageView image = new ImageView();
			image.setImage(img);
			image.setFitHeight(201);
			image.setFitWidth(228);
			
			FlowPane inner = new FlowPane();
			inner.setPrefHeight(201);
			inner.setPrefWidth(523);
			inner.setHgap(2);
			inner.setVgap(5);
			//add the image view and the inner flow pane to the main flow pane
			main.getChildren().addAll(image,inner);
			//fill the inner flow pane with text fields and buttons
			Label IdLabel = new Label("Flight_Id:");
			IdLabel.setPrefHeight(22);
			IdLabel.setPrefWidth(90);
			IdLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			IdLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField IdText = new TextField();
			IdText.setText(q.getString("FLIGHT_NUMBER").trim());
			IdText.setLayoutX(100);
			IdText.setLayoutY(10);
			IdText.setDisable(true);
			
			Label DurationLabel = new Label("Duration:");
			DurationLabel.setLayoutX(10);
			DurationLabel.setLayoutY(12);
			DurationLabel.setPrefHeight(22);
			DurationLabel.setPrefWidth(90);
			DurationLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			DurationLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField DurationText = new TextField();
			DurationText.setText(q.getString("DURATION").trim());
			DurationText.setLayoutX(100);
			DurationText.setLayoutY(10);
			DurationText.setDisable(true);

			Label FromLabel = new Label("From:");
			FromLabel.setLayoutX(253);
			FromLabel.setLayoutY(12);
			FromLabel.setPrefHeight(22);
			FromLabel.setPrefWidth(90);
			FromLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			FromLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField FromText = new TextField();
			FromText.setText(q.getString("DEPARTURE_COUNTRY").trim());
			FromText.setLayoutX(345);
			FromText.setLayoutY(10);
			FromText.setDisable(true);

			Label ToLabel = new Label("To:");
			ToLabel.setLayoutX(253);
			ToLabel.setLayoutY(42);
			ToLabel.setPrefHeight(22);
			ToLabel.setPrefWidth(90);
			ToLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			ToLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField ToText = new TextField();
			ToText.setText(q.getString("ARRIVAL_COUNTRY").trim());
			ToText.setLayoutX(345);
			ToText.setLayoutY(40);
			ToText.setDisable(true);
			
			Label DepartureLabel = new Label("Departure:");
			DepartureLabel.setLayoutX(10);
			DepartureLabel.setLayoutY(42);
			DepartureLabel.setPrefHeight(22);
			DepartureLabel.setPrefWidth(90);
			DepartureLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			DepartureLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField DepartureText = new TextField();
			DepartureText.setText(q.getString("DEPARTURE_TIME").trim());
			DepartureText.setLayoutX(102);
			DepartureText.setLayoutY(40);
			DepartureText.setDisable(true);

			Label ArrivalLabel = new Label("Arrival:");
			ArrivalLabel.setLayoutX(10);
			ArrivalLabel.setLayoutY(72);
			ArrivalLabel.setPrefHeight(22);
			ArrivalLabel.setPrefWidth(90);
			ArrivalLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			ArrivalLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField ArrivalText = new TextField();
			ArrivalText.setText(q.getString("ARRIVAL_TIME").trim());
			ArrivalText.setLayoutX(102);
			ArrivalText.setLayoutY(10);
			ArrivalText.setDisable(true);
			/******************************/
			AnchorPane container = new AnchorPane();
			container.setPrefHeight(119);
			container.setPrefWidth(522);
			//shadow on hover
	        Shadow shadow = new Shadow();  
	        shadow.setBlurType(BlurType.GAUSSIAN);  
	        shadow.setColor(Color.BLACK);  
	        shadow.setHeight(2);  
	        shadow.setRadius(2);  
	        shadow.setWidth(2);   
			//get the data from the procedure which will return the data for us
			int firstSeats,busSeats,ecoSeats;
			double fp,bp,ep;
		    //Preparing a CallableStatement to call a procedure
		    CallableStatement cstmt = myConn.prepareCall("{call REMAINED_SEAT(?, ? ,?, ?, ?, ?,?)}");
		    //Setting the value for the IN parameter
		    cstmt.setString(1,q.getString("FLIGHT_NUMBER").trim());
		    //Registering the type of the OUT parameters
		    cstmt.registerOutParameter(2, Types.INTEGER);
		    cstmt.registerOutParameter(3, Types.FLOAT);
		    cstmt.registerOutParameter(4, Types.INTEGER);
		    cstmt.registerOutParameter(5, Types.FLOAT);
		    cstmt.registerOutParameter(6, Types.INTEGER);
		    cstmt.registerOutParameter(7, Types.FLOAT);
		    //Executing the CallableStatement
		    cstmt.executeUpdate();
		    //Retrieving the values 
		    firstSeats = cstmt.getInt(2);
		    fp = cstmt.getFloat(3);
		    busSeats = cstmt.getInt(4);
		    bp = cstmt.getFloat(5);
		    ecoSeats = cstmt.getInt(6);
		    ep = cstmt.getFloat(7);
			
		    FlowPane block1 = new FlowPane();
			block1.setAlignment(Pos.TOP_CENTER);
			block1.setLayoutX(56);
			block1.setLayoutY(8);
			block1.setOrientation(Orientation.VERTICAL);
			block1.setPrefHeight(103);
			block1.setPrefWidth(124);
			block1.setVgap(12);
			block1.setStyle("-fx-border-color: pink;");
			block1.setId(q.getString("FLIGHT_NUMBER").trim());
			block1.setOnMouseEntered(ev->{//put a listener on hover
				block1.setEffect(shadow);
			});
			block1.setOnMouseExited(ev->{//put a listener to remove shadow 
				block1.setEffect(null);
			});
			
			Label bl11 = new Label();
			bl11.setText("First class");//class
			bl11.setAlignment(Pos.CENTER);
			bl11.setPrefHeight(18);
			bl11.setPrefWidth(119);
			bl11.setFont(Font.font("Elephant",FontWeight.NORMAL,FontPosture.REGULAR,14));
			bl11.textFillProperty().set(Color.rgb(8, 177,255,1));

			Label bl12 = new Label();
			bl12.setText(""+fp+" $");//price
			bl12.setAlignment(Pos.CENTER);
			bl12.setPrefHeight(18);
			bl12.setPrefWidth(119);
			bl12.setLayoutX(34);
			bl12.setLayoutY(10);
			bl12.setFont(Font.font("Elephant",FontWeight.NORMAL,FontPosture.REGULAR,14));
			bl12.textFillProperty().set(Color.rgb(8, 177,255,1));

			Label bl13 = new Label();
			bl13.setText(""+firstSeats+" Seats Remained");//remained Seats
			bl13.setAlignment(Pos.CENTER);
			bl13.setPrefHeight(18);
			bl13.setPrefWidth(119);
			bl12.setLayoutX(34);
			bl12.setLayoutY(28);
			bl13.setFont(Font.font("Eras Light ITC",FontWeight.NORMAL,FontPosture.REGULAR,14));
			bl13.textFillProperty().set(Color.rgb(255,0,0,1));
			//lisener on click
			block1.setOnMousePressed(e->{
				if(!bl13.getText().equalsIgnoreCase("0 Seats Remained")) {
					Book("FIRST",block1.getId());
				}
				
			});
			//add to flow pane
			block1.getChildren().addAll(bl11,bl12,bl13);

			FlowPane block2 = new FlowPane();
			block2.setAlignment(Pos.TOP_CENTER);
			block2.setLayoutX(199);
			block2.setLayoutY(8);
			block2.setOrientation(Orientation.VERTICAL);
			block2.setPrefHeight(103);
			block2.setPrefWidth(124);
			block2.setVgap(12);
			block2.setStyle("-fx-border-color: pink;");
			block2.setId(q.getString("FLIGHT_NUMBER").trim());
			block2.setOnMouseEntered(ev->{//put a listener on hover
				block2.setEffect(shadow);
			});
			block2.setOnMouseExited(ev->{//put a listener to remove shadow 
				block2.setEffect(null);
			});
			
			
			Label bl21 = new Label();
			bl21.setText("Business class");//class
			bl21.setAlignment(Pos.CENTER);
			bl21.setPrefHeight(18);
			bl21.setPrefWidth(119);
			bl21.setFont(Font.font("Elephant",FontWeight.NORMAL,FontPosture.REGULAR,14));
			bl21.textFillProperty().set(Color.rgb(8, 177,255,1));

			Label bl22 = new Label();
			bl22.setText(""+bp+" $");//price
			bl22.setAlignment(Pos.CENTER);
			bl22.setPrefHeight(18);
			bl22.setPrefWidth(119);
			bl22.setFont(Font.font("Elephant",FontWeight.NORMAL,FontPosture.REGULAR,14));
			bl22.textFillProperty().set(Color.rgb(8, 177,255,1));

			Label bl23 = new Label();
			bl23.setText(""+busSeats+" Seats Remained");//remained Seats
			bl23.setAlignment(Pos.CENTER);
			bl23.setPrefHeight(18);
			bl23.setPrefWidth(119);
			bl23.setFont(Font.font("Eras Light ITC",FontWeight.NORMAL,FontPosture.REGULAR,14));
			bl23.textFillProperty().set(Color.rgb(255,0,0,1));
			//listener on click
			block2.setOnMousePressed(e->{
				if(!bl23.getText().equalsIgnoreCase("0 Seats Remained")) {
					Book("BUSINESS",block2.getId());
				}
			});
			//add to flow pane
			block2.getChildren().addAll(bl21,bl22,bl23);

			FlowPane block3 = new FlowPane();
			block3.setAlignment(Pos.TOP_CENTER);
			block3.setLayoutX(340);
			block3.setLayoutY(8);
			block3.setOrientation(Orientation.VERTICAL);
			block3.setPrefHeight(103);
			block3.setPrefWidth(124);
			block3.setVgap(12);
			block3.setStyle("-fx-border-color: pink;");
			block3.setId(q.getString("FLIGHT_NUMBER").trim());			block3.setOnMouseEntered(ev->{//put a listener on hover
				block3.setEffect(shadow);
			});
			block3.setOnMouseExited(ev->{//put a listener to remove shadow 
				block3.setEffect(null);
			});
			
			
			Label bl31 = new Label();
			bl31.setText("Economic class");//class
			bl31.setAlignment(Pos.CENTER);
			bl31.setPrefHeight(18);
			bl31.setPrefWidth(119);
			bl31.setFont(Font.font("Elephant",FontWeight.NORMAL,FontPosture.REGULAR,14));
			bl31.textFillProperty().set(Color.rgb(8, 177,255,1));

			Label bl32 = new Label();
			bl32.setText(""+ep+" $");//price
			bl32.setAlignment(Pos.CENTER);
			bl32.setPrefHeight(18);
			bl32.setPrefWidth(119);
			bl32.setFont(Font.font("Elephant",FontWeight.NORMAL,FontPosture.REGULAR,14));
			bl32.textFillProperty().set(Color.rgb(8, 177,255,1));

			Label bl33 = new Label();
			bl33.setText(""+ecoSeats+" Seats Remained");//remained Seats
			bl33.setAlignment(Pos.CENTER);
			bl33.setPrefHeight(18);
			bl33.setPrefWidth(119);
			bl33.setFont(Font.font("Eras Light ITC",FontWeight.NORMAL,FontPosture.REGULAR,14));
			bl33.textFillProperty().set(Color.rgb(255,0,0,1));
			//listener on cick
			block3.setOnMousePressed(e->{
				if(!bl33.getText().equalsIgnoreCase("0 Seats Remained")) {
					Book("ECONOMY",block3.getId());
				}
				
			});

			//add to flow pane
			block3.getChildren().addAll(bl31,bl32,bl33);			
			
			container.getChildren().addAll(block1,block2,block3);
			/******************************/
			
			
			inner.getChildren().addAll(IdLabel,IdText,DurationLabel,DurationText,FromLabel,FromText,DepartureLabel,DepartureText,ToLabel,ToText,ArrivalLabel,ArrivalText,container);
			view.getChildren().add(main);
		}		
	}
	public void Book(String type,String Id) {
		
		view.getParent().setDisable(true);//disable the view when verification
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/verifyBookPage.fxml"));
		verifyBookController controller = new verifyBookController();
		controller.setValues(type, Id);
		loader.setController(controller);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controller.setScene();
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Verify");
		stage.setResizable(false);//to disable the maximize button 
		stage.setOnHiding(ev->{
			if(stage.getUserData()==null) {
				view.getParent().setDisable(false);//enable the view when there is no payment
			}else if(stage.getUserData().equals("True")) {
				Pay(type,Id,id);
			}
		});
		stage.show();

	}
	public void Pay(String type,String fid,String pid) {
		System.out.println(pid+"\t"+fid+ "\t"+type);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/PaymentPage.fxml"));
		PaymentPageController controller = new PaymentPageController();
		controller.setValues(type, fid,pid);
		loader.setController(controller);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Pay");
		stage.setResizable(false);//to disable the maximize button 
		stage.setOnHiding(ev->{
			view.getParent().setDisable(false);//enable the view when payment view is closed
			try {
				setScene(null);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		stage.show();
		
	}
	// Event Listener on Button[#search].onAction
	@FXML
	public void search(ActionEvent event) {
		// TODO Autogenerated
		//check if the fields are empty then fill the values with specific values
		String arrC = null,depC = null;
		int y=0,m=0,d=0;
		Date date = new Date();
		if(arrCountry.getText().trim().isEmpty() && day.getText().trim().isEmpty() && month.getText().trim().isEmpty() && year.getText().trim().isEmpty() && depCountry.getText().trim().isEmpty()) {
			alert.setText("Fill at least one field to be able to search");
			return;
		}
		if(!arrCountry.getText().trim().isEmpty()) {
			arrC=arrCountry.getText().trim();
		}
		if(!depCountry.getText().trim().isEmpty()) {
			depC=depCountry.getText().trim();
		}
		if(!year.getText().trim().isEmpty()) {
			y=Integer.parseInt(year.getText().trim());
			if(y<(date.getYear()+1900)) {
				alert.setText("Enter a valid year");
			return;
		}
		}
		if(!month.getText().trim().isEmpty()) {
			m=Integer.parseInt(month.getText().trim());
			if(m < (date.getMonth()+1)) {
				alert.setText("Enter a valid month");
				return;
			}
		}
		if(!day.getText().trim().isEmpty()) {
			d=Integer.parseInt(day.getText().trim());
			if(d<date.getDate()) {
				alert.setText("Enter a valid date");
				return;
			}
		}
		if(d > 31 || d < 0 ) {
			alert.setText("Enter a valid date");
			return;
		}else if(m>12 || m<0 ) {
			alert.setText("Enter a valid month");
			return;
		}
		//execute a query
		ResultSet q = null;
		q = model.executeProcedure(myConn, "SEARCH_FLIGHT "+arrC+","+depC+","+y+","+m+","+d+"");
		//then call the setFlights with passing the result set	
		if(q == null) {//the search return null as a result
			alert.setText("Your search options are unavialable");
			return;
		}else {
			try {
				setScene(q);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			alert.setText(" ");
		}
				 				
	}
	// Event Listener on Button[#reset].onAction
	@FXML
	public void reset(ActionEvent event) {
		// TODO Autogenerated
		arrCountry.setText("");
		depCountry.setText("");
		year.setText("");
		month.setText("");
		day.setText("");
		try {
			setScene(null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void profile(ActionEvent event) {
		view.getParent().setDisable(true);//disable the view when verification
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/ProfilePage.fxml"));
		ProfilePageController controller = new ProfilePageController();
		controller.setId(id);
		loader.setController(controller);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controller.setScene();
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Profile");
		stage.setResizable(false);//to disable the maximize button 
		stage.setOnHiding(ev->{
			view.getParent().setDisable(false);//enable the view 
		});
		stage.show();
	}
	public void logout(ActionEvent event) {
		//back to logintry {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/LoginPage.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage stage = (Stage) alert.getScene().getWindow();
		stage.close();
		
		Scene scene = new Scene(root,400,600);
		stage.setScene(scene);
		stage.setTitle("Login");
		stage.setResizable(false);//to disable the maximize button 
		stage.setOnHiding(ev->{
			view.getParent().setDisable(false);//enable the view 
		});
		stage.show();
	}
	public void setId(String id) {
		this.id = id;
	}
}
