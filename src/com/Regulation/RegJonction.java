package com.Regulation;

import java.util.Observable;

import com.ReseauRoutier.Jonction;
import com.ReseauRoutier.SegmentDeRoute;

public class RegJonction extends Regulation{

	public RegJonction(Jonction elem)
	{
		this.elem = elem;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof Feu)
		{
			if(((Feu) o).getCouleur() == CouleurFeu.Rouge)
			{
				if(((Feu) o).getSens() == 0)
				{
					for(SegmentDeRoute s:((Jonction) elem).getSegments())
					{
						if(!(s.equals(((Feu) o).getRoute())))
						{
							//s.set
						}
					}
				}
				
			}
		}
	}

	
}
