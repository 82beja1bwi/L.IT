package models;

public class Serviceauftrag extends Auftrag{

	public Serviceauftrag(int pAuftragsnr, String pStatus, Kunde pKunde, boolean pZugewiesen, Rechner pRechner)
	{
		super(pAuftragsnr, pStatus, pKunde, pZugewiesen, pRechner);
	}
}
