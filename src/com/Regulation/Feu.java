package com.Regulation;

import com.ReseauRoutier.SegmentDeRoute;

public abstract class Feu extends Semaphore{
	
	private CouleurFeu couleur;
	protected RegSegment regSeg;
	protected RegJonction regJonc;
	
	public Feu(int sens, SegmentDeRoute route)
	{
		super(sens, route);
		
		this.regSeg = new RegSegment(route);
		this.addObserver(regSeg);
		
		if(sens == 0)
		{
			this.regJonc = new RegJonction(route.getJonctionSens0());
			this.addObserver(regJonc);
		}
		else
		{
			this.regJonc = new RegJonction(route.getJonctionSens1());
			this.addObserver(regJonc);
		}
		
		this.couleur = CouleurFeu.Vert;	
	}
	
	public SegmentDeRoute getRoute() {
		return route;
	}

	public void setRoute(SegmentDeRoute route) {
		this.route = route;
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
