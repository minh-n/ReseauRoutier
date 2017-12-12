package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Jonction extends ElementRoute {

	private static int j_id = 1;
	private static int longueur = 1;
	private int id;
	private ArrayList<SegmentDeRoute> segments;

	//private ArrayList<Voisin> voisins = new ArrayList<Voisin>();
	
	public Jonction()
	{
		super(longueur);
		segments = new ArrayList<>();
		setId(j_id);
		j_id+= 1;
	}
	

	// Fusionnable dans ElementRoute
	@Override
	public void deplacerVoiture() {
System.out.println("Iteration des voitures contenu dans la jonction : " + this.getId());
		
		for (Iterator<Voiture> ite = voituresSens0.iterator(); ite.hasNext(); ){
			Voiture voit = ite.next();
			
			if (!voit.isTraite()){
					voit.setVitesse(voit.getvMax());
					ite.remove();
					voit.embranchement(voit.getVitesse());
					voit.setTraite(true);
			}
						
		}
		
		for (Iterator<Voiture> ite = voituresSens1.iterator(); ite.hasNext(); ){
			Voiture voit = ite.next();
			
			if (!voit.isTraite()){
				voit.setVitesse(voit.getvMax());
				ite.remove();
				voit.embranchement(voit.getVitesse());
				voit.setTraite(true);
			}
			
		}
	}
	
	public void afficherJonction() {
		System.out.println("Jonction n" + id);
		for (SegmentDeRoute v : this.segments){
			System.out.println(v.toString());
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public ArrayList<SegmentDeRoute> getSegments() {
		return segments;
	}


	public void setSegments(ArrayList<SegmentDeRoute> segments) {
		this.segments = segments;
	}
	
	@Override
	public String toString() {
		return "Jonction [id=" + id + ", segments=" + segments + "]";
	}
	
	// Fusionnable dans ElementRoute
	public void affichageVoitures(){
		System.out.println("_________Jonction n°" + this.id + "\n-- Sens 0 :\n");
		for (Voiture v:voituresSens0){
			System.out.println("\t-Voiture n°" + v.getId() + " : pos = "+v.getPositionDansRoute()+", vit = "+v.getVitesse()/*+", sens? = "+v.getSens()+"\n"*/);
		}
		
		System.out.println("\n-- Sens 1 :\n");
		for (Voiture v:voituresSens1){
			System.out.println("\t-Voiture n°" + v.getId() + " : pos = "+v.getPositionDansRoute()+", vit = "+v.getVitesse()/*+", sens? = "+v.getSens()+"\n"*/);
		}
		System.out.println("\n");
	}


}
