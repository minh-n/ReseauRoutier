package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Random;

public class SegmentDeRoute extends ElementRoute{

	private static int s_id = 1;
	private int id;

	public SegmentDeRoute()
	{
		setId(s_id);
		s_id+= 1;
	}
	
	public SegmentDeRoute(int min, int max)
	{
		
		super();
		
		Random rand = new Random();
		super.setLongueur(rand.nextInt((max - min) + 1) + min);
		
		setId(s_id);
		s_id+= 1;

	}
	
	public SegmentDeRoute(int lon)
	{
		super(lon);
		setId(s_id);
		s_id+= 1;

	}
	
	
	/**
	 * Ajoute une voiture a ce segment de route. 
	 * Prend en compte l'occupation d'un segment.
	 * @param v
	 */
	public void ajoutVoiture(Voiture v, int s)
	{
		
		/**
		 * TODO faire les sens
		 */
		v.setSens(s);
		
		v.setRouteActuelle(this);
		boolean occupe = true;
		int i = 0;
		do
		{
			occupe = verifOccupe(i);
			if (occupe == false)
			{
				//TODO bizarre de devoir faire un break ?

				break;
			}
			
			i++;
		}while(occupe);
		//v.setTronconActuel(troncons.get(i)); ca serait un setPosition
		getMesVoitures().add(v);
	}
	
	public boolean verifOccupe(int n)
	{
		for(Voiture voit : getMesVoitures())
		{
			/*if (n == voit.getTronconActuel().getId()) //si le troncon numero n correspond a un troncon occupe par une voiture
			{
				return true;
			}*/
		}
		
		return false;
	}
	
	public void suppressionVoiture(Voiture v)
	{
		v.setRouteActuelle(null); //??
		getMesVoitures().remove(v);
	}
	
	
	
	/**
	 * Deplacement de voitures
	 */
	@Override
	public void deplacerVoiture() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * AFFICHAGE
	 */
	
	@Override
	public String toString() {
		return "Segment id = " + id + " [longueur=" + super.getLongueur() +"]";
	}
	
	/**
	 * Afficher le contenu d'un segment.
	 */
	public void afficherSegment()
	{
		System.out.println("[longueur = " + super.getLongueur());
		System.out.println("]");
	}
	
	
	/*
	 * GETTERS et SETTERS
	 * 
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
