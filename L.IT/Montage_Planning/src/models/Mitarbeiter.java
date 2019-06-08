package models;

public class Mitarbeiter {
	private int personalnr;
	private String name;
	private String vorname;
	private double urlaubstage;
	private int krankheitstage;

	public Mitarbeiter(int pPersonalnr, String pName, String pVorname, double pUrlaubstage, int pKrankheitstage) {
		personalnr = pPersonalnr;
		name = pName;
		vorname = pVorname;
		urlaubstage = pUrlaubstage;
		krankheitstage = pKrankheitstage;
	}

	public Mitarbeiter(int personalnr, String name) {
		super();
		this.personalnr = personalnr;
		this.name = name;
	}

	public int getPersonalnr() {
		return personalnr;
	}

	public void setPersonalnr(int personalnr) {
		this.personalnr = personalnr;
	}

	public void setName(String pName) {
		name = pName;
	}

	public void setVorname(String pVorname) {
		vorname = pVorname;
	}

	public void setUrlaubstage(double pUrlaubstage) {
		urlaubstage = pUrlaubstage;
	}

	public void setKrankheitstage(int pKrankheitstage) {
		krankheitstage = pKrankheitstage;
	}

	public int getPerosnalnr() {
		return personalnr;
	}

	public String getName() {
		return name;
	}

	public String getVorname() {
		return vorname;
	}

	public double getUrlaubstage() {
		return urlaubstage;
	}

	public int getKrankheitstage() {
		return krankheitstage;
	}
}
