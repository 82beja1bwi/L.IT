package controllers;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.Label;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
 @FXML
 private Label lblMontage;
 
 @FXML
 private TextField txtUsername;
 
 @FXML
 private TextField txtPassword;
 
 public void Login (ActionEvent event) throws IOException {
	 
	//if(txtUsername.getText().equals("u") && txtPassword.getText().equals("p")) {
		
	//	Stage primaryStage = new Stage();
	//	Parent root = FXMLLoader.load(getClass().getResource("/application/Wochenansicht.fxml"));
	//	Scene scene = new Scene(root,400,400);
	//	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	//	primaryStage.setScene(scene);
	//	primaryStage.show();
	//} 
 }
}
