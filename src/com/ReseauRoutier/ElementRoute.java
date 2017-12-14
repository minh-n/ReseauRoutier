package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Observable;
import com.Regulation.*;

public abstract class ElementRoute extends Observable {

	protected int longueur;	
	protected boolean traite; // indique si les voitures de l'element de route ont ete traitees dans un intervalle de temps

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
	
	public boolean ajouterVoiture(Voiture v)
	{
		if(v.getPositionDansRoute() < 0 || v.getPositionDansRoute() >= this.longueur)
		{
			System.err.println("ajoutVoiture : route trop courte !");
			return false;
		}
		
		v.setRouteActuelle(this);
		
		if(v.getSens() == 0)
		{
			v.setRouteSuiv(v.determinerProchain()); 
			this.voituresSens0.add(v);
		}
		else
		{
			v.setRouteSuiv(v.determinerProchain()); 
			this.voituresSens1.add(v); 
		}
		
		return true;
	}
	
	public boolean retirerVoiture(Voiture v)
	{
		if(v.getSens() == 0)
		{
			return this.voituresSens0.remove(v);
		}
		return this.voituresSens1.remove(v);
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

	public ArrayList<Voiture> getVoituresSens1() {
		return voituresSens1;
	}
}
