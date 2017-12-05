package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Observable;

public abstract class ElementRoute extends Observable {

	private int longueur;
	private int id;
	
	private ArrayList<Voiture> voituresSens0;
	private ArrayList<Voiture> voituresSens1;


	public ElementRoute()
	{
		voituresSens0 = new ArrayList<Voiture>();
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

	public ArrayList<Voiture> getVoituresSens0() {
		return voituresSens0;
	}

	public void setVoituresSens0(ArrayList<Voiture> mesVoitures) {
		this.voituresSens0 = mesVoitures;
	}
	
	public ArrayList<Voiture> getVoituresSens1() {
		return voituresSens1;
	}


	public void setVoituresSens1(ArrayList<Voiture> voituresSens1) {
		this.voituresSens1 = voituresSens1;
	}
}
