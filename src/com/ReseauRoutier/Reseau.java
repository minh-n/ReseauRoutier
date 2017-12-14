package com.ReseauRoutier;

import java.util.ArrayList;

public class Reseau {
	
	
	private ArrayList<Jonction> jonctions;
	
	
	public Reseau()
	{
		jonctions = new ArrayList<Jonction>();

	}
	
	/**
	 * Initialise le réseau
	 * @return
	 */
	public boolean initReseau(){
		
		/*
		// On crée un petit réseau de la forme 
		// O-------O---O----------O
		 
		jonctions.add(new JonctionBarriere());
		jonctions.add(new JonctionSimple());
		jonctions.add(new JonctionSimple());
		jonctions.add(new JonctionBarriere());
		
		lierJonctions(jonctions.get(0), jonctions.get(1), new SegmentDeRoute(7));
		lierJonctions(jonctions.get(1), jonctions.get(2), new SegmentDeRoute(3));
		lierJonctions(jonctions.get(2), jonctions.get(3), new SegmentDeRoute(10));
		*/			
		jonctions.add(new JonctionBarriere()); 		//0
		jonctions.add(new JonctionSimple());		//1
		jonctions.add(new JonctionCarrefour());		//2
		jonctions.add(new JonctionSimple());		//3
		jonctions.add(new JonctionSimple());		//4	
		jonctions.add(new JonctionCarrefour());		//5
		jonctions.add(new JonctionSimple());		//6	
		jonctions.add(new JonctionBarriere());		//7
		jonctions.add(new JonctionSimple());		//8	
		jonctions.add(new JonctionSimple());		//9	
		jonctions.add(new JonctionBarriere());		//10
			
		//Creation des liens entre les jonctions
			
		lierJonctions(jonctions.get(0), jonctions.get(1), new SegmentDeRoute(7));
			
		lierJonctions(jonctions.get(1), jonctions.get(2), new SegmentDeRoute(3));
		
		//carrefour1
		lierJonctions(jonctions.get(2), jonctions.get(3), new SegmentDeRoute(5));
		lierJonctions(jonctions.get(2), jonctions.get(5), new SegmentDeRoute(6));
		lierJonctions(jonctions.get(2), jonctions.get(6), new SegmentDeRoute(5));
		
		lierJonctions(jonctions.get(6), jonctions.get(7), new SegmentDeRoute(13));

		lierJonctions(jonctions.get(3), jonctions.get(4), new SegmentDeRoute(4));
			
		//carrefour2
		lierJonctions(jonctions.get(4), jonctions.get(5), new SegmentDeRoute(6));
		lierJonctions(jonctions.get(5), jonctions.get(8), new SegmentDeRoute(2));
			
		lierJonctions(jonctions.get(8), jonctions.get(9), new SegmentDeRoute(20));
			
		lierJonctions(jonctions.get(9), jonctions.get(10), new SegmentDeRoute(15));
		
		return true;
	}
	
	/**
	 * Fait passer un intervalle de temps
	 */
	public void iteration(){
		/* On traite tous les ﾃｩlﾃｩments de route du rﾃｩseau */
		for (Jonction j:jonctions){
			j.deplacerVoiture();
			for(SegmentDeRoute seg:j.getSegments()){
				if (!seg.isTraite()){
					seg.deplacerVoiture();
					seg.setTraite(true);
				}
			}
		}
		
		System.out.println("\n################################# FIN ITERATION\n\n");
		/* On reset leur attribut "traite" pour la prochaine itﾃｩration */
		resetTraite();
		
	}
	
	/**
	 * Relie la jonction 'jonc1' à la jonction 'jonc2' avec le segment de route 'seg'
	 * @param jonc1
	 * @param jonc2
	 * @param seg
	 */
	public void lierJonctions(Jonction jonc1, Jonction jonc2, SegmentDeRoute seg){
		jonc1.getSegments().add(seg);
		jonc2.getSegments().add(seg);
		seg.getSesJonctions().add(jonc1);
		seg.getSesJonctions().add(jonc2);
	}
	
	/**
	 * Ajoute la voiture 'voit' dans le premier segment de la jonction n° 'idJonction'
	 * @param voit
	 * @param idJonction
	 */
	public void insérerVoiture(Voiture voit, int idJonction){
		Jonction joncActuelle = jonctions.get(idJonction);
		voit.setRouteActuelle(joncActuelle.getSegments().get(0));
		voit.setPositionDansRoute(0);
		if (voit.getSens() == 0){
			voit.setRouteSuiv(joncActuelle.getSegments().get(0).getSesJonctions().get(0)); // la jonction qui suit le 1er segment, dans le sens 0
			voit.getRouteActuelle().getVoituresSens0().add(voit); 
		}
		else{
			voit.setRouteSuiv(joncActuelle); // la jonction qui suit le 1er segment, dans le sens 1 (i.e. elle même)
			voit.getRouteActuelle().getVoituresSens1().add(voit); 
		}
	}
	
	private void resetTraite(){
		for (Jonction j:jonctions){
			for (Voiture voit:j.getVoituresSens0()){ // techniquement c'est inutile vu qu'une jonction ne peut contenir qu'une voiture
				voit.setTraite(false);
			}
			for (Voiture voit:j.getVoituresSens1()){
				voit.setTraite(false);
			}
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
			j.affichageVoitures();
			for(SegmentDeRoute seg:j.getSegments()){
				if (!seg.isTraite()){
					seg.affichageVoitures();
					seg.setTraite(true);
				}
			}
		}
		resetTraite();
	}

}
