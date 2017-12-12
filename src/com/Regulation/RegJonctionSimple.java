package com.Regulation;

import java.util.Observable;

import com.ReseauRoutier.Jonction;
import com.ReseauRoutier.SegmentDeRoute;

public class RegJonctionSimple extends RegJonction{

	public RegJonctionSimple(Jonction elem)
	{
		this.elem = elem;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof Feu)
		{
			
			if(((Feu) o).getCouleur().getCouleur().compareTo("Rouge") == 0)
			{
				if(((Feu) o).getSens() == 0)
				{
					for(SegmentDeRoute s:((Jonction) elem).getSegments())
					{
						if(!(s.equals(((Feu) o).getRoute())))
						{
							s.getFeuSens1().getCouleur().setCouleur("Rouge");
						}
					}
				}
				else
				{
					for(SegmentDeRoute s:((Jonction) elem).getSegments())
					{
						if(!(s.equals(((Feu) o).getRoute())))
						{
							s.getFeuSens0().getCouleur().setCouleur("Rouge");
						}
					}
				}
			}
			else
			{
				if(((Feu) o).getSens() == 0)
				{
					for(SegmentDeRoute s:((Jonction) elem).getSegments())
					{
						if(!(s.equals(((Feu) o).getRoute())))
						{
							s.getFeuSens1().getCouleur().setCouleur("Vert");
						}
					}
				}
				else
				{
					for(SegmentDeRoute s:((Jonction) elem).getSegments())
					{
						if(!(s.equals(((Feu) o).getRoute())))
						{
							s.getFeuSens0().getCouleur().setCouleur("Vert");
						}
					}
				}
			}
		}
	}

	
}
