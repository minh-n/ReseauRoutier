package com.ReseauRoutier;

import java.util.ArrayList;

import com.Regulation.CouleurFeu;
import com.Regulation.Feu;

public abstract class JonctionReg extends Jonction{
	
	protected ArrayList<Feu> sesFeux;

	public JonctionReg(int nbRoutes)
	{
		super();
		this.nbRoutes = nbRoutes;
		this.sesFeux = new ArrayList<Feu>();
	}
	
	public boolean ajouterFeu(Feu feu)
	{
		if(this.sesFeux.size() < this.nbRoutes)
		{
			this.sesFeux.add(feu);
			return true;
		}
		
		System.err.println("ajouterFeu : Trop de feu !");
		return true;
	}
	
	public boolean retirerFeu(Feu feu)
	{
		return this.sesFeux.remove(feu);
	}

	public ArrayList<Feu> getSesFeux() {
		return sesFeux;
	}
	
	/**
	 * Met le premier feu d'un carrefour au vert pour permettre la circulation.
	 */
	public void setupFeu()
	{		
		
		/**
		 * TODO bug
		 */
		int i = 1;
		while(i < sesFeux.size())
		{
			sesFeux.get(i).setCouleur(CouleurFeu.Vert);
			i++;
		}	}
}
