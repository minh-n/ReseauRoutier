package com.ReseauRoutier;

public class Voiture {
	
	private static int v_id = 0; //incrementeur ID de la voiture
	private int id;
	
	private int vMax;
	private int longeur; //permet de faire de plus longues voitures (camions)
	private int sens;
	private int vitesse;
	private int positionDansRoute;
	private boolean traite; // indique si la voiture a été traitée dans un intervalle de temps
	
	private ElementRoute routeActuelle;
	private ElementRoute routePrec;
	private ElementRoute routeSuiv;
	
	public Voiture(int v){
		v_id++;
		this.id = v_id;
		this.setVitesse(v);
		traite = false;
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
		
		/* Si la voiture est sur une jonction */
		if (this.routeActuelle instanceof Jonction){
			Jonction joncActuelle = (Jonction) this.routeActuelle;
			
			/* Si la voiture est sur une barrière on lui fait juste changer de sens*/
			if (joncActuelle.getSegments().size() == 1){ 
				if (this.sens == 0) this.sens = 1;
				else this.sens = 0;
				return this.routePrec; 
			}
			
			/* Si la voiture est sur une jonction simple on la fait aller sur le prochain segment de route*/
			else if (joncActuelle.getSegments().size() == 2){ 
				if (joncActuelle.getSegments().get(0) == this.routePrec) return joncActuelle.getSegments().get(1); // pas trouvé d'autre moyen qu'on retourne pas celle sur laquelle on était pas précedemment y-y
				else return joncActuelle.getSegments().get(0);
			}
			
			/* Si la voiture est sur un carrefour on choisit le segment au hasard*/
			else{ 
				int choixSegment = (int)(Math.random() * joncActuelle.getSegments().size() + 1);
				SegmentDeRoute segmentChoisi = joncActuelle.getSegments().get(choixSegment);
				
				/* Si le random nous fait tomber sur le meme segment que précédemment, on change de sens */
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
			if (segmentActuel.getSesJonctions().get(0) == this.routePrec) return segmentActuel.getSesJonctions().get(0);
			else return segmentActuel.getSesJonctions().get(0);
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

	public boolean isTraite() {
		return traite;
	}

	public void setTraite(boolean traite) {
		this.traite = traite;
	}

	public void setRouteActuelle(ElementRoute routeActuelle) {
		this.routeActuelle = routeActuelle;
	}	
}
