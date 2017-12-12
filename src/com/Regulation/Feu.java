package com.Regulation;

import java.util.Observable;

import com.ReseauRoutier.SegmentDeRoute;

public abstract class Feu extends Observable{
	
	private int sens;
	private CouleurFeu couleur;
	private SegmentDeRoute route;
	protected Regulation r;
	
	public Feu(int sens, SegmentDeRoute route)
	{
		this.sens = sens;
		this.route = route;
		
		this.couleur = CouleurFeu.Vert;	
	}
	
	public abstract void changerCouleur(CouleurFeu couleur);

	public int getSens() {
		return sens;
	}

	public void setSens(int sens) {
		this.sens = sens;
	}

	public CouleurFeu getCouleur() {
		return couleur;
	}

	public void setCouleur(CouleurFeu couleur) {
		this.couleur = couleur;
		setChanged();
		notifyObservers(route);
	}

}
