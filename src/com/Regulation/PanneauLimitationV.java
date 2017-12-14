package com.Regulation;

import com.ReseauRoutier.SegmentDeRoute;
import com.ReseauRoutier.Voiture;

public class PanneauLimitationV extends Semaphore{

	private int valeur;
	
	public PanneauLimitationV(int sens, int valeur, SegmentDeRoute route)
	{
		
		super(sens, route);

		this.valeur = valeur;
	}

	@Override
	public void regle() {
		
		if(this.sens == 0)
		{
			for(Voiture v:this.route.getVoituresSens0())
			{
				v.setvMax(this.valeur);
				if(v.getVitesse() > v.getvMax())
				{
					v.setVitesse(this.valeur);
				}
			}
		}
		else
		{
			for(Voiture v:this.route.getVoituresSens1())
			{
				v.setvMax(this.valeur);
				if(v.getVitesse() > v.getvMax())
				{
					v.setVitesse(this.valeur);
				}			
				}
		}
	}
	
}
