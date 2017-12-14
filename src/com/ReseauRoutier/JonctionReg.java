package com.ReseauRoutier;

import java.util.ArrayList;

import com.Regulation.CouleurFeu;
import com.Regulation.Feu;
import com.Regulation.Regulation;


public abstract class JonctionReg extends Jonction{
	
	protected ArrayList<Feu> sesFeux;
	protected Regulation regulation;
	
	public void setRegulation(Regulation regulation) {
		this.regulation = regulation;
	}

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
	
	public boolean setupFeu()
	{
		if(this.sesFeux.size() < this.nbRoutes)
		{
			System.err.println("setupFeu : la jonction "+ getId() +" est pas complete !");
			return false;
		}
		
		this.sesFeux.get(0).setCouleur(CouleurFeu.Vert);
		return true;
	}
}
