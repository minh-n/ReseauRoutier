package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Random;

public class SegmentDeRoute extends ElementRoute{

	private static int s_id = 1;
	private int id;
	private boolean traite; // indique si les voitures de l'élément de route ont été traitées dans un intervalle de temps

	
	private ArrayList<Jonction> sesJonctions;

	public SegmentDeRoute()
	{
		setId(s_id);
		s_id+= 1;
		traite = false;
	}
	
	public SegmentDeRoute(int min, int max)
	{
		
		super();
		
		Random rand = new Random();
		super.setLongueur(rand.nextInt((max - min) + 1) + min);
		
		setId(s_id);
		s_id+= 1;

	}
	
	public SegmentDeRoute(int lon, Jonction prec, Jonction suiv)
	{
		super(lon);
		
		
		setId(s_id);
		s_id+= 1;

	}
	
	
	
	/**
	 * Ajoute une voiture a ce segment de route. 
	 * Prend en compte l'occupation d'un segment.
	 * @param v
	 */
	public void ajoutVoiture(Voiture v)
	{
		
		/**
		 * TODO faire les sens
		 */
		//v.setSens(s);
		
		v.setRouteActuelle(this);
		boolean occupe = true;
		int i = 0;
		do
		{
			occupe = estOccupe(i, v.getSens());
	
			i++;
		}while(occupe);
		//v.setTronconActuel(troncons.get(i)); ca serait un setPosition
		if (v.getSens() == 0) getVoituresSens0().add(v);
		else getVoituresSens0().add(v);
	}
	
	public void suppressionVoiture(Voiture v)
	{
		v.setRouteActuelle(null); //??
		getVoituresSens0().remove(v);
	}
	
	
	
	
	
	
	/*****************************************DEPLACEMENT****************************************/
	
	/****																					*****/

	/****											a implementer							*****/

	
	/**
	 * Deplacement de voitures
	 */
	@Override
	public void deplacerVoiture() {

		
		/*ArrayList<Voiture> voitureSegmentSuivant = new ArrayList<Voiture>();
		int distanceRestante = 0; */
	
		System.out.println("Iteration des voitures contenu dans le segment : " + this.getId());

		/*TODO faire pareil pour SENS1 */
		for(Voiture voit : this.getVoituresSens0())
		{
			if (!voit.isTraite()){
				voit.setVitesse(voit.getvMax());
				
				/* Verifications si c'est physiquement possible d'avancer 
				 * autrement, on décrémente la vitesse jusqu'à ce que ce soit possible */
				while (estOccupe(voit.getPositionDansRoute() + voit.getVitesse(), voit.getSens()) && voit.getVitesse() > 0){
					voit.setvMax(voit.getVitesse()-1);
				}
				
				/* Si la vitesse est à 0, lavoiture n'avance pas donc on passe à la voiture suivante */
	 			if (voit.getVitesse() == 0) continue; 
	 			
	 			
				voit.embranchement(voit.getVitesse());
				voit.setTraite(true);
			}
		}
		
		for(Voiture voit : this.getVoituresSens1())
		{
			if (!voit.isTraite()){
				voit.setVitesse(voit.getvMax());
				
				while (estOccupe(voit.getPositionDansRoute() + voit.getVitesse(), voit.getSens()) && voit.getVitesse() > 0){
					voit.setvMax(voit.getVitesse()-1);
				}
				
				/* Si la vitesse est à 0, lavoiture n'avance pas donc on passe à la voiture suivante */
	 			if (voit.getVitesse() == 0) continue; 
	 			
	 			
				voit.embranchement(voit.getVitesse());
				voit.setTraite(true);
			}
		}
	}
	
	

	/**
	 * 
	 */
	public int distanceRestante(Voiture v)
	{
		int distanceRestante = this.getLongueur() - v.getPositionDansRoute();
		//System.out.println("Ce segment mesure " + s.getLongueur() + ", et la voiture aurait depasse de ");
		
		return distanceRestante;
	}
	
	
	/**
	 * Indique s'il reste assez de place sur ce segment pour deplacer la voiture.
	 * @param v
	 * @return false si elle n'est pas suffisante
	 */
	public boolean segmentSuffisant(Voiture v)
	{
		
		if(v.getVitesse() >= distanceRestante(v))
		{
			System.out.println("\n--Pour la voiture " + v.getId() + " => Segment trop court !\n");
			return false;
		}
		return true;
	}
	
	/**
	 * Retourne le nombre de tronçons de dépassement d'une voiture 
	 * dont le déplacement la fait sortir du segment
	 * @param v
	 * @return
	 */
	public int depassementSegment(Voiture v){
		if (!segmentSuffisant(v)){
			return v.getVitesse() - distanceRestante(v);
		}
		return -1;
	}
	
	/**
	 * Indique, pour un sens donné, si le tronçon n°indice est déjà
	 * occupé par une voiture ou non.
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
	
	// TODO redéfinir ce truc ou aloes redéfinir l'arrrayList des jonctions en 2 jonctions "suivante" et "précédente"
	@Override
	public String toString() {
		String affichage = "SegmentDeRoute [id=" + id + ", sesJonctions=" + sesJonctions + "]";
		return affichage;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isTraite() {
		return traite;
	}

	public void setTraite(boolean traite) {
		this.traite = traite;
	}

	public ArrayList<Jonction> getSesJonctions() {
		return sesJonctions;
	}

	public void setSesJonctions(ArrayList<Jonction> sesJonctions) {
		this.sesJonctions = sesJonctions;
	}


}
