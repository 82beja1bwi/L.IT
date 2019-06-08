package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class SA_RechnerinfoController implements EventHandler, Initializable {
	/** Rechnerinfo */
	@FXML
	private Label lbl_SAI_status;
	@FXML
	private Label lbl_SAI_lieferdatum;
	@FXML
	private Label lbl_SAI_bearbeitungsdatum;
	@FXML
	private Label lbl_SAI_kunde;
	@FXML
	private Label lbl_SAI_kundenNr;
	@FXML
	private Label lbl_SAI_kundenEMail;
	@FXML
	private Label lbl_SAI_Seriennummer;

	/** Problemdokumentation */
	@FXML
	private ComboBox<String> comboBox_SAI_Bearbeitungsstatus;
	@FXML
	private Label lbl_SAI_SuchStatus;

	@FXML
	private TextField txt_SAI_Einzelteilsuche;

	@FXML
	private RadioButton rbtn_SAI_Hardware;
	@FXML
	private RadioButton rbtn_SAI_Software;
	@FXML
	private RadioButton rbtn_SAI_Kunde;
	@FXML
	private Button btn_SAI_pdf;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		lbl_SAI_status = new Label();
		 lbl_SAI_lieferdatum = new Label();

lbl_SAI_bearbeitungsdatum = new Label();
		lbl_SAI_kunde = new Label();
		 lbl_SAI_kundenNr = new Label();
		lbl_SAI_kundenEMail = new Label();
		lbl_SAI_Seriennummer = new Label();

		/** Problemdokumentation */
		comboBox_SAI_Bearbeitungsstatus = new ComboBox<String>();
		lbl_SAI_SuchStatus = new Label();

		txt_SAI_Einzelteilsuche = new TextField();

		rbtn_SAI_Hardware= new RadioButton();
		rbtn_SAI_Software = new RadioButton();
		rbtn_SAI_Kunde = new RadioButton();
		btn_SAI_pdf = new Button();
		
		String stsBearb = "in Bearbeitung";
		String stsFertig = "erledigt";
		String stsImLager = "im Lager";


		ObservableList<String> status = FXCollections.observableArrayList(stsBearb, stsFertig, stsImLager);
		comboBox_SAI_Bearbeitungsstatus.setItems(status);
		
	}

	@Override
	public void handle(Event arg0) {
		// TODO Auto-generated method stub

	}


}
