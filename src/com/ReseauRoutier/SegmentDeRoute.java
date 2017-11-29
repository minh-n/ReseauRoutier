package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Random;

public class SegmentDeRoute extends ElementRoute{

	private static int s_id = 1;
	private int id;
	private int longueur;
	private int sens1;
	private int sens2;
	
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
		this.troncons = new ArrayList<Troncon>();
		this.mesVoitures = new ArrayList<Voiture>();

		Random rand = new Random();
		longueur = rand.nextInt((max - min) + 1) + min;
		setId(s_id);
		s_id+= 1;
		
		for(int i = 0 ; i < longueur ; i++)
		{
			troncons.add(new Troncon());
		}
	}
	
	public SegmentDeRoute(int lon)
	{
		this.longueur = lon;
		this.troncons = new ArrayList<Troncon>();
		setId(s_id);
		s_id+= 1;
		
		for(int i = 0 ; i < this.longueur ; i++)
		{
			troncons.add(new Troncon());
		}
		
	}
	
	/**
	 * Retourne un troncon. Permet de trouver le troncon d'arrivee
	 * a partir d'un troncon de depart et d'un decalage de n.
	 * @param depart
	 * @param n
	 * @return
	 */
//	public Troncon avancerDeNTroncons(int depart, int n)
//	{		
//		return troncons.get(depart + n); 
//	}	
	
	public int getLongueur() {
		return longueur;
	}
	
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	
	public ArrayList<Troncon> getTroncons() {
		return troncons;
	}
	
	public void setTroncons(ArrayList<Troncon> troncons) {
		this.troncons = troncons;
	}
	
	@Override
	public String toString() {
		return "Segment id = " + id + " [longueur=" + this.longueur + ", troncons=" + this.troncons + "]";
	}
	
	/**
	 * Afficher le contenu d'un segment.
	 */
	public void afficherSegment()
	{
		System.out.println("[longueur = " + this.longueur 
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
	
	/**
	 * Ajoute une voiture a ce segment de route. 
	 * Prend en compte l'occupation d'un segment.
	 * @param v
	 */
	public void ajoutVoiture(Voiture v)
	{
		
		/**
		 * TODO faire les sens
		 */
		v.setSens(sens1);
		
		v.setRouteActuelle(this);
		boolean occupe = true;
		int i = 0;
		do
		{
			occupe = verifOccupe(i);
			if (occupe == false)
			{
				//TODO bizarre de devoir faire un break
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

	public int getSens1() {
		return sens1;
	}

	public void setSens1(int sens1) {
		this.sens1 = sens1;
	}

	public int getSens2() {
		return sens2;
	}

	public void setSens2(int sens2) {
		this.sens2 = sens2;
	}


}
