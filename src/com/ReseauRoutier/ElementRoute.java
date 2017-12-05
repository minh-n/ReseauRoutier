package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Observable;

public abstract class ElementRoute extends Observable {

	private int longueur;
	private int id;
	
	private ArrayList<Voiture> mesVoitures;
	
	//liste voiture ou pas ?
	
	public ElementRoute()
	{
		mesVoitures = new ArrayList<Voiture>();
	}
	
	
	public abstract void deplacerVoiture();
	
	
	public ElementRoute(int lon)
	{
		this.longueur = lon;
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

	public ArrayList<Voiture> getMesVoitures() {
		return mesVoitures;
	}

	public void setMesVoitures(ArrayList<Voiture> mesVoitures) {
		this.mesVoitures = mesVoitures;
	}
}
