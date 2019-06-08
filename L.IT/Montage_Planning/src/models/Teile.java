package models;

public class Teile {
	private int teilenr;
	private String kategorie;
	private int lagerbestand;
	private String bezeichnung;
	
	public Teile(int pTeilenr, String pKategorie, int pLagerbestand)
	{
		teilenr = pTeilenr;
		kategorie = pKategorie; 
		lagerbestand = pLagerbestand;
	}
	//Konstruktor für Db Abfrage (FA)
	public Teile(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	

	
	
	public void setKategorie(String pKategorie)
	{
		kategorie = pKategorie;
	}
	

	//Lagerbestand berechnen?
	public int getTeilenr()
	{
		return teilenr;
	}
	public String getKategorie()
	{
		return kategorie;
	}
	public int getLagerbestand()
	{
		return lagerbestand;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	

	

	
}
