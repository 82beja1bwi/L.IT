package models;

public class SA_Rechner extends Rechner {
	
	private double arbeitsaufwand;
	private boolean getestet;
	private boolean kundenverschuldet;
	private boolean prozessor_kaputt;
	private boolean grafikkarte_kaputt;
	private boolean festplatte_kaputt;
	private boolean dvd_Laufwerk_kaputt;
	private boolean neues_Betriebssystem;

	public SA_Rechner(int pSeriennr, Auftrag pAuftrag, String pStatus, Teile pTeile, double pArbeitsaufwand) {
		super(pSeriennr, pAuftrag, pStatus, pTeile);
		this.arbeitsaufwand = pArbeitsaufwand;
	}

	public void setArbeitsaufwand(double pArbeitsaufwand) {
		arbeitsaufwand = pArbeitsaufwand;
	}

	public void setGetestet(boolean pGetestet) {
		getestet = pGetestet;
	}

	public void setKundenverschuldet(boolean pKundenverschuldet) {
		kundenverschuldet = pKundenverschuldet;
	}

	public void setProzessor_kaputt(boolean pProzessor) {
		prozessor_kaputt = pProzessor;
	}

	public void setGrafikkarte_kaputt(boolean pGrafikkarte) {
		grafikkarte_kaputt = pGrafikkarte;
	}

	public void setFestplatte_kaputt(boolean pFestplatte) {
		festplatte_kaputt = pFestplatte;
	}

	public void setDvd_Laufwerk_kaputt(boolean pLaufwerk) {
		dvd_Laufwerk_kaputt = pLaufwerk;
	}

	public void setNeues_Betriebssystem(boolean pBetriebssystem) {
		neues_Betriebssystem = pBetriebssystem;
	}

	public double getArbeitsaufwand() {
		return arbeitsaufwand;
	}

	public boolean getGetestet() {
		return getestet;
	}

	public boolean getKundenverschuldet() {
		return kundenverschuldet;
	}

	public boolean getProzessor() {
		return prozessor_kaputt;
	}

	public boolean getGrafikkarte() {
		return grafikkarte_kaputt;
	}

	public boolean getFestplatte() {
		return festplatte_kaputt;
	}

	public boolean getLaufwerk() {
		return dvd_Laufwerk_kaputt;
	}

	public boolean getBetriebssystem() {
		return neues_Betriebssystem;
	}
}
