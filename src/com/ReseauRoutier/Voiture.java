package com.ReseauRoutier;

public class Voiture {
	private static int inc = 0;
	private int id;
	private int vMax;
	private int longeur;
	
	public Voiture(){
		inc++;
		this.id = inc;
	}

	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Voiture v = new Voiture();
//		System.out.println(v.toString());
//		
//		Voiture v1 = new Voiture();
//
//		System.out.println(v1.toString());
//		System.out.println(v.toString());
//
//	}

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

	
}
