package com.Regulation;

import com.ReseauRoutier.SegmentDeRoute;
import com.ReseauRoutier.Voiture;

public class PanneauLimitationV extends Semaphore{

	private int valeurvMaxRoute;
	
	public PanneauLimitationV(int sens, int valeur, SegmentDeRoute route)
	{
		
		super(sens, route);

		this.valeurvMaxRoute = valeur;
	}

	@Override
	public void regle() {
		
		if(this.sens == 0)
		{
			for(Voiture v:this.route.getVoituresSens0())
			{
				v.setvMax(this.valeurvMaxRoute);
				if(v.getVitesse() > v.getvMax())
				{
					v.setVitesse(this.valeurvMaxRoute);
				}
			}
		}
		else
		{
			for(Voiture v:this.route.getVoituresSens1())
			{
				v.setvMax(this.valeurvMaxRoute);
				if(v.getVitesse() > v.getvMax())
				{
					v.setVitesse(this.valeurvMaxRoute);
				}			
			}
		}
	}
	
}
