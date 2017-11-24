package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.HashMap;

public class JonctionSimple extends Jonction{
	
	public JonctionSimple()
	{
		super();
	}
	
	
	public JonctionSimple(ArrayList<Jonction> voisins)
	{
		super(voisins);
	}
	
	public void addVoisin(Jonction voisin)
	{
		super.addVoisin(voisin);	}

}
