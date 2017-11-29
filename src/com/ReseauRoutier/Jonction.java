package com.ReseauRoutier;

import java.util.ArrayList;

public class Jonction extends ElementRoute {
	
	private static int j_id = 1;
	private int id;
	private ArrayList<Voisin> voisins = new ArrayList<Voisin>();
	
	public Jonction()
	{
		//aiguillage = new HashMap<>();
		setId(j_id);
		j_id+= 1;
	}

	/**
	 * ajout d'un voisin
	 * @param j
	 */
	public void addVoisin(Jonction j)
	{
		SegmentDeRoute nouvSegment = new SegmentDeRoute(1, 10);
		Voisin v = new Voisin(j, nouvSegment, 1, 2);
		this.voisins.add(v);
		j.getVoisins().add(new Voisin(this, nouvSegment, 2, 1));
	}
	
	/**
	 * ajout de plusieurs voisins contenus dans une liste
	 * @param jonctions
	 */
	public void addVoisin(ArrayList<Jonction> jonctions)
	{
		for (Jonction j:jonctions){
			SegmentDeRoute nouvSegment = new SegmentDeRoute(1, 10);
			Voisin v = new Voisin(j, nouvSegment, 1, 2);
			voisins.add(v);
			j.getVoisins().add(new Voisin(this, nouvSegment, 2, 1));
		}
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
