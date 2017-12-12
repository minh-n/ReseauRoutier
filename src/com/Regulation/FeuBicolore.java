package com.Regulation;

import com.ReseauRoutier.SegmentDeRoute;

public class FeuBicolore extends Feu{
	
	public FeuBicolore(int sens, SegmentDeRoute route)
	{
		super(sens, route);
		this.r = new RegSegment(route);
		this.addObserver(r);
	}
	
	public void changerCouleur(CouleurFeu couleur)
	{
	}

	
	public CouleurFeu getCouleur()
	{
		return super.getCouleur();
	}
	
	public void setCouleur(CouleurFeu c)
	{
		super.setCouleur(c);
	}
}
