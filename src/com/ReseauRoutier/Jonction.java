package com.ReseauRoutier;

import java.util.ArrayList;


public abstract class Jonction extends ElementRoute {

	private static int j_id = 1;
	private static int longueur = 1;
	private int id;
	private ArrayList<SegmentDeRoute> segments;

	//private ArrayList<Voisin> voisins = new ArrayList<Voisin>();
	
	public Jonction()
	{
		super(longueur);
		setId(j_id);
		j_id+= 1;
	}
	
	
	/*

	
	public Segment segSuivant(Vehicule v) 
	{
		for(Segment s : sesAcces)
		{
			if(s != v.getSaRoute())
			{
				return s;
			}
		}
	}*/
	
	
	
	public void afficherJonction() {
		System.out.println("Jonction n" + id);
		for (SegmentDeRoute v : this.segments){
			System.out.println(v.toString());
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public ArrayList<SegmentDeRoute> getSegments() {
		return segments;
	}


	public void setSegments(ArrayList<SegmentDeRoute> segments) {
		this.segments = segments;
	}
	
	
}
