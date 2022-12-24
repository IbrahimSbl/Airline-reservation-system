package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

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
import javafx.stage.StageStyle;

public class EmployeeController {
	@FXML
	private ScrollPane scroll;
	@FXML
	private FlowPane view;
	private Model model = new Model();
	Connection myConn = model.connect("sa", "bob1234");
	public void setScene() throws FileNotFoundException, SQLException {
		Image img = new Image(new FileInputStream("images/sea.jpg"));
        BackgroundImage bImg = new BackgroundImage(img,
                                                   BackgroundRepeat.NO_REPEAT,
                                                   BackgroundRepeat.NO_REPEAT,
                                                   BackgroundPosition.DEFAULT,
                                                   new BackgroundSize(1,1,true,true,false,false));
        Background bGround = new Background(bImg);
        scroll.setBackground(bGround);
        setEmployees();
	}
	public void setEmployees() throws FileNotFoundException, SQLException {
		ResultSet q = model.getData(myConn,"*","EMPLOYEE","");
		view.getChildren().clear();
		while(q.next()) {
			FlowPane main = new FlowPane();
			main.setPrefHeight(204);
			main.setPrefWidth(737);
			
			Image img = new Image(new FileInputStream("images/contact.png"));
			ImageView image = new ImageView();
			image.setImage(img);
			image.setFitHeight(225);
			image.setFitWidth(248);
			
			FlowPane inner = new FlowPane();
			inner.setPrefHeight(204);
			inner.setPrefWidth(485);
			inner.setHgap(2);
			inner.setVgap(5);
			//add the image view and the inner flow pane to the main flow pane
			main.getChildren().addAll(image,inner);
			//fill the inner flow pane with text fields and buttons
			Label IdLabel = new Label("ID:");
			IdLabel.setPrefHeight(22);
			IdLabel.setPrefWidth(90);
			IdLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			IdLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField IdText = new TextField();
			IdText.setText(q.getString("EMPLOYEE_ID").trim());
			IdText.setLayoutX(70);
			IdText.setLayoutY(10);
			IdText.setDisable(true);
			
			Label NameLabel = new Label("Name:");
			NameLabel.setLayoutX(10);
			NameLabel.setLayoutY(12);
			NameLabel.setPrefHeight(22);
			NameLabel.setPrefWidth(90);
			NameLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			NameLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField NameText = new TextField();
			NameText.setText(q.getString("EMPLOYEE_NAME").trim());
			NameText.setLayoutX(70);
			NameText.setLayoutY(10);
			NameText.setDisable(true);

			Label JobLabel = new Label("Job:");
			JobLabel.setLayoutX(219);
			JobLabel.setLayoutY(12);
			JobLabel.setPrefHeight(22);
			JobLabel.setPrefWidth(90);
			JobLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			JobLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField JobText = new TextField();
			JobText.setText(q.getString("JOB_TITLE").trim());
			JobText.setLayoutX(70);
			JobText.setLayoutY(10);
			JobText.setDisable(true);

			Label SalaryLabel = new Label("Salary:");
			SalaryLabel.setLayoutX(219);
			SalaryLabel.setLayoutY(37);
			SalaryLabel.setPrefHeight(22);
			SalaryLabel.setPrefWidth(90);
			SalaryLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			SalaryLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField SalaryText = new TextField();
			SalaryText.setText(q.getString("SALARY").trim());
			SalaryText.setLayoutX(70);
			SalaryText.setLayoutY(10);
			SalaryText.setDisable(true);

			Label BirthdayLabel = new Label("Birthday:");
			BirthdayLabel.setLayoutX(219);
			BirthdayLabel.setLayoutY(37);
			BirthdayLabel.setPrefHeight(22);
			BirthdayLabel.setPrefWidth(90);
			BirthdayLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			BirthdayLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField BirthdayText = new TextField();
			BirthdayText.setText(q.getString("BIRTHDAY").trim());
			BirthdayText.setLayoutX(70);
			BirthdayText.setLayoutY(10);
			BirthdayText.setDisable(true);

			Label EmailLabel = new Label("Email:");
			EmailLabel.setLayoutX(10);
			EmailLabel.setLayoutY(72);
			EmailLabel.setPrefHeight(22);
			EmailLabel.setPrefWidth(90);
			EmailLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			EmailLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField EmailText = new TextField();
			EmailText.setText(q.getString("EMAIL").trim());
			EmailText.setLayoutX(102);
			EmailText.setLayoutY(70);
			EmailText.setDisable(true);

			Label AddressLabel = new Label("Address:");
			AddressLabel.setLayoutX(253);
			AddressLabel.setLayoutY(72);
			AddressLabel.setPrefHeight(22);
			AddressLabel.setPrefWidth(90);
			AddressLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			AddressLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField AddressText = new TextField();
			AddressText.setText(q.getString("ADDRESS").trim());
			AddressText.setLayoutX(345);
			AddressText.setLayoutY(70);
			AddressText.setDisable(true);

			Label PhoneLabel = new Label("Phone:");
			PhoneLabel.setLayoutX(10);
			PhoneLabel.setLayoutY(102);
			PhoneLabel.setPrefHeight(22);
			PhoneLabel.setPrefWidth(90);
			PhoneLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			PhoneLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField PhoneText = new TextField();
			PhoneText.setText(q.getString("PHONE_NUMBER").trim());
			PhoneText.setLayoutX(102);
			PhoneText.setLayoutY(100);
			PhoneText.setDisable(true);
			
			Button Delete = new Button("Delete");
			Delete.setPrefHeight(40);
			Delete.setPrefWidth(155);
			Delete.setStyle("-fx-background-color:lightblue;-fx-mnemonicParsing:false;");
			Image dimg = new Image(new FileInputStream("images/delete.png"));
			ImageView dimage = new ImageView();
			dimage.setFitHeight(26);
			dimage.setFitWidth(26);
			dimage.setImage(dimg);
			Delete.setGraphic(dimage);
			Delete.setId(q.getString("EMPLOYEE_ID").trim());
			Delete.setOnAction(e->{
				delete(Delete.getId());
			});

			Button Update = new Button("Update");
			Update.setPrefHeight(40);
			Update.setPrefWidth(155);
			Update.setLayoutX(10);
			Update.setLayoutY(130);
			Update.setStyle("-fx-background-color:lightblue;-fx-mnemonicParsing:false;");
			Image uimg = new Image(new FileInputStream("images/update.png"));
			ImageView uimage = new ImageView();
			uimage.setFitHeight(26);
			uimage.setFitWidth(26);
			uimage.setImage(uimg);
			Update.setGraphic(uimage);
			Update.setId(q.getString("EMPLOYEE_ID").trim());
			Update.setOnAction(e->{
				update(Update.getId());
			});

			Button History = new Button("FlightHistory");
			History.setPrefHeight(40);
			History.setPrefWidth(155);
			History.setLayoutX(112);
			History.setLayoutY(130);
			History.setStyle("-fx-background-color:lightblue;-fx-mnemonicParsing:false;");
			Image himg = new Image(new FileInputStream("images/history.png"));
			ImageView himage = new ImageView();
			himage.setFitHeight(26);
			himage.setFitWidth(26);
			himage.setImage(himg);
			History.setGraphic(himage);
			History.setId(q.getString("EMPLOYEE_ID").trim());
			History.setOnAction(e->{
				showFlightHistory(History.getId(),JobText.getText());
			});
			
			inner.getChildren().addAll(IdLabel,IdText,NameLabel,NameText,JobLabel,JobText,SalaryLabel,SalaryText,BirthdayLabel,BirthdayText,EmailLabel,EmailText,AddressLabel,AddressText,PhoneLabel,PhoneText,Delete,Update,History);
			view.getChildren().add(main);
		}
		
		
	}
	public void delete(String id) {
		model.remove(myConn, "EMPLOYEE", "EMPLOYEE_ID ='"+id+"'");
		try {
			setEmployees();
		} catch (FileNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(String id) {
		
		view.getParent().setDisable(true);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/AddUpdatePage.fxml"));
		AddUpdateController controller = new AddUpdateController();
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
		stage.setTitle("Update employee");
		stage.setOnHiding(e->{
			view.getParent().setDisable(false);
			try {
				setEmployees();
			} catch (FileNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		stage.show();
	}
	public void showFlightHistory(String id,String role) {
		view.getParent().setDisable(true);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/FlightHistoryPage.fxml"));
		FlightHistoryController controller = new FlightHistoryController();
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
			controller.setScene(role);
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
	// Event Listener on Button.onAction
	@FXML
	public void addEmployee(ActionEvent event) {
		// TODO Autogenerated
		Stage stage;
		view.getParent().setDisable(true);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/AddUpdatePage.fxml"));
		AddUpdateController controller = new AddUpdateController();
		loader.setController(controller);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			controller.setScene("add");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Add employee");
		stage.setOnHiding(e->{
			view.getParent().setDisable(false);
			try {
				setEmployees();
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
