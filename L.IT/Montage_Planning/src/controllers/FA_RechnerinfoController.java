package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import application.Datenbank;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import models.FA_Rechner;

public class FA_RechnerinfoController implements EventHandler, Initializable {
	/** Rechnerinfo */
	@FXML
	private Label lbl_FAI_status;
	@FXML
	private Label lbl_FAI_lieferdatum;
	@FXML
	private Label lbl_FAI_bearbeitungsdatum;
	@FXML
	private Label lbl_FAI_kunde;
	@FXML
	private Label lbl_FAI_kundenNr;
	@FXML
	private Label lbl_FAI_kundenEMail;
	@FXML
	private Label lbl_FAI_Seriennummer;
	@FXML
	private ComboBox<String> comboBox_FAI_Status = null;
	/** Einzelteiltabelle */
	@FXML
	private TableColumn<String,String> TableColumn_FAI_einzelteile;

	private Datenbank db = new Datenbank();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		db.openConnection();
		lbl_FAI_status = new Label();
		lbl_FAI_lieferdatum = new Label();
		lbl_FAI_bearbeitungsdatum = new Label();
		lbl_FAI_kunde = new Label();
		lbl_FAI_kundenNr = new Label();
		lbl_FAI_kundenEMail = new Label();
		lbl_FAI_Seriennummer = new Label();
		TableColumn_FAI_einzelteile = new TableColumn<String,String>();
		String stsBearb = "in Bearbeitung";
		String stsFertig = "erledigt";
		String stsImLager = "im Lager";

		ObservableList<String> status = FXCollections.observableArrayList(stsBearb, stsFertig, stsImLager);

		comboBox_FAI_Status.setItems(status);

		FA_RechnerInfo_fuellen();

	}

	/**
	 * Labels der FA_RECHNERINFO Ansicht werden befüllt mit Werten aus der Datenbank
	 */
	public void FA_RechnerInfo_fuellen() {
		int serienNr = 0;

		FA_Rechner fr;
		try {
			serienNr = Integer.parseInt(lbl_FAI_Seriennummer.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			fr = db.getFARechnerInfo(serienNr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// hier die einzelnen Labels befüllen
		if (fr.getFirmenname() != null) {
			lbl_FAI_kunde.setText(fr.getFirmenname());
		} else {
			lbl_FAI_kunde.setText(fr.getPrivatName());
		}
		String bearbeitungsdatum = fr.getBearbeitungsdatum().toString();
		lbl_FAI_bearbeitungsdatum.setText(bearbeitungsdatum);
		lbl_FAI_kundenEMail.setText(fr.geteMail());
		lbl_FAI_kundenNr.setText(fr.getKundenId().toString());
		lbl_FAI_lieferdatum.setText();// gibt es nochnicht
		lbl_FAI_Seriennummer.setText(fr.getSeriennr().toString());
		lbl_FAI_status.setText(fr.getStatus());
		//Einzelteile:
		TableColumn_FAI_einzelteile.
	}

	@Override
	public void handle(Event arg0) {
		// TODO Auto-generated method stub

	}
}
