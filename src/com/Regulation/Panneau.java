package com.Regulation;

import java.util.Observable;

import com.ReseauRoutier.SegmentDeRoute;
import com.ReseauRoutier.Voiture;

public class Panneau extends Semaphore{

	private int valeur;
	
	public Panneau(int sens, int valeur, SegmentDeRoute route)
	{
		super(sens, route);
		this.valeur = valeur;
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		if(o instanceof Voiture)
		{
//			if(((Voiture) o).getSens() == sens && ((Voiture) o).getPositionDansRoute() >= this.getPositionDansRoute()
//					&& ((Voiture) o).getPositionPrecedente() <= this.getPositionDansRoute() && ((Voiture) o).getRouteActuelle() == this.getRoute())
//			{
//				System.out.println("Panneau " + this.getId()); 
//				System.out.println("Position : " + this.getPositionDansRoute());
//				System.out.println(o.toString() + "\n");
//				((Voiture) o).setvMax(valeur);
//				System.out.println(o.toString() + "\n");
//			}
		}
	}
}
