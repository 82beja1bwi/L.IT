package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import application.Datenbank;
import application.Datenbank_Gabby;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import models.Auftragsverteilung;

public class RechneransichtController implements Initializable {

	// Grundfunktionen, Menüleiste
	@FXML
	private Button btnLogout;
	@FXML
	private Tab tabWoche;
	@FXML
	private Tab tabListe;
	@FXML
	private Label lblName;

	// Listenansicht
	@FXML
	private ComboBox<String> comboBox_RL_filter;
	@FXML
	private TextField txt_RL_sucheingabe;
	@FXML
	private TableView<Auftragsverteilung> tableRechnerListe;
	@FXML
	private TableColumn<Auftragsverteilung, Integer> col_RL_serienNr;
	@FXML
	private TableColumn<Auftragsverteilung, String> col_RL_status;
	@FXML
	private TableColumn<Auftragsverteilung, Date> col_RL_bearbeitungsdatum;
	@FXML
	private TableColumn<Auftragsverteilung, Date> col_RL_lieferdatum;

	// Wochenansicht
	@FXML
	private ComboBox<String> comboBox_RW_Wochenansicht;
	@FXML
	private TableView<Auftragsverteilung> tableRechnerWoche;
	@FXML
	private TableColumn<Auftragsverteilung, Integer> col_RW_Montag;
	@FXML
	private TableColumn<Auftragsverteilung, Integer> col_RW_Dienstag;
	@FXML
	private TableColumn<Auftragsverteilung, Integer> col_RW_Mittwoch;
	@FXML
	private TableColumn<Auftragsverteilung, Integer> col_RW_Donnerstag;
	@FXML
	private TableColumn<Auftragsverteilung, Integer> col_RW_Freitag;

	private Datenbank_Gabby db = new Datenbank_Gabby();

	// Woche1 03.06.2019-07.06.2019
	LocalDate woche1_tag1 = LocalDate.of(2019, Month.JUNE, 03);
	LocalDate woche1_tag2 = LocalDate.of(2019, Month.JUNE, 04);
	LocalDate woche1_tag3 = LocalDate.of(2019, Month.JUNE, 05);
	LocalDate woche1_tag4 = LocalDate.of(2019, Month.JUNE, 06);
	LocalDate woche1_tag5 = LocalDate.of(2019, Month.JUNE, 07);

	// Woche2 10.06.2019-14.06.2019
	LocalDate woche2_tag1 = LocalDate.of(2019, Month.JUNE, 10);
	LocalDate woche2_tag2 = LocalDate.of(2019, Month.JUNE, 11);
	LocalDate woche2_tag3 = LocalDate.of(2019, Month.JUNE, 12);
	LocalDate woche2_tag4 = LocalDate.of(2019, Month.JUNE, 13);
	LocalDate woche2_tag5 = LocalDate.of(2019, Month.JUNE, 14);

	// Woche3 17.06.2019-21.06.2019
	LocalDate woche3_tag1 = LocalDate.of(2019, Month.JUNE, 17);
	LocalDate woche3_tag2 = LocalDate.of(2019, Month.JUNE, 18);
	LocalDate woche3_tag3 = LocalDate.of(2019, Month.JUNE, 19);
	LocalDate woche3_tag4 = LocalDate.of(2019, Month.JUNE, 20);
	LocalDate woche3_tag5 = LocalDate.of(2019, Month.JUNE, 21);

	String woche1, woche2, woche3;

	ObservableList<String> options;
	DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		db.openConnection();

		// ComboBox Wochenangaben als String speichern
		woche1 = woche1_tag1.format(f) + "-" + woche1_tag5.format(f);
		woche2 = woche2_tag1.format(f) + "-" + woche2_tag5.format(f);
		woche3 = woche3_tag1.format(f) + "-" + woche3_tag5.format(f);

		// ComboBox Wochenangaben Liste
		options = FXCollections.observableArrayList(woche1, woche2, woche3);

		// ComboBox befüllen
		comboBox_RW_Wochenansicht.setItems(options);
		comboBox_RW_Wochenansicht.setPromptText(woche1);

		// Wochen- und Listenansicht Inhalte
		wochenansichtFuellen();
		listenansichtFuellen();

