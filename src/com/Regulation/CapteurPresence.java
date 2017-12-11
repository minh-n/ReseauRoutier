package com.Regulation;

import java.util.Observable;

import com.ReseauRoutier.Voiture;

public class CapteurPresence extends Capteur{

	public CapteurPresence(int pos, int sens)
	{
		super(pos, sens);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(((Voiture) o).getSens() == this.getSens() && ((Voiture) o).getPositionDansRoute() >= this.getPositionDansRoute()
				&& ((Voiture) o).getPositionPrecedente() <= this.getPositionDansRoute() && ((Voiture) o).getRouteActuelle() == this.getRoute())
		{
			System.out.println("Capteur de presence " + this.getId()); 
			System.out.println("Position : " + this.getPositionDansRoute());
			System.out.println(o.toString() + "\n");
		}
	}

	

}
