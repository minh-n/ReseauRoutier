package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Random;

public class SegmentDeRoute extends ElementRoute{

	private static int s_id = 1;
	private int id;
	private int longueur;
	private Jonction jonction1;
	private Jonction jonction2;
	
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
	public Troncon avancerDeNTroncons(int depart, int n)
	{
		
		
		//TODO
		
		/*
		 * 
		 * d = depart+n
		 * if d > max(troncontailleMax)
		 * 
		 * on prend la distance entre depart et tronconTailleMax, et on l'enleve
		 * de d.
		 * 
		 * le reste on enleve 1 (jonction) et on le met sur la route suivante.
		 * ne pas oublier de update la voiture avec la nouvelle route 
		 * et les nouvelles jonctions (how? jsp)
		 * 
		 * 
		 * 
		 */
		
		
		/**
		 * TODO : conditions de jonction.
		 * 
		 * si jonction.getVoisins().size() == 1 c'est une barriere. Demi tour
		 * 
		 * si jonction.getVoisin().size() > 2 c'est un carrouf
		 * dans ce cas, on get un voisin random entre 0 et voisin.size();

		 * 
		 */
		
		
		return troncons.get(depart + n); 
		
		
	}
	
	
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
		v.setRouteActuelle(null);
		v.setTronconActuel(null);
		mesVoitures.remove(v);
	}

	public Jonction getJonction1() {
		return jonction1;
	}

	public void setJonction1(Jonction jonction1) {
		this.jonction1 = jonction1;
	}

	public Jonction getJonction2() {
		return jonction2;
	}

	public void setJonction2(Jonction jonction2) {
		this.jonction2 = jonction2;
	}
	
}
