package com.Regulation;

import java.util.Observable;

import com.ReseauRoutier.Voiture;

public class CapteurVitesse extends Capteur{

	public CapteurVitesse(int pos, int sens)
	{
		super(pos, sens);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if(o instanceof Voiture)
		{
			if(((Voiture) o).getSens() == this.getSens() && ((Voiture) o).getPositionDansRoute() > this.getPositionDansRoute()) //pas au point
			{
				System.out.println("Changement dans voiture " + ((Voiture) o).getId() + " par capteur " + this.getId());
				System.out.println(o.toString() + "\n");
			}
		}
	}
}
