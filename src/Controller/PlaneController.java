package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PlaneController {
	@FXML
	private ScrollPane scroll;
	@FXML
	private FlowPane view;
	private Model model = new Model();
	private Connection myConn = model.connect("sa", "bob1234");
	public void setScene() throws SQLException, FileNotFoundException {
		ResultSet q = model.getData(myConn,"*","AIRPLANE","");
		view.getChildren().clear();
		while(q.next()) {
			FlowPane main = new FlowPane();
			main.setPrefHeight(131);
			main.setPrefWidth(737);
			main.setLayoutX(46);
			main.setLayoutY(796);
			main.setAlignment(Pos.TOP_CENTER);
			
			Image img = new Image(new FileInputStream("images/flightHistory.png"));
			ImageView image = new ImageView();
			image.setImage(img);
			image.setFitHeight(96);
			image.setFitWidth(96);
			
			FlowPane inner = new FlowPane();
			inner.setPrefHeight(121);
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
			IdText.setText(q.getString("AIRPLANE_ID").trim());
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
			NameText.setText(q.getString("AIRPLANE_NAME").trim());
			NameText.setLayoutX(70);
			NameText.setLayoutY(10);
			NameText.setDisable(true);

			Label TypeLabel = new Label("Type:");
			TypeLabel.setLayoutX(219);
			TypeLabel.setLayoutY(12);
			TypeLabel.setPrefHeight(22);
			TypeLabel.setPrefWidth(90);
			TypeLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			TypeLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField TypeText = new TextField();
			TypeText.setText(q.getString("TYPE").trim());
			TypeText.setLayoutX(70);
			TypeText.setLayoutY(10);
			TypeText.setDisable(true);

			Label SeatsLabel = new Label("Seats:");
			SeatsLabel.setLayoutX(10);
			SeatsLabel.setLayoutY(37);
			SeatsLabel.setPrefHeight(22);
			SeatsLabel.setPrefWidth(90);
			SeatsLabel.setFont(Font.font("Georgia",FontWeight.NORMAL,FontPosture.REGULAR,18));
			SeatsLabel.textFillProperty().set(Color.rgb(8, 177,255,1));
			TextField SeatsText = new TextField();
			SeatsText.setText(q.getString("NUMBER_OF_SEATS").trim());
			SeatsText.setLayoutX(70);
			SeatsText.setLayoutY(10);
			SeatsText.setDisable(true);

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
			Delete.setId(q.getString("AIRPLANE_ID").trim());
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
			Update.setId(q.getString("AIRPLANE_ID").trim());
			Update.setOnAction(e->{
				update(Update.getId());
			});

			Button History = new Button("FlightHistory");
			History.setPrefHeight(40);
			History.setPrefWidth(155);
			History.setLayoutX(112);
			History.setLayoutY(130);
			History.setStyle("-fx-background-color:lightblue;-fx-mnemonicParsing:false;");
			Image himg = new Image(new FileInputStream("images/flightHistory.png"));
			ImageView himage = new ImageView();
			himage.setFitHeight(26);
			himage.setFitWidth(26);
			himage.setImage(himg);
			History.setGraphic(himage);
			History.setId(q.getString("AIRPLANE_ID").trim());
			History.setOnAction(e->{
				showFlightHistory(History.getId());
			});
			
			inner.getChildren().addAll(IdLabel,IdText,NameLabel,NameText,TypeLabel,TypeText,SeatsLabel,SeatsText,Delete,Update,History);
			view.getChildren().add(main);
		}
	}
	
	private void showFlightHistory(String id) {
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
			controller.setScene("AirPlane");//to let the controller know to take the flight of a plane
		} catch (SQLException e1) {			//since this fxml page is common between employees and planes
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/AddUpdatePlane.fxml"));
		AddUpdatePlaneController controller = new AddUpdatePlaneController();
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
		stage.setTitle("Update plane");
		stage.setOnHiding(e->{
			view.getParent().setDisable(false);
			try {
				setScene();
			} catch (FileNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		stage.show();
	}
	private void delete(String id) {
		// TODO Auto-generated method stub
		model.remove(myConn, "AIRPLANE", "AIRPLANE_ID ='"+id+"'");
		try {
			setScene();
		} catch (FileNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void addPlane(ActionEvent event) {
		// TODO Autogenerated
		view.getParent().setDisable(true);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/References/AddUpdatePlane.fxml"));
		AddUpdatePlaneController controller = new AddUpdatePlaneController();
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
		stage.setTitle("Add plane");
		stage.setOnHiding(e->{
			view.getParent().setDisable(false);
			try {
				setScene();
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
		Stage stage= (Stage)scroll.getScene().getWindow();//close current window
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
