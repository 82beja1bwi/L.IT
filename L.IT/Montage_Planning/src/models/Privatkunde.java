package models;

public class Privatkunde extends Kunde{
	private String name; 
	private String vorname;
	
	public Privatkunde(String pEMail, String pName, String pVorname)
	{
		super(pEMail);
		name = pName;
		vorname = pVorname;
	}
	public void setName(String pName)
	{
		name = pName;
	}
	public void setVorname(String pVorname)
	{
		vorname = pVorname;
	}
	public String getName()
	{
		return name;
	}
	public String getVorname()
	{
		return vorname;
	}
}
