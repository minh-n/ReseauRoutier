package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Observable;
import com.Regulation.*;

public abstract class ElementRoute extends Observable {

	protected int longueur;
	
	protected ArrayList<Voiture> voituresSens0;
	protected ArrayList<Voiture> voituresSens1;
	
	public ElementRoute()
	{
		voituresSens0 = new ArrayList<Voiture>();
		voituresSens1 = new ArrayList<Voiture>();
	}
	
	public abstract void deplacerVoiture();
	
	public ElementRoute(int lon)
	{
		voituresSens0 = new ArrayList<Voiture>();
		voituresSens1 = new ArrayList<Voiture>();
		this.longueur = lon;
	}

	public void resetTraite(){
		for (Voiture v:voituresSens0){
			v.setTraite(false);
		}
		for (Voiture v:voituresSens1){
			v.setTraite(false);
		}
	}
	
	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
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
