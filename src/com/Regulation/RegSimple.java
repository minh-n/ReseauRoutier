package com.Regulation;

import java.util.Observable;

import com.ReseauRoutier.JonctionReg;
import com.ReseauRoutier.Reseau;

public class RegSimple extends Regulation{

	public RegSimple(JonctionReg jonction, Reseau reseau)
	{
		super(jonction, reseau);
	}
	
	public RegSimple(JonctionReg jonction)
	{
		super(jonction);
	}
	
	/**
	 * change la couleur des feux d'une jonction a chaque iteration pour avoir qu'un seul feu vert a la fois
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Reseau)
		{
			int i = 0;
			
			while(i < jonction.getSesFeux().size())
			{				
				System.out.println("Jonction actuel "  + jonction.getId());
				System.out.println("son feu " + jonction.getSesFeux().get(0));
				
				if(jonction.getSesFeux().get(i).getCouleur() == CouleurFeu.Vert || jonction.getSesFeux().get(i).getCouleur() == CouleurFeu.Orange)
				{
					jonction.getSesFeux().get(i).changerCouleur();
					jonction.getSesFeux().get(i).regle();
					i++;
					if(i > jonction.getSesFeux().size()-1)
					{
						i = 0;
					}
					jonction.getSesFeux().get(i).changerCouleur();
					jonction.getSesFeux().get(i).regle();
					break;
				}
				i++;
			}
		}
	}

	
}
