package models;

public abstract class Kunde {
	private String eMail;
	
	public Kunde(String pEMail)
	{
		eMail = pEMail;
	}
	public void setEMail(String pEMail)
	{
		eMail = pEMail;
	}
	public String getEMail()
	{
		return eMail;
	}
	
	
}