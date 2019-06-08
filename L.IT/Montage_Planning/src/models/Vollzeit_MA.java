package models;

public class Vollzeit_MA extends Monteur{
	private static final int arbeitszeit = 8;
	
	public Vollzeit_MA(int pPersonalnr, String pName, String pVorname, double pUrlaubstage, int pKrankheitstage)
	{
		super(pPersonalnr, pName, pVorname, pUrlaubstage, pKrankheitstage);
	}
	public int getArbeitszeit()
	{
		return arbeitszeit;
	}
}
