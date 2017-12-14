package com.Regulation;

import java.util.Observable;

import com.ReseauRoutier.JonctionReg;
import com.ReseauRoutier.Reseau;

public class RegSimple extends Regulation{

	public RegSimple(JonctionReg jonction, Reseau reseau)
	{
		super(jonction, reseau);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		if(o instanceof Reseau)
		{
			int i = 0;
			
			while(i < jonction.getSesFeux().size()-1)
			{
				if(jonction.getSesFeux().get(i).getCouleur() == CouleurFeu.Vert)
				{
					jonction.getSesFeux().get(i).changerCouleur();
					jonction.getSesFeux().get(i).regle();
					i++;
					if(i >= jonction.getSesFeux().size()-1)
					{
						i = 0;
					}
					jonction.getSesFeux().get(i).changerCouleur();
					jonction.getSesFeux().get(i).regle();
				}
				i++;
			}
		}
	}

	
}