		// db.closeConnection();
	}

	// Wochenansicht Tabelle befüllen
	public void wochenansichtFuellen() {

		ObservableList<Auftragsverteilung> rechnerWochenansichtTabelle = FXCollections.observableArrayList();

		// ComboBox Listener
		comboBox_RW_Wochenansicht.getSelectionModel().selectedItemProperty().addListener((options) -> {
			tableRechnerWoche.getItems().clear();
			String wochenAuswahl = comboBox_RW_Wochenansicht.getSelectionModel().selectedItemProperty().getValue();	// DD.MM.YYYY-DD.MM.YYYY

			// String spliten 
			String startdatum = wochenAuswahl.substring(0, 10);
			String enddatum = wochenAuswahl.substring(11, 21);
			
			
			try {
				rechnerWochenansichtTabelle.addAll(db.getRechnerAusAuftragsverteilungWoche(startdatum, enddatum));
				System.out.println(rechnerWochenansichtTabelle.toString());
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e);
			}
			
			int size = rechnerWochenansichtTabelle.size();
			System.out.println(size);
			
			
			
			for (int i = 0; i < size; i++) {

				Date d = rechnerWochenansichtTabelle.get(i).getBearbeitungsdatum();
				Integer seriennr = rechnerWochenansichtTabelle.get(i).getSeriennr();		// SERIENNUMER DIE REIN MUSS
				SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week abbreviated
				
				System.out.println(simpleDateformat.format(d));
				System.out.println(seriennr);
				
				
				// switch case ausprobieren, ist vll übersichtlicher 
				
		        if(simpleDateformat.format(d).equals("Montag")) {
		        	System.out.println("Montag");
//		        	rechnerWochenansichtTabelle.stream().filter(p -> p.getSeriennr() == seriennr);
		        	col_RW_Montag.setCellValueFactory(new PropertyValueFactory<>(rechnerWochenansichtTabelle.get(i).getSeriennr().toString()));

		        }
		        if(simpleDateformat.format(d).equals("Dienstag")) {
		        	System.out.println("Dienstag");
//		        	rechnerWochenansichtTabelle.stream().filter(p -> p.getSeriennr() == seriennr);

		        	
		        }
		        if(simpleDateformat.format(d).equals("Mittwoch")) {
		        	System.out.println("Mittwoch");
//		        	rechnerWochenansichtTabelle.stream().filter(p -> p.getSeriennr() == seriennr);
		        	col_RW_Mittwoch.setCellValueFactory(new PropertyValueFactory<>(rechnerWochenansichtTabelle.get(i).getSeriennr().toString()));
		        	
		        }
		        if(simpleDateformat.format(d).equals("Donnerstag")) {
		        	System.out.println("Donnerstag");
//		        	rechnerWochenansichtTabelle.stream().filter(p -> p.getSeriennr() == seriennr);
		        	col_RW_Donnerstag.setCellValueFactory(new PropertyValueFactory<>(rechnerWochenansichtTabelle.get(i).getSeriennr().toString()));
		        }
		        if(simpleDateformat.format(d).equals("Freitag")) {
		        	System.out.println("Freitag");
//		        	rechnerWochenansichtTabelle.stream().filter(p -> p.getSeriennr() == seriennr);
		        	col_RW_Freitag.setCellValueFactory(new PropertyValueFactory<>(rechnerWochenansichtTabelle.get(i).getSeriennr().toString()));
		        	
		        }
			
			} 
			
			
		});
			
		tableRechnerWoche.setItems(rechnerWochenansichtTabelle);
			
	}

	// Listenansicht Tabelle befüllen
	public void listenansichtFuellen() {

		ObservableList<Auftragsverteilung> rechnerListenansichtTabelle = FXCollections.observableArrayList();

		try {
			rechnerListenansichtTabelle.addAll(db.getRechnerAusAuftragsverteilungListe(/* LoginController.user */));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		col_RL_serienNr.setCellValueFactory(new PropertyValueFactory<>("seriennr"));
		col_RL_status.setCellValueFactory(new PropertyValueFactory<>("status"));
		col_RL_bearbeitungsdatum.setCellValueFactory(new PropertyValueFactory<>("bearbeitungsdatum"));
		col_RL_lieferdatum.setCellValueFactory(new PropertyValueFactory<>("lieferdatum"));

		tableRechnerListe.setItems(rechnerListenansichtTabelle);
	}
	

	// Wochenansicht - Klick auf Rechner öffnet Rechnerinfo
	public void clickRechnerWoche(MouseEvent e) {

		if (e.getClickCount() == 2) {
			int seriennrAktuell = tableRechnerWoche.getSelectionModel().getSelectedItem().getSeriennr();

			try {
				int idAuftragsart = db.getRechnerAuftragsart(seriennrAktuell);
				System.out.println(idAuftragsart);
				if (idAuftragsart == 502) {
					new FolgeFenster("/views/FA_Rechnerinfo.fxml");
				} else if (idAuftragsart == 501) {
					new FolgeFenster("/views/SA_Rechnerinfo.fxml");
				} else {
					System.out.println("Keine Info vorhanden");
				}
			} catch (SQLException | IOException e2) {
				e2.printStackTrace();
			}

		}
	}
	

	// Listenansicht - Klick auf Rechner öffnet Rechnerinfo
	public void clickRechnerListe(MouseEvent e) {

		if (e.getClickCount() == 2) {
			int seriennrAktuell = tableRechnerListe.getSelectionModel().getSelectedItem().getSeriennr();

			// idAuftragsart 501 = Serviceauftrag --> die Angabe irgendwo fest speichern?
			// idAuftragsart 502 = Fertigungsauftrag

			try {
				int idAuftragsart = db.getRechnerAuftragsart(seriennrAktuell);
				System.out.println(idAuftragsart);
				if (idAuftragsart == 502) {
					new FolgeFenster("/views/FA_Rechnerinfo.fxml");
				} else if (idAuftragsart == 501) {
					new FolgeFenster("/views/SA_Rechnerinfo.fxml");
				} else {
					System.out.println("Keine Info vorhanden");
				}
			} catch (SQLException | IOException e2) {
				e2.printStackTrace();
			}

		}
	}

}
