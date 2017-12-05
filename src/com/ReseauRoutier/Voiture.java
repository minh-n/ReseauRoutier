package com.ReseauRoutier;

public class Voiture {
	
	private static int v_id = 0; //incrementeur ID de la voiture
	private int id;
	
	private int vMax;
	private int longeur; //permet de faire de plus longues voitures (camions)
	private int sens;
	private int vitesse;
	
	private int positionDansRoute;
	
	private ElementRoute routeActuelle;
	private ElementRoute routePrec;
	private ElementRoute routeSuiv;
	
	public Voiture(int v){
		v_id++;
		this.id = v_id;
		this.setVitesse(v);
	}
	
	/* Cette fonction part du principe que sa vitesse ne lui permet pas de dépasser le segment sur lequel elle est */
	public void avancer(){
		if (sens == 0){
			setPositionDansRoute(getPositionDansRoute() + getVitesse());
		}
		else{
			setPositionDansRoute(getPositionDansRoute() - getVitesse());
		}
	}
	
	public void avancer(int avancement){
		if (sens == 0){
			setPositionDansRoute(getPositionDansRoute() + avancement);
		}
		else{
			setPositionDansRoute(getPositionDansRoute() - avancement);
		}
	}
	
	/*/!\ fonction récursive */
	public void embranchement(int reste){
		
		
		
		routePrec = routeActuelle;
		routeActuelle = routeSuiv;
		routeSuiv = determinerProchain();
		
		if (this.sens == 0){
			routePrec.getVoituresSens0().remove(this);
			this.routeActuelle.getVoituresSens0().add(this);
		}
		else{
			routePrec.getVoituresSens0().remove(this);
			this.routeActuelle.getVoituresSens0().add(this);
		}
		
		
		if (reste >= routeActuelle.getLongueur()){
			embranchement(reste - routeActuelle.getLongueur());
		}
		else{
			avancer(reste);
		}
	}
	
	public ElementRoute determinerProchain(){
		if (this.routeActuelle instanceof Jonction){
			Jonction joncActuelle = (Jonction) this.routeActuelle;
			if (joncActuelle.getSegments().size() == 1){ // dans le cas d'une barrière 
				if (this.sens == 0) this.sens = 1;
				else this.sens = 0;
				return this.routePrec; 
			}
			else if (joncActuelle.getSegments().size() == 2){ // cas d'une jonction simple
				if (joncActuelle.getSegments().get(0) == routePrec) return joncActuelle.getSegments().get(1); // pas trouvé d'autre moyen qu'on retourne pas celle sur laquelle on était pas précedemment y-y
				else return joncActuelle.getSegments().get(0);
			}
			else{ // cas d'un carrefour 
				int choixSegment = (int)(Math.random() * joncActuelle.getSegments().size() + 1);
				return joncActuelle.getSegments().get(choixSegment);
			}
		}
		else{
			// TODO depuis un SegmentDeRoute, comment on accède aux Jonctions qu'il lie? pas de moyen encore
			return null; // pour que ça compile, rip y-y
		}
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
	}	
}
