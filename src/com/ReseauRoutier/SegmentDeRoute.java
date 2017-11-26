package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Random;

public class SegmentDeRoute extends ElementRoute{

	private static int s_id = 1;
	private int id;
	private int longueur;
	private ArrayList<Troncon> troncons;
	private ArrayList<Jonction> jonctions;
	
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
	
}
