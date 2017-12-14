package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.Observable;

import com.Regulation.Capteur;
import com.Regulation.FeuTricolore;
import com.Regulation.RegSimple;
import com.Regulation.Semaphore;

public class Reseau extends Observable{
	
	private ArrayList<Jonction> jonctions;
	private ArrayList<SegmentDeRoute> routes;
	
	public Reseau()
	{
		jonctions = new ArrayList<Jonction>();
		routes = new ArrayList<SegmentDeRoute>();
	}
	
	/**
	 * Initialise le reseau complexe
	 * @return
	 */
	public boolean initReseau(){
		
		JonctionSimple jonc = new JonctionSimple();
		
		jonctions.add(new JonctionBarriere()); 		//0
		jonctions.add(jonc);		//1
		jonctions.add(new JonctionCarrefour(3));		//2
		jonctions.add(new JonctionSimple());		//3
		jonctions.add(new JonctionSimple());		//4	
		jonctions.add(new JonctionCarrefour(4));		//5
		jonctions.add(new JonctionSimple());		//6	
		jonctions.add(new JonctionBarriere());		//7
		jonctions.add(new JonctionSimple());		//8	
		jonctions.add(new JonctionSimple());		//9	
		jonctions.add(new JonctionBarriere());		//10
		
		RegSimple r = new RegSimple(jonc, this);
		
		//Creation des liens entre les jonctions
		
		lierJonctions(jonctions.get(0), jonctions.get(1), 7);

		lierJonctions(jonctions.get(1), jonctions.get(2), 3);
	
		//carrefour1
		lierJonctions(jonctions.get(2), jonctions.get(3), 5);
		lierJonctions(jonctions.get(2), jonctions.get(5), 6);
		lierJonctions(jonctions.get(2), jonctions.get(6), 5);
		
		lierJonctions(jonctions.get(6), jonctions.get(7), 13);

		lierJonctions(jonctions.get(3), jonctions.get(4), 4);
		
		//carrefour2
		lierJonctions(jonctions.get(4), jonctions.get(5), 6);
		lierJonctions(jonctions.get(5), jonctions.get(8), 2);
		
		lierJonctions(jonctions.get(8), jonctions.get(9), 20);
		
		lierJonctions(jonctions.get(9), jonctions.get(10), 15);
		
		setupJonction();
			
		return true;
	}
	
	
	/**
	 * Initialise le reseau simple
	 * @return
	 */
	public boolean initReseauSimple(){
		
		JonctionSimple jonc = new JonctionSimple();
		
		jonctions.add(new JonctionBarriere()); 		//1	
		jonctions.add(jonc);		//2
		jonctions.add(new JonctionSimple());		//3
		jonctions.add(new JonctionSimple());		//4
		jonctions.add(new JonctionBarriere());		//5
		
		RegSimple r = new RegSimple(jonc, this);
		
		//Creation des liens entre les jonctions
		
		lierJonctions(jonctions.get(0), jonctions.get(1), 8);
		lierJonctions(jonctions.get(1), jonctions.get(2), 5);
		lierJonctions(jonctions.get(2), jonctions.get(3), 3);
		lierJonctions(jonctions.get(3), jonctions.get(4), 4);
		
		setupJonction();
				
		return true;
	}
	
	public void setupJonction()
	{
		for(Jonction j:jonctions)
		{
			j.setupFeu();
		}
	}
	
