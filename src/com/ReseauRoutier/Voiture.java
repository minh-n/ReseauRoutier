package com.ReseauRoutier;

public class Voiture {
	
	private static int v_id = 0; //incrementeur ID de la voiture
	private int id;
	
	private int vMax;
	private int longeur; //permet de faire de plus longues voitures (camions)
	private int sens;
	private int vitesse;
	
	private Troncon tronconActuel;
	
	private ElementRoute routeActuelle;
	private ElementRoute routePrec;
	private ElementRoute routeSuiv;
	
	public Voiture(int v){
		v_id++;
		this.id = v_id;
		this.setVitesse(v);
	}
	
	@Override
	public String toString() {
		return "Voiture [id=" + id + ", vMax=" + vMax + "]";
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

	public ElementRoute getRouteActuelle() {
		return routeActuelle;
	}

	public void setRouteActuelle(SegmentDeRoute routeActuelle) {
		this.routeActuelle = routeActuelle;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public int getSens() {
		return sens;
	}

	public void setSens(int sens) {
		this.sens = sens;
	}
	
	
	

	
}
