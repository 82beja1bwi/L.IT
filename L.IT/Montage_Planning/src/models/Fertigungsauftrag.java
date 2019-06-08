package models;

import java.util.Date;

public class Fertigungsauftrag extends Auftrag{
	
	private Geschaeftskunde gk;
	private Privatkunde pk;

	public Fertigungsauftrag(int pAuftragsnr, String pStatus, Kunde pKunde, boolean pZugewiesen, Rechner pRechner)
	{
		super(pAuftragsnr, pStatus, pKunde, pZugewiesen, pRechner);
	}
	
	//Konstruktor für FA_REchner, passend für DB-Abfrage GESCHAEFSTKUNDE
	public Fertigungsauftrag(Geschaeftskunde gk, Date lieferdatum) {
		super( lieferdatum);
		this.gk = gk;
	}
	//Konstruktor für FA_REchner, passend für DB-Abfrage PRIVATKUNDE
	public Fertigungsauftrag(Privatkunde pk, Date lieferdatum) {
		super( lieferdatum);
		this.pk = pk;
	}
	
	
	
	
}
