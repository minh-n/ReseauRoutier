package com.ReseauRoutier;

import java.util.Random;

public class SegmentDeRoute extends ElementRoute{

	private static int s_id = 1;
	private int id;

	public SegmentDeRoute()
	{
		setId(s_id);
		s_id+= 1;
	}
	
	public SegmentDeRoute(int min, int max)
	{
		
		super();
		
		Random rand = new Random();
		super.setLongueur(rand.nextInt((max - min) + 1) + min);
		
		setId(s_id);
		s_id+= 1;

	}
	
	public SegmentDeRoute(int lon)
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
			voit.setVitesse(voit.getvMax());
			
			/* Verifications si c'est physiquement possible d'avancer 
			 * autrement, on décrémente la vitesse jusqu'à ce que ce soit possible */
			while (estOccupe(voit.getPositionDansRoute() + voit.getVitesse(), voit.getSens()) && voit.getVitesse() > 0){
				voit.setvMax(voit.getVitesse()-1);
			}
			
			if (voit.getVitesse() == 0) continue; // si sa vitesse est 0, passer à la suite
			
			
			voit.embranchement(voit.getVitesse());
			/* On regarde si le fait d'avancer la voiture la fait sortir d'un segment ou pas */
			/*if(!segmentSuffisant(voit))
			{
				if (voit.getRouteSuiv().getLongueur() == 1){ // cas Jonction (ou Section de longueur 1)
					if (depassementSegment(voit) == 1){
						voit.embranchement();
					}
					else{
						
					}
				}
				// TODO on fait avancer ou reculer (?)
				// TODO oummar
				// TODO oumma
				// TODO oumm
				// TODO oum
				// TODO ou
				// TODO o
				}
				else{
					voit.avancer();
				}
				
			}*/
		}
		
		
		//on deplace les voitures vers le segment suivant. Il faut faire les verif
		
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
	
	public int depassementSegment(Voiture v){
		if (!segmentSuffisant(v)){
			return v.getVitesse() - distanceRestante(v);
		}
		return -1;
	}

	
	
	
	
	
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
	
	/**
	 * On regarde s'il y a une voiture devant la voiture v dans la limite de la vitesse
	 * @param v
	 * @return
	 */
	public boolean voitureDevant(Voiture v)
	{
		/*for (Voiture v : getMesVoitures()){
			if (v != voit){
				if (voit.getPosition() + voit.getVitesse() <)
			}
		}*/
		
		//s'il y a une voiture dans v.position + v.vitesse, on retourne true 
		// true = collision. La voiture v s'arrete avant la voiture suivante et sa vitesse est reduite a zero.
		
		//est-ce qu'on diminue la vitesse directement dans cette fonction ? ou plutot dans la fonction deplacer.
		return false;
	}
	
	
	/**
	 * Renvoie true s'il y a un panneau sens interdit dans la distance que la voiture va parcourir
	 * @param v
	 * @return
	 */
	public boolean panneauSensInterditDevant(Voiture v)
	{
		
		//pareil, on verifie la position de la voiture + sa vitesse. S'il y a qqc dans cet intervalle, on renvoie true
		
		//sinon false
		return false;
	}
	
	
	/*
	 * AFFICHAGE
	 */
	
	@Override
	public String toString() {
		return "Segment id = " + id + " [longueur=" + super.getLongueur() +"]";
	}
	
	/**
	 * Afficher le contenu d'un segment.
	 */
	public void afficherSegment()
	{
		System.out.println("[longueur = " + super.getLongueur());
		System.out.println("]");
	}
	
	
	/*
	 * GETTERS et SETTERS
	 * 
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
