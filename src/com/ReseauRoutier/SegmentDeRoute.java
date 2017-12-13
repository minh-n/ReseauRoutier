package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.Regulation.CouleurFeu;
import com.Regulation.Feu;
import com.Regulation.FeuBicolore;

public class SegmentDeRoute extends ElementRoute{

	private static int s_id = 1;
	private int id;
	//private boolean traite; // indique si les voitures de l'element de route ont ete traitees dans un intervalle de temps
	private Feu feuSens0;
	private Feu feuSens1;
	
	private Jonction jonctionSens0;
	private Jonction jonctionSens1;

	public SegmentDeRoute()
	{
		setId(s_id);
		s_id+= 1;
		
		setJonctionSens0(new JonctionSimple());
		setJonctionSens1(new JonctionSimple());
		
		traite = false;
		feuSens0 = new FeuBicolore(0, this);
		feuSens1 = new FeuBicolore(1, this);
	}
	
	public SegmentDeRoute(int min, int max)
	{
		super();
		setJonctionSens0(new JonctionSimple());
		setJonctionSens1(new JonctionSimple());
		Random rand = new Random();
		super.setLongueur(rand.nextInt((max - min) + 1) + min);
		
		setId(s_id);
		s_id+= 1;
		
		feuSens0 = new FeuBicolore(0, this);
		feuSens1 = new FeuBicolore(1, this);

	}
	
	public SegmentDeRoute(int lon)
	{
		super(lon);
		setJonctionSens0(new JonctionSimple());
		setJonctionSens1(new JonctionSimple());
		setId(s_id);
		s_id+= 1;
		
		feuSens0 = new FeuBicolore(0, this);
		feuSens1 = new FeuBicolore(1, this);
	}
	
	/**
	 * Deplacement de voitures sur le segment
	 */
	@Override
	public void deplacerVoiture() {
		
		//System.out.println("Iteration des voitures contenu dans le segment : " + this.getId());
		
		if(feuSens1.getCouleur() == CouleurFeu.Vert)
		{
			for (Iterator<Voiture> ite = voituresSens0.iterator(); ite.hasNext(); ){
				Voiture voit = ite.next();			
				if (!voit.isTraite()){
					voit.setVitesse(voit.getvMax());
					if (segmentSuffisant(voit)){
						//Verifications si c'est physiquement possible d'avancer 
						//autrement, on decremente la vitesse jusqu'a ce que ce soit possible 
						// TODO immplémenter ce truc directement dans voiture.avancer()
						while (estOccupe(voit.getPositionDansRoute() + voit.getVitesse(),
								voit.getSens()) && voit.getVitesse() > 0){
							voit.setVitesse(voit.getVitesse()-1);
						}
	
						// TODO
						// D'autres éléments pourront diminuer la vitesse d'une voiture genre les feux tricolores 
						
						// Si la vitesse est a 0, lavoiture n'avance pas donc on passe a la voiture suivante 
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
		}
		
		if(feuSens1.getCouleur() == CouleurFeu.Vert)
		{
			for (Iterator<Voiture> ite = voituresSens1.iterator(); ite.hasNext(); ){
				Voiture voit = ite.next();
				
				if (!voit.isTraite()){
					voit.setVitesse(voit.getvMax());
					if (segmentSuffisant(voit)){
					
						while (estOccupe(voit.getPositionDansRoute() + voit.getVitesse(), 
								voit.getSens()) && voit.getVitesse() > 0)
						{
							voit.setVitesse(voit.getVitesse()-1);
						}
						
						// TODO
						// D'autres elements pourront diminuer la vitesse d'une voiture genre les feux tricolores 
						
						// Si la vitesse est a 0, lavoiture n'avance pas donc on passe a la voiture suivante 
			 			if (voit.getVitesse() == 0) continue; 
			 			else{
			 				System.out.println("On avance voiture " + voit.getId() );
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
	 * Retourne le nombre de troncons de depassement d'une voiture 
	 * dont le deplacement la fait sortir du segment
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
	 * Indique, pour un sens donne, si le troncon a l'indice est 
	 * occupe par une voiture ou non.
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
//		for (Jonction j:sesJonctions){
//			affichage += " " + j.getId();
//		}
//		affichage += "]";
		return affichage;
	}
	
	public void affichageVoitures(){
		System.out.println("_________Segment n" + this.id + " ("+this.longueur+")\n-- Sens 0 :\n");
		for (Voiture v:voituresSens0){
			System.out.println("\t-Voiture n" + v.getId() + " : pos = "+v.getPositionDansRoute()+", vit = "+v.getVitesse()/*+", sens? = "+v.getSens()+"\n"*/);
		}
		
		System.out.println("\n-- Sens 1 :\n");
		for (Voiture v:voituresSens1){
			System.out.println("\t-Voiture n" + v.getId() + " : pos = "+v.getPositionDansRoute()+", vit = "+v.getVitesse()/*+", sens? = "+v.getSens()+"\n"*/);
		}
		
		System.out.println("\n");
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/*public boolean isTraite() {
		return traite;
	}

	public void setTraite(boolean traite) {
		this.traite = traite;
	}*/

	public Feu getFeuSens0() {
		return feuSens0;
	}

	public void setFeuSens0(Feu feuSens0) {
		this.feuSens0 = feuSens0;
	}

	public Feu getFeuSens1() {
		return feuSens1;
	}

	public void setFeuSens1(Feu feuSens1) {
		this.feuSens1 = feuSens1;
	}

	public Jonction getJonctionSens0() {
		return jonctionSens0;
	}

	public void setJonctionSens0(Jonction jonctionSens0) {
		this.jonctionSens0 = jonctionSens0;
	}

	public Jonction getJonctionSens1() {
		return jonctionSens1;
	}

	public void setJonctionSens1(Jonction jonctionSens1) {
		this.jonctionSens1 = jonctionSens1;
	}


}
