package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import models.Auftragsverteilung;
import models.FA_Rechner;
import models.Teile;

public class Datenbank {

	// private static final String DB_CONNECTION =
	// "jdbc:mysql://193.196.143.168:3306/aj9s-montage?serverTimezone=UTC";
	// private static final String DB_USER = "aj9s-montage";
	// private static final String DB_PASSWORD = "TPrKrlU9QsMv6Oh7";

	// NICHT LOESCHEN: Datenbankverbindung GABBY LOKAL
	// private static final String DB_CONNECTION =
	// "jdbc:mysql://localhost:3306/aj9s-montage?serverTimezone=UTC"; //fuer jan
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/aj9s-montage?serverTimezone=UTC";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";

	private Connection connection;

	// Datenbankverbindung herstellen
	private Connection getConnection() {
		Connection dbConnection = null;
		try {
			// Treiber laden
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbConnection;
	}

	// Datenbankverbindung oeffnen
	public void openConnection() {
		this.connection = getConnection();
	}

	// Datenbankverbindung trennen
	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("Fehler beim Schließen der Datenbankverbindung.");
		}
	}

	public void listKunde() throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT Name FROM Kunde");
		while (rs.next()) {
			System.out.println(rs.getString("Name"));
		}
	}

	// Abfrage der Usernamen
	public ArrayList<String> Usernameabfrage() throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT idPersonalnummer FROM Mitarbeiter");
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		int columnCount = rsmd.getColumnCount(); //
		ArrayList<String> list = new ArrayList(columnCount);
		while (rs.next()) {
			int i = 1;
			while (i <= columnCount) {
				list.add(rs.getString(i++));
			}
		}
		return list;
	}

	// // Abfrage der Passwoerter
	// public ArrayList Passwortabfrage() throws SQLException {
	//
	// Statement stmt = connection.createStatement();
	// ResultSet rs = stmt.executeQuery("SELECT name FROM Mitarbeiter"); // Abfrage
	// wie Mitarbeiter heissen
	//
	// ResultSetMetaData rsmd = rs.getMetaData(); // Groesse der Tabelle
	// int columnCount = rsmd.getColumnCount(); //
	// ArrayList<String> list = new ArrayList(columnCount); // Erstellt ArrayList
	//
	// while (rs.next()) {
	// int i = 1;
	// while (i <= columnCount) {
	// list.add(rs.getString(i++));
	// }
	// }
	// return list;
	// }



	// Hier die Abfragen fuer die Rechnerinformationscontroller
	/** Seriennummer */
	// FRAGE: wenn man den Hyperlink Seriennummer in der Rechner Listenansicht
	// anklickt -> dann muss die Seriennummer irgendwo zwischengespeichert werden! --> SIEHE RECHNERINFOCONTROLLER METHODE CLICKRECHNER!
	// Dieser Schritt entfaellt deshalb
	// Die Seriennummer ist fuer viele der folgenden Methoden notwendig

	/** Kunde */ 
	public String getKunde(int serienNummer) throws SQLException {
		Statement stmt = connection.createStatement();
		String query = "SELECT Kunde FROM Auftrag WHERE idAuftragsnummer = (SELECT Auftrag_idAuftragsnummer FROM Rechner_Teile WHERE Bezeichnung = '"
				+ serienNummer + "')";
		ResultSet rs = stmt.executeQuery(query);
		String kunde = null;

		while (rs.next()) {

			kunde = rs.getString("kunde");
		}
		return kunde;
	}

	// /** KundenEMail */
	// public String getKundenMail(int serienNummer) throws SQLException {
	// // Auftrag holen
	// // Kunde des jwlg Auftrags holen
	// // Kundenid + name auf davon holen
	// Statement stmt = connection.createStatement();
	// String query = "SELECT EMail FROM Kunde WHERE idKundennummer = (SELECT
	// Kunde_idKunde FROM Auftrag WHERE idAuftragsnummer = (SELECT
	// Auftrag_idAuftragsnummer FROM Rechner_Teile WHERE Bezeichnung = '"
	// + serienNummer + "'))";
	// ResultSet rs = stmt.executeQuery(query);
	// String kundenEMail = null;
	//
	// while (rs.next()) {
	//
	// kundenEMail = rs.getString("EMail");
	// }
	// return kundenEMail;
	// }
	/** Rechnerinfo */
	// Konstutor Rechner: (Integer seriennr, String status, Date lieferdatum, Date
	// bearbeitungsdatum, String emailKunde, String nameKunde, Integer idKunde)
	// Teile erstmal vorweg..: Teile (...SELECT id_rechnerteile WHERE REchner.id_snr
	// = Rechner_Teile.REchner_idsnr)

	// INFO FUER FA_RECHNER ANSICHT
	public FA_Rechner  getFARechnerInfo(int pSeriennr) throws SQLException { //FA_Rechner


//		List<FA_Rechner> rechnerinfo = new ArrayList<>();
		FA_Rechner fr = null;

		Statement stmt = connection.createStatement();
		String query = "SELECT Rechner.Auftrag_idAuftragsnummer, Auftrag.Kunde_idKunde, Status.Bezeichnung, "
								+ "Teile.Bezeichnung, Auftragsverteilung.Datum, Kunde.Firmenname, Kunde.Name, Kunde.EMail"
					+ "FROM Auftragsverteilung, Auftrag, Status, Teile, Rechner, Kunde "
					+ "WHERE (Rechner.idSeriennummer = '"+pSeriennr+"') "
							+ "AND (Auftrag.idAuftragsnummer = (SELECT Rechner.Auftrag_idAuftragsnummer FROM Rechner WHERE Rechner.idSeriennummer = '"+pSeriennr+"') ) "
							+ "AND (Status.idStatus = (SELECT Rechner.Status_idStatus FROM Rechner WHERE  Rechner.idSeriennummer = '"+pSeriennr+"')) "
							+ "AND (Teile.idTeilenummer = (SELECT Rechner_Teile.Teile_idTeilenummer FROM Rechner_Teile WHERE Rechner_Teile.Rechner_idSeriennummer = '"+pSeriennr+"')) "
							+ "AND (Auftragsverteilung.Rechner_seriennummer = '"+pSeriennr+"') "
							+ "AND (Kunde.idKundennummer = (SELECT Auftrag.Kunde_idKunde FROM Auftrag WHERE Auftrag.idAuftragsnummer = (SELECT Rechner.Auftrag_idAuftragsnummer FROM Rechner WHERE Rechner.idSeriennummer = '\"+pSeriennr+\"')))";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {

			int pAuftragsNr = Integer.parseInt(rs.getString("Rechner.Auftrag_idAuftragsnummer")); //WHERE Rechner.idSeriennummer = '"+pSeriennr+"'
			int pKundenId = Integer.parseInt(rs.getString("Auftrag.Kunde_idKunde")); // WHERE Auftrag.idAuftragsnummer = (SELECT Rechner.Auftrag_idAuftragsnummer FROM Rechner WHERE Rechner.idSeriennummer = '"+pSeriennr+"') 
			String pStatus = rs.getString("Status.Bezeichnung"); //WHERE Status.idStatus = (SELECT Rechner.Status_idStatus FROM Rechner WHERE  Rechner.idSeriennummer = '"+pSeriennr+"')
			Teile pTeile = (Teile) rs.getObject("Teile.Bezeichnung");	// WHERE Teile.idTeilenummer = (SELECT Rechner_Teile.Teile_idTeilenummer FROM Rechner_Teile WHERE Rechner_Teile.Rechner_idSeriennummer = '"++pSeriennr"')
			Date pLieferdatum = rs.getDate("Auftragsverteilung.Datum");	//GIBT ES NOCH NICHT //WHERE Auftragsverteilung.Rechner_seriennummer = '"+pSeriennr+"'
			Date pBearbeitungsdatum = rs.getDate("Auftragsverteilung.Datum");	//WHERE Auftragsverteilung.Rechner_seriennummer = '"+pSeriennr+"'
			String pFirmenname = null;
			String pPrivatname = null;
			String pEMail = rs.getString("Kunde.EMail");
			if( rs.getString("Kunde.Firmenname") != null) {		//WHERE Kunde.idKundennummer = (SELECT Auftrag.Kunde_idKunde FROM Auftrag WHERE Auftrag.idAuftragsnummer = (SELECT Rechner.Auftrag_idAuftragsnummer FROM Rechner WHERE Rechner.idSeriennummer = '"+pSeriennr+"'))
				pFirmenname = rs.getString("Kunde.Firmenname");

				}else {
					pPrivatname = rs.getString("Kunde.Name");	//WHERE Kunde.idKundennummer = (SELECT Auftrag.Kunde_idKunde FROM Auftrag WHERE Auftrag.idAuftragsnummer = (SELECT Rechner.Auftrag_idAuftragsnummer FROM Rechner WHERE Rechner.idSeriennummer = '"+pSeriennr+"'))
				}
//			Geschaeftskunde gk;
//			Privatkunde pk;
			fr = new FA_Rechner(pSeriennr, pAuftragsNr, pStatus, pBearbeitungsdatum, pLieferdatum, pFirmenname, pPrivatname, pKundenId, pEMail, pTeile);

		}
		return fr;
	}

	/** Bearbeitungsdatum */

	/** Lieferdatum */

	/** Einzelteile */
	public List<String> getRechnerEinzelteile(int serienNummer) throws SQLException {
		Statement stmt = connection.createStatement();
		String query = "SELECT Bezeichnung FROM Teile WHERE idTeilenummer = (SELECT Teile_idTeilenummer FROM Rechner_Teile WHERE Rechner_idSeriennummer = '"
				+ serienNummer + "')";
		ResultSet rs = stmt.executeQuery(query);

		List<String> rechnerEinzelteile = new ArrayList<>();

		while (rs.next()) {

			rechnerEinzelteile.add(rs.getString("Bezeichnung"));
		}
		return rechnerEinzelteile;
	}

	/** Einzelteil Suche im Servicauftrag */
	public int getEinzelteilLagerbestand(String eingabe) throws SQLException {
		// Hier muss noch gecheckt werden, dass die eingabe ueberhaupt sinnvoll ist.
		Statement stmt = connection.createStatement();
		String query = "SELECT Lagerbestand FROM Rechner_Teile WHERE Bezeichnung = '" + eingabe + "'";
		ResultSet rs = stmt.executeQuery(query);
		int lagerbestand = 0;

		while (rs.next()) {
			lagerbestand = rs.getInt("Lagerbestand");
		}
		return lagerbestand;
	}

}
