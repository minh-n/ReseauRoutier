package com.Regulation;

import com.ReseauRoutier.SegmentDeRoute;

public class FeuTricolore extends Feu{

	public FeuTricolore(int sens, SegmentDeRoute route)
	{
		super(sens, route);
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
