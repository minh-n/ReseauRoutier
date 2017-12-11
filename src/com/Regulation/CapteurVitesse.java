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
			if(((Voiture) o).getSens() == this.getSens() && ((Voiture) o).getPositionDansRoute() >= this.getPositionDansRoute()
					&& ((Voiture) o).getPositionPrecedente() <= this.getPositionDansRoute() && ((Voiture) o).getRouteActuelle() == this.getRoute())
			{
				System.out.println("Capteur de vitesse " + this.getId()); 
				System.out.println("Position : " + this.getPositionDansRoute());
				System.out.println("La vitesse de la voiture "+ ((Voiture) o).getId() + " est "+ ((Voiture) o).getVitesse());
				System.out.println(o.toString() + "\n");
			}
		}
	}
}
