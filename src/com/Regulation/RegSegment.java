package com.Regulation;

import java.util.Observable;

import com.ReseauRoutier.SegmentDeRoute;
import com.ReseauRoutier.Voiture;

public class RegSegment extends Regulation {
	
	public RegSegment(SegmentDeRoute elem)
	{
		this.elem = elem;
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		if(o instanceof Feu)
		{
			if(((Feu) o).getCouleur().getCouleur().compareTo("Rouge") == 0)
			{
				if(((Feu) o).getSens() == 0)
				{
					for(Voiture v:((SegmentDeRoute) elem).getVoituresSens0())
					{
						v.setVitesse(0);
					}
				}
				else
				{
					for(Voiture v:((SegmentDeRoute) elem).getVoituresSens0())
					{
						v.setVitesse(0);
					}
				}
			}
		}
		else if(((Feu) o).getCouleur().getCouleur().compareTo("Vert") == 0)
		{
			if(((Feu) o).getSens() == 0)
			{
				for(Voiture v:((SegmentDeRoute) elem).getVoituresSens0())
				{
					v.setVitesse(v.getvMax());
				}
			}
			else
			{
				for(Voiture v:((SegmentDeRoute) elem).getVoituresSens0())
				{
					v.setVitesse(v.getvMax());
				}
			}
		}
	}

}
