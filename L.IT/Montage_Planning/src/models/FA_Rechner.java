package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class FA_Rechner extends Rechner {


	private static final double ARBEITSAUFWAND = 4;

	
		
		
		public FA_Rechner(int pSeriennr, Auftrag pAuftrag, String pStatus, Teile[] pTeile) {
			super(pSeriennr, pAuftrag, pStatus, pTeile);
			// TODO Auto-generated constructor stub
		}
		// Auftragsverteilung.Rechner_seriennummer, Rechner.Status_idStatus, Auftragsverteilung.Datum, Kunde.EMail, Kunde.Name, Kunde.idKundennummer
		
		
//		public Rechner(int pSeriennr, Fertigungsauftrag fa, String pStatus, Teile... pTeile)
//		{
//			setSeriennr(pSeriennr);
//			this.fa = fa;
//			status = pStatus;
//			this.teile = new ArrayList<Teile>(Arrays.asList(pTeile));
//		}
		
//	//hier KOnstruktor für DB Abfrage 
		public FA_Rechner(int pSeriennr, int auftragsNr, String pStatus, Date pBearbeitungsdatum, Date pLieferdatum,
				String pFirmenname, String pPrivatname, int pKundenId,String pEMail, Teile... pTeile) {
			super(pSeriennr, auftragsNr, pStatus, pBearbeitungsdatum, pLieferdatum, pFirmenname, pPrivatname, pKundenId,pEMail, pTeile);
			// TODO Auto-generated constructor stub
		}
//		public FA_Rechner(int pSeriennr, String pStatus,  Date lieferdatum, Date bearbeitungsdatum, String emailKunde, String nameKunde, Integer idKunde) {
//			super(pSeriennr, pStatus);
//			//this.teile = teile;
//			this.lieferdatum = lieferdatum;
//			this.bearbeitungsdatum = bearbeitungsdatum;
//			this.emailKunde = emailKunde;
//			this.nameKunde = nameKunde;
//			this.idKunde = idKunde;
//		}
//	
	public double getArbeitsaufwand() {
		return ARBEITSAUFWAND;
	}


	
}
