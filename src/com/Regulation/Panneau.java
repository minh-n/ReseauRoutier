package com.Regulation;

import java.util.Observable;

import com.ReseauRoutier.Voiture;

public class Panneau extends Capteur{

	private int valeur;
	
	public Panneau(int pos, int sens, int valeur)
	{
		super(pos, sens);
		this.valeur = valeur;
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		if(o instanceof Voiture)
		{
			if(((Voiture) o).getSens() == this.getSens() && ((Voiture) o).getPositionDansRoute() >= this.getPositionDansRoute()
					&& ((Voiture) o).getPositionPrecedente() <= this.getPositionDansRoute() && ((Voiture) o).getRouteActuelle() == this.getRoute())
			{
				System.out.println("Panneau " + this.getId()); 
				System.out.println("Position : " + this.getPositionDansRoute());
				System.out.println(o.toString() + "\n");
				((Voiture) o).setvMax(valeur);
				System.out.println(o.toString() + "\n");
			}
		}
	}
}