	/**
	 * Fait passer un intervalle de temps
	 */
	public void iteration(){
		/* On traite tous les elements de route du reseau */
		for (Jonction j:jonctions){
			j.deplacerVoiture();
			for(SegmentDeRoute seg:j.getSegments()){
				if (!seg.isTraite()){
					seg.deplacerVoiture();
					seg.setTraite(true);
				}
			}
		}
		/* On reset leur attribut "traite" pour la prochaine iteration */
		resetTraite();
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Relie la jonction 'jonc1' a la jonction 'jonc2' avec le segment de route 'seg'
	 * @param jonc1
	 * @param jonc2
	 * @param seg
	 */
	public boolean lierJonctions(Jonction jonc1, Jonction jonc2, int tailleRoute){
		
		SegmentDeRoute seg = new SegmentDeRoute(tailleRoute);
		
		if(jonc1.ajouterRoute(seg) && jonc2.ajouterRoute(seg))
		{
			seg.setJonctionSens0(jonc1);
			seg.setJonctionSens1(jonc2);
			
			if(jonc1 instanceof JonctionReg)
			{
				FeuTricolore f = new FeuTricolore(0, seg); //TODO accepter feu bi et tri
				if(!((JonctionReg) jonc1).ajouterFeu(f)) return false;
				seg.ajoutSemaphore(f);
			}
			else if(jonc2 instanceof JonctionReg)
			{
				FeuTricolore f = new FeuTricolore(1, seg); //TODO accepter feu bi et tri
				if(!((JonctionReg) jonc2).ajouterFeu(f)) return false;
				seg.ajoutSemaphore(f);
			}
			
			routes.add(seg);
			
			return true;
		}
		
		System.err.println("lierJonction : impossible de lier les deux jonctions !");
		return false;
	}
	
	/**
	 * Ajoute la voiture 'voit' dans le segment n° 'idRoute'
	 * @param voit
	 * @param idJonction
	 */
	public boolean insererVoiture(Voiture voit, int idRoute){
		
		SegmentDeRoute r = getRoute(idRoute);
		if(r.equals(null))
		{
			System.err.println("La route n'existe pas !");
			return false;
		}
		
		voit.setRouteActuelle(r);
		voit.setPositionDansRoute(0);
		
		if (voit.getSens() == 0){
			voit.setRouteSuiv(r.getJonctionSens0()); // la jonction qui suit le 1er segment, dans le sens 0
			r.ajouterVoiture(voit);
		}
		else{
			voit.setRouteSuiv(r.getJonctionSens1()); // la jonction qui suit le 1er segment, dans le sens 1 (i.e. elle même)
			r.ajouterVoiture(voit);
		}
		
		return true;
	}
	
	//Insere un capteur, peut inserer un nbr qqc de capteur a une meme position d'une route
	public boolean insererCapteur(Capteur capt, int idRoute){
	
		SegmentDeRoute r = getRoute(idRoute);
		if(r.equals(null))
		{
			System.err.println("La route n'existe pas !");
			return false;
		}
		
		capt.setRoute(r);
		capt.setPositionDansRoute(capt.getPositionDansRoute());
		
		if(capt.getPositionDansRoute() >= 0 && capt.getPositionDansRoute() < capt.getRoute().getLongueur())
		{
			if (capt.getSens() == 0){
				capt.getRoute().getCapteurSens0().add(capt); 
				capt.addObs();
			}
			else{
				capt.getRoute().getCapteurSens1().add(capt); 
				capt.addObs();
			}
		}
		else
		{
			System.err.println("La route est trop courte !");
			return false;
		}
		
		return true;
	}
	
	public boolean insererSemaphore(Semaphore sema, int idRoute)
	{
		SegmentDeRoute r = getRoute(idRoute);
		if(r.equals(null))
		{
			System.err.println("La route n'existe pas !");
			return false;
		}
		
		r.ajoutSemaphore(sema);
		return true;
	}
	
	public Jonction getJonction(int idJonction)
	{
		for(Jonction j:jonctions)
		{
			if(j.getId() == idJonction)
			{
				return j;
			}
		}
		
		return null;
	}
	
	public SegmentDeRoute getRoute(int idRoute)
	{
		for(SegmentDeRoute r:routes)
		{
			if(r.getId() == idRoute)
			{
				return r;
			}
		}
		
		return null;
	}
	
	private void resetTraite(){
		for (Jonction j:jonctions){
			for (Voiture voit:j.getVoituresSens0()){ // techniquement c'est inutile vu qu'une jonction ne peut contenir qu'une voiture
				voit.setTraite(false);
			}
			for (Voiture voit:j.getVoituresSens1()){
				voit.setTraite(false);
			}
			j.setTraite(false);
			for(SegmentDeRoute seg:j.getSegments()){
				if (seg.isTraite()){
					for (Voiture voit:seg.getVoituresSens0()){
						voit.setTraite(false);
					}
					for (Voiture voit:seg.getVoituresSens1()){
						voit.setTraite(false);
					}
					seg.setTraite(false);
				}
			}
		}
	}
	
	public ArrayList<Jonction> getJonctions() {
		return jonctions;
	}
	
	public void setJonctions(ArrayList<Jonction> jonctions) {
		this.jonctions = jonctions;
	}

	@Override
	public String toString() {
		return "Reseau [jonctions=" + jonctions + "]";
	}
	
	public void affichageVoitures(){
		for (Jonction j:jonctions){
			if(!j.getVoituresSens0().isEmpty() || !j.getVoituresSens1().isEmpty())
			{
				j.affichageVoitures();

			}
			for(SegmentDeRoute seg:j.getSegments()){
				if((!seg.getVoituresSens0().isEmpty() || !seg.getVoituresSens1().isEmpty()))
				{
					if (!seg.isTraite()){
						seg.affichageVoitures();
						seg.setTraite(true);
				}
				}
			}
		}
		resetTraite();
	}

}
