package models;

public class Geschaeftskunde extends Kunde{
	private String firmenname;
	
	public Geschaeftskunde(String pEMail, String pFirmenname)
	{
		super(pEMail);
		firmenname = pFirmenname;
	}
}
