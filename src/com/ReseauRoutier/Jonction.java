package com.ReseauRoutier;

import java.util.ArrayList;


public class Jonction extends ElementRoute {


	private static int j_id = 1;
	private int id;
	private ArrayList<Voisin> voisins = new ArrayList<Voisin>();
	
	public Jonction()
	{
		setId(j_id);
		j_id+= 1;
	}
	
	public void afficherJonction() {
		System.out.println("Jonction n" + id);
		for (Voisin v : this.voisins){
			System.out.println(v.toString());
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Voisin> getVoisins() {
		return voisins;
	}

	public void setVoisins(ArrayList<Voisin> voisins) {
		this.voisins = voisins;
	}
	
	
}
