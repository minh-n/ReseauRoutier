package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Observable;
import com.Regulation.*;

public abstract class ElementRoute extends Observable {

	protected int longueur;	
	protected boolean traite; // indique si les voitures de l'element de route ont ete traitees dans un intervalle de temps

	protected ArrayList<Voiture> voituresSens0;
	protected ArrayList<Voiture> voituresSens1;
	protected ArrayList<Capteur> capteurSens0;
	protected ArrayList<Capteur> capteurSens1;
	
	public ElementRoute()
	{
		voituresSens0 = new ArrayList<Voiture>();
		voituresSens1 = new ArrayList<Voiture>();
		capteurSens0 = new ArrayList<Capteur>();
		capteurSens1 = new ArrayList<Capteur>();
	}
	
	public abstract void deplacerVoiture();
	
	public ElementRoute(int lon)
	{
		voituresSens0 = new ArrayList<Voiture>();
		voituresSens1 = new ArrayList<Voiture>();
		capteurSens0 = new ArrayList<Capteur>();
		capteurSens1 = new ArrayList<Capteur>();
		this.longueur = lon;
	}
	
	public ArrayList<Capteur> getCapteurSens0() {
		return capteurSens0;
	}

	public void setCapteurSens0(ArrayList<Capteur> capteurSens0) {
		this.capteurSens0 = capteurSens0;
	}

	public ArrayList<Capteur> getCapteurSens1() {
		return capteurSens1;
	}

	public void setCapteurSens1(ArrayList<Capteur> capteurSens1) {
		this.capteurSens1 = capteurSens1;
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

	public boolean isTraite() {
		return traite;
	}

	public void setTraite(boolean traite) {
		this.traite = traite;
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
