package com.Regulation;

import com.ReseauRoutier.SegmentDeRoute;
import com.ReseauRoutier.Voiture;

public class FeuBicolore extends Feu{
	
	public FeuBicolore(int sens, SegmentDeRoute route)
	{
		super(sens, route);
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
	}
	
	public CouleurFeu getCouleur()
	{
		return super.getCouleur();
	}
	
	public void changerCouleur()
	{
		if(this.couleur == CouleurFeu.Rouge)
		{
			this.couleur = CouleurFeu.Vert;
		}
		else if(this.couleur == CouleurFeu.Vert)
		{
			this.couleur = CouleurFeu.Rouge;
		}
	}
}
