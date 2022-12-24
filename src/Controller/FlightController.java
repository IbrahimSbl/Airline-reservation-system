package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Model;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FlightController {
	@FXML
	private ScrollPane scroll;
	@FXML
	private FlowPane view;
	@FXML
	private TextField arrCountry;
	@FXML
	private TextField depCountry;
	@FXML
	private TextField year;
	@FXML
	private TextField month;
	@FXML
	private TextField day;
	@FXML
	private Label alert;
	@FXML
	private Button search;
	@FXML
	private Button reset;
	
	private Model model = new Model();
	Connection myConn = model.connect("sa", "bob1234");
	public void setScene() throws FileNotFoundException, SQLException {/*
		Image img = new Image(new FileInputStream("images/skyplane.jpg"));
        BackgroundImage bImg = new BackgroundImage(img,
                                                   BackgroundRepeat.NO_REPEAT,
                                                   BackgroundRepeat.NO_REPEAT,
                                                   BackgroundPosition.DEFAULT,
                                                   new BackgroundSize(1,1,true,true,false,false));
        Background bGround = new Background(bImg);
        view.setBackground(bGround);*/
        setFlights(null);//set result set to null since it is first entry
	}
	public void setFlights(ResultSet result) throws FileNotFoundException, SQLException {
		ResultSet q = result;
		if(q == null) {//if the passed result set is == null
			q = model.getData(myConn,"*","FLIGHT","");//then show all the flights
		}
		view.getChildren().clear();
		while(q.next()) {
			FlowPane main = new FlowPane();
			main.setPrefHeight(165);
			main.setPrefWidth(617);
			main.setOrientation(Orientation.VERTICAL);
			
			Image img = new Image(new FileInputStream("images/flightlogo.png"));
			ImageView image = new ImageView();
			image.setImage(img);
			image.setFitHeight(132);
			image.setFitWidth(157);
			
			FlowPane inner = new FlowPane();
			inner.setPrefHeight(154);
			inner.setPrefWidth(485);
			inner.setHgap(2);
			inner.setVgap(5);
			inner.setOrientation(Orientation.HORIZONTAL);
			//add the image view and the inner flow pane to the main flow pane
			main.getChildren().addAll(image,inner);
			//fill the inner flow pane with text fields and buttons
			Label IdLabel = new Label("ID:");
			IdLabel.setPrefHeight(22);
			IdLabel.setPrefWidth(90);
			IdLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			IdLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField IdText = new TextField();
			IdText.setText(q.getString("FLIGHT_NUMBER").trim());
			IdText.setLayoutX(70);
			IdText.setLayoutY(10);
			IdText.setDisable(true);
			
			Label ArrivalLabel = new Label("Arrival:");
			ArrivalLabel.setLayoutX(10);
			ArrivalLabel.setLayoutY(12);
			ArrivalLabel.setPrefHeight(22);
			ArrivalLabel.setPrefWidth(90);
			ArrivalLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			ArrivalLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField ArrivalText = new TextField();
			ArrivalText.setText(q.getString("ARRIVAL_COUNTRY").trim());
			ArrivalText.setLayoutX(70);
			ArrivalText.setLayoutY(10);
			ArrivalText.setDisable(true);

			Label FromLabel = new Label("From:");
			FromLabel.setLayoutX(219);
			FromLabel.setLayoutY(12);
			FromLabel.setPrefHeight(22);
			FromLabel.setPrefWidth(90);
			FromLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			FromLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField FromText = new TextField();
			FromText.setText(q.getString("DEPARTURE_TIME").trim());
			FromText.setLayoutX(70);
			FromText.setLayoutY(10);
			FromText.setDisable(true);

			Label DepartureLabel = new Label("Departure:");
			DepartureLabel.setLayoutX(10);
			DepartureLabel.setLayoutY(37);
			DepartureLabel.setPrefHeight(22);
			DepartureLabel.setPrefWidth(90);
			DepartureLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			DepartureLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField DepartureText = new TextField();
			DepartureText.setText(q.getString("DEPARTURE_COUNTRY").trim());
			DepartureText.setLayoutX(70);
			DepartureText.setLayoutY(10);
			DepartureText.setDisable(true);

			Label ToLabel = new Label("To:");
			ToLabel.setLayoutX(219);
			ToLabel.setLayoutY(37);
			ToLabel.setPrefHeight(22);
			ToLabel.setPrefWidth(90);
			ToLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			ToLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField ToText = new TextField();
			ToText.setText(q.getString("ARRIVAL_TIME").trim());
			ToText.setLayoutX(70);
			ToText.setLayoutY(10);
			ToText.setDisable(true);

			Label DurationLabel = new Label("Duration:");
			DurationLabel.setLayoutX(10);
			DurationLabel.setLayoutY(72);
			DurationLabel.setPrefHeight(22);
			DurationLabel.setPrefWidth(90);
			DurationLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			DurationLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField DurationText = new TextField();
			DurationText.setText(q.getString("DURATION").trim());
			DurationText.setLayoutX(102);
			DurationText.setLayoutY(70);
			DurationText.setDisable(true);
			
			
			Label PlaneLabel = new Label("Plane_Id:");
			PlaneLabel.setLayoutX(10);
			PlaneLabel.setLayoutY(72);
			PlaneLabel.setPrefHeight(22);
			PlaneLabel.setPrefWidth(90);
			PlaneLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			PlaneLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField PlaneText = new TextField();
			PlaneText.setText(q.getString("AID").trim());
			PlaneText.setLayoutX(102);
			PlaneText.setLayoutY(70);
			PlaneText.setDisable(true);
			
			ResultSet r = model.getData(myConn,"NUMBER_OF_SEATS","AIRPLANE", "AIRPLANE_ID = '"+q.getString("AID").trim()+"'");
			String nbSeat = "0";
			if(r.next()) {
				nbSeat = r.getString("NUMBER_OF_SEATS");
			}
			Label SeatsLabel = new Label("Seats:");
			SeatsLabel.setLayoutX(10);
			SeatsLabel.setLayoutY(72);
			SeatsLabel.setPrefHeight(22);
			SeatsLabel.setPrefWidth(90);
			SeatsLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			SeatsLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField SeatsText = new TextField();
			SeatsText.setText(nbSeat);
			SeatsText.setLayoutX(102);
			SeatsText.setLayoutY(70);
			SeatsText.setDisable(true);
			
			Button Delete = new Button("Delete");
			Delete.setPrefHeight(40);
			Delete.setPrefWidth(117);
			Delete.setStyle("-fx-background-color:lightblue;-fx-mnemonicParsing:false;");
			Image dimg = new Image(new FileInputStream("images/delete.png"));
			ImageView dimage = new ImageView();
			dimage.setFitHeight(26);
			dimage.setFitWidth(26);
			dimage.setImage(dimg);
			Delete.setGraphic(dimage);
			Delete.setId(q.getString("FLIGHT_NUMBER").trim());
			Delete.setOnAction(e->{
				delete(Delete.getId());
			});

			Button Update = new Button("Update");
			Update.setPrefHeight(40);
			Update.setPrefWidth(117);
			Update.setLayoutX(10);
			Update.setLayoutY(130);
			Update.setStyle("-fx-background-color:lightblue;-fx-mnemonicParsing:false;");
			Image uimg = new Image(new FileInputStream("images/update.png"));
			ImageView uimage = new ImageView();
			uimage.setFitHeight(26);
			uimage.setFitWidth(26);
			uimage.setImage(uimg);
			Update.setGraphic(uimage);
			Update.setId(q.getString("FLIGHT_NUMBER").trim());
			Update.setOnAction(e->{
				update(Update.getId());
			});

			Button Passengers = new Button("Passengers");
			Passengers.setPrefHeight(40);
			Passengers.setPrefWidth(117);
			Passengers.setLayoutX(112);
			Passengers.setLayoutY(130);
			Passengers.setStyle("-fx-background-color:lightblue;-fx-mnemonicParsing:false;");
			Image pimg = new Image(new FileInputStream("images/history.png"));
			ImageView pimage = new ImageView();
			pimage.setFitHeight(26);
			pimage.setFitWidth(26);
			pimage.setImage(pimg);
			Passengers.setGraphic(pimage);
			Passengers.setId(q.getString("FLIGHT_NUMBER").trim());
			Passengers.setOnAction(e->{
				showPassengerHistory(Passengers.getId());
			});

			Button Employees = new Button("Employees");
			Employees.setPrefHeight(40);
			Employees.setPrefWidth(117);
			Employees.setLayoutX(112);
			Employees.setLayoutY(130);
			Employees.setStyle("-fx-background-color:lightblue;-fx-mnemonicParsing:false;");
			Image eimg = new Image(new FileInputStream("images/employeeHistory.png"));
			ImageView eimage = new ImageView();
			eimage.setFitHeight(26);
			eimage.setFitWidth(26);
			eimage.setImage(eimg);
			Employees.setGraphic(eimage);
			Employees.setId(q.getString("FLIGHT_NUMBER").trim());
			Employees.setOnAction(e->{
				showEmployeeHistory(Employees.getId());
			});
			
			inner.getChildren().addAll(IdLabel,IdText,DepartureLabel,DepartureText,FromLabel,FromText,ArrivalLabel,ArrivalText,ToLabel,ToText,DurationLabel,DurationText,PlaneLabel,PlaneText,SeatsLabel,SeatsText,Delete,Update,Passengers,Employees);
			view.getChildren().add(main);
		}
	
	}
	
	@FXML
	public void search(ActionEvent event) {
		//check if the fields are empty then fill the values with specific values
		String arrC = null,depC = null;
		int y=0,m=0,d=0;
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
		}
		if(!month.getText().trim().isEmpty()) {
			m=Integer.parseInt(month.getText().trim());
		}
		if(!day.getText().trim().isEmpty()) {
			d=Integer.parseInt(day.getText().trim());
		}
		if(d > 31 || d < 0) {
			alert.setText("Enter a valid date");
			return;
		}else if(m>12 || m<0) {
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
				setFlights(q);
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
	@FXML
	public void reset(ActionEvent event) {
		arrCountry.setText("");
		depCountry.setText("");
		year.setText("");
		month.setText("");
		day.setText("");
		try {
			setFlights(null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void showEmployeeHistory(String id) {
		// TODO Auto-generated method stub
		view.getParent().setDisable(true);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/EmployeesHistory.fxml"));
		EmployeesHistoryController controller = new EmployeesHistoryController();
		controller.setId(id);
		loader.setController(controller);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			controller.setScene();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("OverView");
		stage.setResizable(false);//to disable the maximize button 
		stage.setOnHiding(e->{
			view.getParent().setDisable(false);
		});
		stage.show();
	}
	private void showPassengerHistory(String id) {
		// TODO Auto-generated method stub
		view.getParent().setDisable(true);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/PassengersHistory.fxml"));
		PassengersHistoryController controller = new PassengersHistoryController();
		controller.setId(id);
		loader.setController(controller);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			controller.setScene();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("OverView");
		stage.setResizable(false);//to disable the maximize button 
		stage.setOnHiding(e->{
			view.getParent().setDisable(false);
		});
		stage.show();
		
	}
	private void update(String id) {
		// TODO Auto-generated method stub
		view.getParent().setDisable(true);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/AddUpdateFlight.fxml"));
		AddUpdateFlightController controller = new AddUpdateFlightController();
		controller.setId(id);
		loader.setController(controller);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			controller.setScene("Update");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Update flight");
		stage.setOnHiding(e->{
			view.getParent().setDisable(false);
			try {
				setFlights(null);
			} catch (FileNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		stage.show();
	}
	private void delete(String id) {
		// TODO Auto-generated method stub
		model.remove(myConn, "FLIGHT", "FLIGHT_NUMBER ='"+id+"'");
		try {
			setFlights(null);
		} catch (FileNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void addFlight(ActionEvent event) {
		// TODO Autogenerated
		view.getParent().setDisable(true);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/AddUpdateFlight.fxml"));
		AddUpdateFlightController controller = new AddUpdateFlightController();
		loader.setController(controller);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			controller.setScene("Add");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Add flight");
		stage.setOnHiding(e->{
			view.getParent().setDisable(false);
			try {
				setFlights(null);
			} catch (FileNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		stage.show();
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void back(ActionEvent event) throws IOException {
		// TODO Autogenerated
		Stage stage= (Stage)scroll.getScene().getWindow();
		stage.close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/MainPage.fxml"));
		Parent root = null;
		root = loader.load();
		stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Main");
		stage.show();
	}
}
