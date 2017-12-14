package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Observable;

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
	
	/**
	 * Indique, pour un sens donnﾃｩ, si le tronﾃｧon nﾂｰindice est dﾃｩjﾃ�
	 * occupﾃｩ par une voiture ou non.
	 * @param indice
	 * @param sens
	 * @return
	 */
	public boolean estOccupe(int indice, int sens)
	{
		if (sens == 0){
			for (Voiture v:this.getVoituresSens0()){
				if (v.getPositionDansRoute() == indice) return true;
			}
		}
		else{
			for (Voiture v:this.getVoituresSens1()){
				if (v.getPositionDansRoute() == indice) return true;
			}
		}
		return false;
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
