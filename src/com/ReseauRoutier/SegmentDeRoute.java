package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Random;

public class SegmentDeRoute extends ElementRoute{

	private static int s_id = 1;
	private int id;
	private int longueur;
	private ArrayList<Troncon> troncons;
	private ArrayList<Jonction> jonctions;
	
	private ArrayList<Voiture> mesVoitures;
		
	public SegmentDeRoute()
	{
		this.troncons = new ArrayList<Troncon>();
		setId(s_id);
		s_id+= 1;
	}
	
	public SegmentDeRoute(int min, int max)
	{
		this.troncons = new ArrayList<Troncon>();
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
	
	public ArrayList<Jonction> getJonctions() {
		return jonctions;
	}

	public void setJonctions(ArrayList<Jonction> jonctions) {
		this.jonctions = jonctions;
	}

	@Override
	public String toString() {
		return "Segment [longueur=" + this.longueur + ", troncons=" + this.troncons + "]";
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
		this.mesVoitures = mesVoitures;
	}
	
	public void addVoiture(Voiture v)
	{
		mesVoitures.add(v);
	}
	
	public void removeVoiture(Voiture v)
	{
		mesVoitures.remove(v);
	}
	
}
