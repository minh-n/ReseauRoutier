package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Random;

public class SegmentDeRoute extends ElementRoute{

	private static int s_id = 1;
	private int id;
	private ArrayList<Troncon> troncons;
	private ArrayList<Voiture> mesVoitures;
		
	public SegmentDeRoute()
	{
		this.troncons = new ArrayList<Troncon>();
		this.mesVoitures = new ArrayList<Voiture>();

		setId(s_id);
		s_id+= 1;
	}
	
	public SegmentDeRoute(int min, int max)
	{
		Random rand = new Random();
		super.setLongueur(rand.nextInt((max - min) + 1) + min);

		
		this.troncons = new ArrayList<Troncon>();
		this.mesVoitures = new ArrayList<Voiture>();

		setId(s_id);
		s_id+= 1;
		
		for(int i = 0 ; i < super.getLongueur() ; i++)
		{
			troncons.add(new Troncon());
		}
	}
	
	public SegmentDeRoute(int lon)
	{
		super(lon);
		this.troncons = new ArrayList<Troncon>();
		setId(s_id);
		s_id+= 1;
		
		for(int i = 0 ; i < super.getLongueur() ; i++)
		{
			troncons.add(new Troncon());
		}
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
		v.setTronconActuel(troncons.get(i));
		mesVoitures.add(v);
	}
	
	public boolean verifOccupe(int n)
	{
		for(Voiture voit : mesVoitures)
		{
			if (n == voit.getTronconActuel().getId()) //si le troncon numero n correspond a un troncon occupe par une voiture
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void suppressionVoiture(Voiture v)
	{
		v.setRouteActuelle(null); //??
		v.setTronconActuel(null); //TODO tout
		mesVoitures.remove(v);
	}
	
	
	
	public ArrayList<Troncon> getTroncons() {
		return troncons;
	}
	
	public void setTroncons(ArrayList<Troncon> troncons) {
		this.troncons = troncons;
	}
	
	@Override
	public String toString() {
		return "Segment id = " + id + " [longueur=" + super.getLongueur() + ", troncons=" + this.troncons + "]";
	}
	
	/**
	 * Afficher le contenu d'un segment.
	 */
	public void afficherSegment()
	{
		System.out.println("[longueur = " + super.getLongueur() 
				+ "\ntroncons = ");
		int i = 0;
		for(Troncon t : this.troncons)
		{
			t.setId(i);
			i++;
			System.out.println(t.toString());
		}
		System.out.println("]");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Voiture> getMesVoitures() {
		return mesVoitures;
	}

	public void setMesVoitures(ArrayList<Voiture> mesVoitures) {
		this.mesVoitures = mesVoitures; //TODO manque setRouteActuelle?
	}


}
