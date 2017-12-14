package com.ReseauRoutier;

import java.util.Observable;

import com.Regulation.Capteur;

public class Voiture extends Observable{
	
	private static int v_id = 0; //incrementeur ID de la voiture
	private int id;
	
	private int vMax;
	private int longeur; //permet de faire de plus longues voitures (camions)
	private int sens;
	private int vitesse;
	private int positionPrecedente;
	private int positionDansRoute;
	private boolean traite; // indique si la voiture a ete traitee dans un intervalle de temps
	
	private ElementRoute routeActuelle;
	private ElementRoute routePrec;
	private ElementRoute routeSuiv;
	
	public Voiture(int v){
		v_id++;
		this.id = v_id;
		this.setVitesse(v);
		this.traite = false;
	}
	
	public Voiture(int max, int dir){
		v_id++;
		this.id = v_id;
		this.vMax = max;
		this.vitesse = this.vMax;
		this.sens = dir;
		this.traite = false;
	}
	
	/* Cette fonction part du principe que sa vitesse ne lui permet pas de depasser le segment sur lequel elle est */
	public void avancer(){
		setPositionPrecedente(getPositionDansRoute());
		setPositionDansRoute(getPositionDansRoute() + getVitesse());
	}
	
	public void avancer(int avancement){
		setPositionPrecedente(getPositionDansRoute());
		setPositionDansRoute(getPositionDansRoute() + avancement);
	}

	
	/*/!\ fonction recursive */
	public void embranchement(int reste){
		
		/* Telle qu'elle est la fonction autorise deux voitures etre sur un moeme tronçon ! => modifier avancer */
		reste-= (routeActuelle.getLongueur() - this.getPositionDansRoute());
		deleteObservers();
		setPositionPrecedente(0);
		routePrec = routeActuelle;
		routeActuelle = routeSuiv;
		routeSuiv = determinerProchain();
		
		
		//System.out.println(nomRoute(routePrec)+" -> "+nomRoute(routeActuelle)+", reste = "+reste);
		
		if (this.sens == 0){
			if(routePrec.getVoituresSens0().contains(this))  routePrec.getVoituresSens0().remove(this);
			this.routeActuelle.getVoituresSens0().add(this);
			addObs();
		}
		else{
			if(routePrec.getVoituresSens1().contains(this)) routePrec.getVoituresSens1().remove(this);
			this.routeActuelle.getVoituresSens1().add(this);
			addObs();
		}
		
		
		this.setPositionDansRoute(0);
		if (reste >= routeActuelle.getLongueur()){
			embranchement(reste);
		}
		else{
			avancer(reste);
		}
	}
	
	public ElementRoute determinerProchain(){
		
		/* Si la voiture est sur une jonction */
		if (this.routeActuelle instanceof Jonction){
			Jonction joncActuelle = (Jonction) this.routeActuelle;
			
			/* Si la voiture est sur une barriere on lui fait juste changer de sens*/
			if (joncActuelle.getSegments().size() == 1){ 
				if(this.sens == 0) this.sens = 1;
				else this.sens = 0;
				return this.routePrec; 
			}
			
			/* Si la voiture est sur une jonction simple on la fait aller sur le prochain segment de route*/
			else if (joncActuelle.getSegments().size() == 2){ 
				if (joncActuelle.getSegments().get(0) == this.routePrec) return joncActuelle.getSegments().get(1); // pas trouve d'autre moyen qu'on retourne pas celle sur laquelle on etait pas precedemment y-y
				else return joncActuelle.getSegments().get(0);
			}
			
			/* Si la voiture est sur un carrefour on choisit le segment au hasard*/
			else{ 
				int choixSegment = (int)(Math.random() * joncActuelle.getSegments().size());
				SegmentDeRoute segmentChoisi = joncActuelle.getSegments().get(choixSegment);
				System.out.println("La voiture " + this.getId() + " a decide de prendre la jonction " + choixSegment +" !\n");

				/* Si le random nous fait tomber sur le meme segment que precedemment, on change de sens */
				if (segmentChoisi == this.routePrec){
					if (this.sens == 0) this.sens = 1;
					else this.sens = 0;
				}
				
				return segmentChoisi;
			}
		}
		
		/* Sinon elle est sur un segment de route : on la fait passer sur la jonction suivante. */
		else{
			SegmentDeRoute segmentActuel = (SegmentDeRoute) this.routeActuelle;
			if (segmentActuel.getJonctionSens0() == this.routePrec) return segmentActuel.getJonctionSens1();
			else return segmentActuel.getJonctionSens0();
		}
	}
	
	public void addObs()
	{
		if(getRouteActuelle() instanceof SegmentDeRoute)
		{
			if(getSens() == 0)
			{
				for(Capteur c:((SegmentDeRoute) getRouteActuelle()).getCapteurSens0())
				{
					this.addObserver(c);
				}
			}
			else
			{
				for(Capteur c:((SegmentDeRoute) getRouteActuelle()).getCapteurSens1())
				{
					this.addObserver(c);
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "Voiture [id=" + id + ", vMax=" + vMax + ", sens=" + sens + ", vitesse=" + vitesse
				+ ", positionDansRoute=" + positionDansRoute + ", positionPrecedente=" + positionPrecedente + ", traite=" + traite + "]";
	}
	
	public String nomRoute(ElementRoute elmt){
		String resultat = "?";
		if (elmt instanceof Jonction){
			Jonction jonc = (Jonction) elmt;
			resultat = "j"+jonc.getId();
		}
		else if (elmt instanceof SegmentDeRoute){
			SegmentDeRoute seg = (SegmentDeRoute) elmt;
			resultat = "s"+seg.getId();
		}
		return resultat;
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

	public ElementRoute getRouteActuelle() {
		return routeActuelle;
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

	public ElementRoute getRoutePrec() {
		return routePrec;
	}

	public void setRoutePrec(ElementRoute routePrec) {
		this.routePrec = routePrec;
	}

	public ElementRoute getRouteSuiv() {
		return routeSuiv;
	}

	public void setRouteSuiv(ElementRoute routeSuiv) {
		this.routeSuiv = routeSuiv;
	}

	public int getPositionDansRoute() {
		return positionDansRoute;
	}

	public void setPositionDansRoute(int positionDansRoute) {
		this.positionDansRoute = positionDansRoute;
		setChanged();
		notifyObservers();
	}

	public boolean isTraite() {
		return traite;
	}

	public void setTraite(boolean traite) {
		this.traite = traite;
	}

	public void setRouteActuelle(ElementRoute routeActuelle) {
		this.routeActuelle = routeActuelle;
	}

	public int getPositionPrecedente() {
		return positionPrecedente;
	}

	public void setPositionPrecedente(int positionPrecedente) {
		this.positionPrecedente = positionPrecedente;
	}
}
