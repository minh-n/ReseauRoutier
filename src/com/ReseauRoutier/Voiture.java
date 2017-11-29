package com.ReseauRoutier;

public class Voiture {
	private static int inc = 0;
	private int id;
	private int vMax;
	private int longeur; //permet de faire de plus longues voitures (camions)
	
	private Troncon tronconActuel;
	
	public Voiture(){
		inc++;
		this.id = inc;
	}
	
	@Override
	public String toString() {
		return "Voiture [id=" + id + ", vMax=" + vMax + ", longeur=" + longeur + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getvMax() {
		return vMax;
	}


	public void setvMax(int vMax) {
		this.vMax = vMax;
	}

	public int getLongeur() {
		return longeur;
	}

	public void setLongeur(int longeur) {
		this.longeur = longeur;
	}

	public Troncon getTronconActuel() {
		return tronconActuel;
	}

	public void setTronconActuel(Troncon tronconActuel) {
		this.tronconActuel = tronconActuel;
	}

	
}
