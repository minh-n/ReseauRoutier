package com.Regulation;

import com.ReseauRoutier.SegmentDeRoute;
import com.ReseauRoutier.Voiture;

public class FeuTricolore extends Feu{

	public FeuTricolore(int sens, SegmentDeRoute route)
	{
		super(sens, route);
	}
	
	
	public CouleurFeu getCouleur()
	{
		return super.getCouleur();
	}
	
	@Override
	public void regle()
	{
		if(this.couleur == CouleurFeu.Rouge)
		{
			if(this.sens == 0)
			{
				for(Voiture v:this.route.getVoituresSens0())
				{
					v.setVitesse(0);
				}
			}
			else
			{
				for(Voiture v:this.route.getVoituresSens1())
				{
					v.setVitesse(0);
				}
			}
		}
		else if(this.couleur == CouleurFeu.Vert)
		{
			if(this.sens == 0)
			{
				for(Voiture v:this.route.getVoituresSens0())
				{
					v.setVitesse(v.getvMax());
				}
			}
			else
			{
				for(Voiture v:this.route.getVoituresSens1())
				{
					v.setVitesse(v.getvMax());
				}
			}
		}
		else
		{
			if(this.sens == 0)
			{
				for(Voiture v:this.route.getVoituresSens0())
				{
					v.setVitesse(v.getvMax()/2);
				}
			}
			else
			{
				for(Voiture v:this.route.getVoituresSens1())
				{
					v.setVitesse(v.getvMax()/2);
				}
			}
		}
	}
	
	public void changerCouleur()
	{
		if(this.couleur == CouleurFeu.Rouge)
		{
			System.out.println("FEU VERT");
			this.couleur = CouleurFeu.Vert;
		}
		else if(this.couleur == CouleurFeu.Vert)
		{
			System.out.println("FEU ORANGE");
			this.couleur = CouleurFeu.Orange;
		}
		else
		{
			this.couleur = CouleurFeu.Rouge;
			System.out.println("FEU ROUGE");
		}
	}
}
