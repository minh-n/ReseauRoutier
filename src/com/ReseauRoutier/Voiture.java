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
	
	/**
	 * Fait avancer la voiture en fonction de sa vitesse
	 */
	public void avancer(){
		setPositionPrecedente(getPositionDansRoute());
		while (routeActuelle.estOccupe(positionDansRoute + vitesse, sens) && vitesse > 0){
			vitesse -= 1;
		}
		setPositionDansRoute(positionDansRoute + vitesse);
	}
	
	/**
	 * Fait avancer la voiture de 'avancement' tronçons
	 * @param avancement
	 */
	public void avancer(int avancement){
		setPositionPrecedente(getPositionDansRoute());
		while (routeActuelle.estOccupe(positionDansRoute + avancement, sens) && avancement > 0){
			avancement -= 1;
		}
		setPositionDansRoute(getPositionDansRoute() + avancement);
	}

	
	/**
	 * Effectue l'embranchement sur un nouvel Element de Route
	 * @param reste le nombre de tronçons restant à parcourir après l'embranchement
	 */
	public void embranchement(int reste){
		
		reste-= (routeActuelle.getLongueur() - this.getPositionDansRoute());
		deleteObservers();
		setPositionPrecedente(0);
		routePrec = routeActuelle;
		routeActuelle = routeSuiv;
		routeSuiv = determinerProchain();
		
		
		
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
	
	/**
	 * Indique si l'embranchement est physiquement possible
	 * @param avancement
	 * @return true s'il y assez de place dans la route suivante pour avancer
	 */
	public boolean embranchementPossible(int avancement){
		if (avancement < 0){
			System.out.println("avancement < 0");
			return false;
		}
		int sensActuel;
		for(int i = avancement; i >= 0 ; i--){
			sensActuel = (routeSuiv instanceof JonctionBarriere ? inverse(sens) : sens );
			if (!routeSuiv.estOccupe(i, sensActuel)){
				System.out.println("Embranchement possible");
				return true; 
			}
		}
		System.out.println("Embranchement impossible");
		return false;
	}
	
	/**
	 * Pour un sens donne, donne le sens inverse 
	 * @param sens
	 * @return
	 */
	private int inverse(int sens){
		return (sens == 0 ? 1 : 0);
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
