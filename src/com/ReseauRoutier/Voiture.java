package com.ReseauRoutier;

public class Voiture {
	private static int v_id = 0; //incrementeur ID de la voiture
	private int id;
	private int vMax;
	private int longeur; //permet de faire de plus longues voitures (camions)
	private int sens;
	private int vitesse;
	
	private Troncon tronconActuel;
	private SegmentDeRoute routeActuelle;
	
	public Voiture(int v, int s){
		v_id++;
		this.id = v_id;
		this.setVitesse(v);
		this.setSens(s);
	}
	
//	public int avancer()
//	{
//		
//		Troncon newTroncon = routeActuelle.avancerDeNTroncons(tronconActuel.getId(), this.vitesse);
//		this.setTronconActuel(newTroncon);
//		return 0;
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

	public Troncon getTronconActuel() {
		return tronconActuel;
	}

	public void setTronconActuel(Troncon tronconActuel) {
		this.tronconActuel = tronconActuel;
	}

	public SegmentDeRoute getRouteActuelle() {
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
