package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Iterator;

import com.Regulation.Capteur;
import com.Regulation.Feu;
import com.Regulation.Semaphore;

public class SegmentDeRoute extends ElementRoute{

	private static int s_id = 1;
	private int id;
	
	private Jonction jonctionSens0;
	private Jonction jonctionSens1;
	
	private ArrayList<Capteur> capteurSens0;
	private ArrayList<Capteur> capteurSens1;
	private ArrayList<Semaphore> semaphoreSens0;
	private ArrayList<Semaphore> semaphoreSens1;

	public SegmentDeRoute()
	{
		id = s_id;
		s_id+= 1;
		
		traite = false;
		
		capteurSens0 = new ArrayList<Capteur>();
		capteurSens1 = new ArrayList<Capteur>();
		
		semaphoreSens0 = new ArrayList<Semaphore>();
		semaphoreSens1 = new ArrayList<Semaphore>();
	}
		
	public SegmentDeRoute(int lon)
	{
		super(lon);
		id = s_id;
		s_id+= 1;

		capteurSens0 = new ArrayList<Capteur>();
		capteurSens1 = new ArrayList<Capteur>();
		
		semaphoreSens0 = new ArrayList<Semaphore>();
		semaphoreSens1 = new ArrayList<Semaphore>();
	}
	
	public void ajoutSemaphore(Semaphore sema)
	{
		Semaphore temp = null;
		boolean vu = false;
		
		if(sema.getSens() == 0)
		{
			for(Semaphore s:semaphoreSens0)
			{
				if(s.getClass() == sema.getClass() || (s instanceof Feu && sema instanceof Feu)) //impossible d'avoir deux feux differents, mais possible d'avoir plusieurs panneaux differents
				{
					temp = s;
					vu = true;
				}
			}
			
			if(vu)
			{
				semaphoreSens0.remove(temp);
			}
			semaphoreSens0.add(sema);
		}
		else
		{
			for(Semaphore s:semaphoreSens1)
			{
				if(s.getClass() == sema.getClass() || (s instanceof Feu && sema instanceof Feu))
				{
					temp = s;
					vu = true;
				}
			}
			if(vu)
			{
				semaphoreSens1.remove(temp);
			}
			semaphoreSens1.add(sema);
		}
	}
	
	public boolean retirerSema(Semaphore sema)
	{
		if(sema.getSens() == 0)
		{
			return this.semaphoreSens0.remove(sema);
		}
		
		return this.semaphoreSens1.remove(sema);
	}
	
	@Override
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
			v.setRouteSuiv(this.jonctionSens0); 
			this.voituresSens0.add(v);
			
			for(Capteur c:this.capteurSens0)
			{
				v.addObserver(c);
			}
			
			for(Semaphore s:this.semaphoreSens0)
			{
				s.regle();
			}
		}
		else
		{
			v.setRouteSuiv(this.jonctionSens1); 
			this.voituresSens1.add(v); 
			
			for(Capteur c:this.capteurSens1)
			{
				v.addObserver(c);
			}
			
			for(Semaphore s:this.semaphoreSens0)
			{
				s.regle();
			}
		}
		
		return true;
	}

	public ArrayList<Semaphore> getSemaphoreSens0() {
		return semaphoreSens0;
	}

	public ArrayList<Semaphore> getSemaphoreSens1() {
		return semaphoreSens1;
	}

	/**
	 * Deplacement de voitures sur le segment
	 */
	@Override
	public void deplacerVoiture() {
		
			for (Iterator<Voiture> ite = voituresSens0.iterator(); ite.hasNext(); ){
				
				for(Semaphore s:this.semaphoreSens0)
				{
					System.out.println("On applique le semaphore " + s.getClass().getName() );
				
					if(s instanceof Feu)
					{
						System.out.println("Le feu est de couleur " + ((Feu)s).getCouleur());

					}
					s.regle();
				}
				
				Voiture voit = ite.next();			
				if (!voit.isTraite()){
					//voit.setVitesse(voit.getvMax());
					if (segmentSuffisant(voit)){
						//Verifications si c'est physiquement possible d'avancer 
						//autrement, on decremente la vitesse jusqu'a ce que ce soit possible 
						while (estOccupe(voit.getPositionDansRoute() + voit.getVitesse(),
								voit.getSens()) && voit.getVitesse() > 0){
							voit.setVitesse(voit.getVitesse()-1);
						}		
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
		
		
	
			for (Iterator<Voiture> ite = voituresSens1.iterator(); ite.hasNext(); ){
								
				for(Semaphore s:this.semaphoreSens1)
				{
					s.regle();
				}
				
				Voiture voit = ite.next();
				
				if (!voit.isTraite()){
					if (segmentSuffisant(voit)){
					
						while (estOccupe(voit.getPositionDansRoute() + voit.getVitesse(), 
								voit.getSens()) && voit.getVitesse() > 0)
						{
							voit.setVitesse(voit.getVitesse()-1);
						}
						
			 			if (voit.getVitesse() == 0) continue; 
			 			else{
			 				System.out.println("On avance voiture " + voit.getId() );
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
	

	/**
	 * @param v
	 * @return la distance restante avant la fin de la route
	 */
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

	@Override
	public String toString() {
		String affichage = "SegmentDeRoute [id=" + id + ", sesJonctions=";
		return affichage;
	}
	
	/**
	 * Affiche les voitures sur ce segment et leur etat
	 */
	public void affichageVoitures(){
		System.out.println("_________Segment n" + this.id + " ("+this.longueur+")\n-- Sens 0 :\n");
		for (Voiture v:voituresSens0){
			System.out.println("\t-Voiture n" + v.getId() + " : pos = "+v.getPositionDansRoute()+", vit = "+v.getVitesse());
		}
		
		System.out.println("\n-- Sens 1 :\n");
		for (Voiture v:voituresSens1){
			System.out.println("\t-Voiture n" + v.getId() + " : pos = "+v.getPositionDansRoute()+", vit = "+v.getVitesse());
		}
		
		System.out.println("\n");
	}
	
	public int getId() {
		return id;
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

	public ArrayList<Capteur> getCapteurSens0() {
		return capteurSens0;
	}

	public ArrayList<Capteur> getCapteurSens1() {
		return capteurSens1;
	}

}
