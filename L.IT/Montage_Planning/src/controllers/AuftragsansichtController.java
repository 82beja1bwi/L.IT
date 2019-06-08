package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class AuftragsansichtController {
	
	//Grundfunktionen
	@FXML private Button btnLogout;
	@FXML private Label lblName;
	@FXML private Tab tabWoche;
	@FXML private Tab tabListe;
	
	//Wochenansicht:
	@FXML private ComboBox<String> comboBox_AW_Wochenansicht;
	@FXML private TableColumn col_AW_Mitarbeit;
	@FXML private TableColumn col_AW_Montag;
	@FXML private TableColumn col_AW_Dienstag;
	@FXML private TableColumn col_AW_Mittwoch;
	@FXML private TableColumn col_AW_Donnerstag;
	@FXML private TableColumn col_AW_Freitag;
	
	//Listenansicht:
	@FXML private ComboBox<String> comboBox_AL_filter;
	@FXML private TextField txt_AL_suche;
	@FXML private TableColumn col_AL_Auftragsnummer;
	@FXML private TableColumn col_AL_Status;
	@FXML private TableColumn col_AL_Anzahl;
	@FXML private TableColumn col_AL_Lieferdatum;
	
	@FXML
	public void filter() {
		//Liste mit Filtern (erstellen oder laden)
		//Liste mit Filtern als String in ComboBox laden
		//Wird eins angeklickt, dann entsprechends filtern
	}

	
}
