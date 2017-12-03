package com.ReseauRoutier;

import java.util.Observable;

public abstract class ElementRoute extends Observable {

	private int longueur;
	private int id;
	
	//liste voiture ou pas ?
	
	public ElementRoute()
	{
		
	}
	
	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
