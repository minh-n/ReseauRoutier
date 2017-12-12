package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.Regulation.Feu;

public class SegmentDeRoute extends ElementRoute{

	private static int s_id = 1;
	private int id;
	private boolean traite; // indique si les voitures de l'ﾃｩlﾃｩment de route ont ﾃｩtﾃｩ traitﾃｩes dans un intervalle de temps
	private Feu feuSens0;
	private Feu feuSens1;
	
	private ArrayList<Jonction> sesJonctions;

	public SegmentDeRoute()
	{
		setId(s_id);
		s_id+= 1;
		sesJonctions = new ArrayList<>();
		traite = false;
	}
	
	public SegmentDeRoute(int min, int max)
	{
		
		super();
		sesJonctions = new ArrayList<>();
		Random rand = new Random();
		super.setLongueur(rand.nextInt((max - min) + 1) + min);
		
		setId(s_id);
		s_id+= 1;

	}
	
	public SegmentDeRoute(int lon)
	{
		super(lon);
		sesJonctions = new ArrayList<>();
		setId(s_id);
		s_id+= 1;

	}
	
	
	
//	/**
//	 * Ajoute une voiture a ce segment de route. 
//	 * Prend en compte l'occupation d'un segment.
//	 * @param v
//	 */
//	public void ajoutVoiture(Voiture v)
//	{
//		
//		/**
//		 * TODO faire les sens
//		 */
//		//v.setSens(s);
//		
//		v.setRouteActuelle(this);
//		boolean occupe = true;
//		int i = 0;
//		do
//		{
//			occupe = estOccupe(i, v.getSens());
//	
//			i++;
//		}while(occupe);
//		//v.setTronconActuel(troncons.get(i)); ca serait un setPosition
//		if (v.getSens() == 0) getVoituresSens0().add(v);
//		else getVoituresSens0().add(v);
//		v.addObs();
//	}
	
	/*public void suppressionVoiture(Voiture v)
	{
		v.setRouteActuelle(null); //??
		getVoituresSens0().remove(v);
	}*/
	
	/**
	 * Deplacement de voitures sur le segment
	 */
	@Override
	public void deplacerVoiture() {
		
		System.out.println("Iteration des voitures contenu dans le segment : " + this.getId());
		
		for (Iterator<Voiture> ite = voituresSens0.iterator(); ite.hasNext(); ){
			Voiture voit = ite.next();			
			if (!voit.isTraite()){
				voit.setVitesse(voit.getvMax());
				if (segmentSuffisant(voit)){
					//Verifications si c'est physiquement possible d'avancer 
					//autrement, on dﾃｩcrﾃｩmente la vitesse jusqu'ﾃ� ce que ce soit possible 
					// TODO immplémenter ce truc directement dans voiture.avancer()
					while (estOccupe(voit.getPositionDansRoute() + voit.getVitesse(), voit.getSens()) && voit.getVitesse() > 0){
						voit.setVitesse(voit.getVitesse()-1);
					}

					// TODO
					// D'autres éléments pourront diminuer la vitesse d'une voiture genre les feux tricolores 
					
					// Si la vitesse est ﾃ� 0, lavoiture n'avance pas donc on passe ﾃ� la voiture suivante 
		 			if (voit.getVitesse() == 0) continue; 
		 			else{
		 				voit.avancer();
		 			}
				}
				else{
					ite.remove();
					voit.embranchement(voit.getVitesse());
				}
				voit.setTraite(true);
			}
		}
		
		
		for (Iterator<Voiture> ite = voituresSens1.iterator(); ite.hasNext(); ){
			Voiture voit = ite.next();
			
			if (!voit.isTraite()){
				voit.setVitesse(voit.getvMax());
				if (segmentSuffisant(voit)){
				
					while (estOccupe(voit.getPositionDansRoute() + voit.getVitesse(), voit.getSens()) && voit.getVitesse() > 0){
						voit.setVitesse(voit.getVitesse()-1);
					}
					
					// TODO
					// D'autres éléments pourront diminuer la vitesse d'une voiture genre les feux tricolores 
					
					// Si la vitesse est ﾃ� 0, lavoiture n'avance pas donc on passe ﾃ� la voiture suivante 
		 			if (voit.getVitesse() == 0) continue; 
		 			else{
		 				voit.avancer();
		 			}
				}
				else{
					ite.remove(); // necessaire pour ne pas avoir de CurrentMosificationException
					voit.embranchement(voit.getVitesse());
				}
				voit.setTraite(true);
			}
		}
	}
	
	public int distanceRestante(Voiture v)
	{
		int distanceRestante = this.getLongueur() - v.getPositionDansRoute();
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
	 * Retourne le nombre de tronﾃｧons de dﾃｩpassement d'une voiture 
	 * dont le dﾃｩplacement la fait sortir du segment
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
	
	@Override
	public String toString() {
		String affichage = "SegmentDeRoute [id=" + id + ", sesJonctions=";
		for (Jonction j:sesJonctions){
			affichage += " " + j.getId();
		}
		affichage += "]";
		return affichage;
	}
	
	public void affichageVoitures(){
		System.out.println("_________Segment n°" + this.id + " ("+this.longueur+")\n-- Sens 0 :\n");
		for (Voiture v:voituresSens0){
			System.out.println("\t-Voiture n°" + v.getId() + " : pos = "+v.getPositionDansRoute()+", vit = "+v.getVitesse()/*+", sens? = "+v.getSens()+"\n"*/);
		}
		
		System.out.println("\n-- Sens 1 :\n");
		for (Voiture v:voituresSens1){
			System.out.println("\t-Voiture n°" + v.getId() + " : pos = "+v.getPositionDansRoute()+", vit = "+v.getVitesse()/*+", sens? = "+v.getSens()+"\n"*/);
		}
		
		System.out.println("\n");
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
