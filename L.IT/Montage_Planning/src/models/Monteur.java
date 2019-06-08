package models;

//<<<<<<< Updated upstream
import java.util.LinkedList;
import java.util.Deque;

public abstract class Monteur extends Mitarbeiter{
	private boolean verfuegbar;
	private Deque<Rechner> queue = new LinkedList<Rechner>();

	public Monteur(int pPersonalnr, String pName, String pVorname, double pUrlaubstage, int pKrankheitstage)
	{
		super(pPersonalnr, pName, pVorname, pUrlaubstage, pKrankheitstage);
	}

	public Monteur(int personalnr, String name) {
		super(personalnr, name);
		// TODO Auto-generated constructor stub
	}
	
	
	
}




