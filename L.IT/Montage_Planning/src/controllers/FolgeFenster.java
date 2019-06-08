package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FolgeFenster extends Stage {
	Stage stage;

	public FolgeFenster(String dateipfad) throws IOException {
	      try {
	          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(dateipfad));
	                  Parent root1 = (Parent) fxmlLoader.load();
	                  Stage stage = new Stage();
	                  stage.setScene(new Scene(root1));  
	                  stage.show();
	          } catch(Exception e) {
	             e.printStackTrace();
	            }
	}
}
