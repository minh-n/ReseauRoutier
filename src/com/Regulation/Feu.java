package com.Regulation;

import com.ReseauRoutier.SegmentDeRoute;

public abstract class Feu extends Semaphore{
	
	protected CouleurFeu couleur;
	
	public Feu(int sens, SegmentDeRoute route)
	{
		super(sens, route);
		this.couleur = CouleurFeu.Vert;	
	}
		
	public SegmentDeRoute getRoute() {
		return route;
	}

	public void setRoute(SegmentDeRoute route) {
		this.route = route;
	}

	public CouleurFeu getCouleur() {
		return couleur;
	}

	public void setCouleur(CouleurFeu couleur) {
		this.couleur = couleur;
	}


	public int getSens() {
		return sens;
	}

	public void setSens(int sens) {
		this.sens = sens;
	}
	
	

	public abstract void changerCouleur();


}
