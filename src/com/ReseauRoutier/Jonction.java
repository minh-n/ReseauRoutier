package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Jonction extends ElementRoute {

	private static int j_id = 1;
	private static int longueur = 1;
	private int id;
	private ArrayList<SegmentDeRoute> segments;
	protected int nbRoutes;
	
	public Jonction()
	{
		super(longueur);
		segments = new ArrayList<SegmentDeRoute>();
		id = j_id;
		j_id+= 1;
	}
	

	/**
	 * Opère au deplacement des voitures dans cette Jonction
	 */
	@Override
	public void deplacerVoiture() {
		
		for (Iterator<Voiture> ite = voituresSens0.iterator(); ite.hasNext(); ){
			Voiture voit = ite.next();
			
			if (!voit.isTraite()){
				if (voit.embranchementPossible(voit.getVitesse())){
					voit.setVitesse(voit.getvMax());
					ite.remove();
					voit.embranchement(voit.getVitesse());
					voit.setTraite(true);
				}
			}
						
		}
		
		for (Iterator<Voiture> ite = voituresSens1.iterator(); ite.hasNext(); ){
			Voiture voit = ite.next();
			
			if (!voit.isTraite()){
				if (voit.embranchementPossible(voit.getVitesse())){
					voit.setVitesse(voit.getvMax());
					ite.remove();
					voit.embranchement(voit.getVitesse());
					voit.setTraite(true);
				}
			}
		}
	}
	
	public int getId() {
		return id;
	}

	public ArrayList<SegmentDeRoute> getSegments() {
		return segments;
	}

	public boolean ajouterRoute(SegmentDeRoute route)
	{
		if(this.segments.size() < nbRoutes)
		{
			this.segments.add(route);
			return true;
		}
		
		System.err.println("ajouterRoute: jonction pleine !");
		return false;
	}
	
	public boolean retirerRoute(SegmentDeRoute route)
	{
		return this.segments.remove(route);
	}

	/**
	 * permet de mettre le premier feu de la jonction au vert
	 * si la jonction ne possede pas de feu, ne fait rien
	 */
	public void setupFeu(){}
	
	@Override
	public String toString() {
		return "Jonction [id=" + id + ", segments=" + segments + "]";
	}
	
	/**
	 * Affiche les voitures dans cette Jonction et leur etat
	 */
	public void affichageVoitures(){

		System.out.println("_________Jonction n" + this.id + "\n-- Sens 0 :\n");

		for (Voiture v:voituresSens0){

			System.out.println("\t-Voiture n" + v.getId() + " : pos = "+v.getPositionDansRoute()+", vit = "+v.getVitesse()/*+", sens? = "+v.getSens()+"\n"*/);

			
		}
		
		System.out.println("\n-- Sens 1 :\n");
		for (Voiture v:voituresSens1){

			System.out.println("\t-Voiture n" + v.getId() + " : pos = "+v.getPositionDansRoute()+", vit = "+v.getVitesse()/*+", sens? = "+v.getSens()+"\n"*/);

		}
		System.out.println("\n");
	}

	
}
