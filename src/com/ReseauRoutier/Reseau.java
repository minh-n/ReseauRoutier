package com.ReseauRoutier;

import java.util.ArrayList;

public class Reseau {
	
	
	private ArrayList<Jonction> jonctions;
	
	
	public Reseau()
	{
		jonctions = new ArrayList<Jonction>();

	}
	
	public boolean initReseau(){
		// TODO
		
		jonctions.add(new JonctionBarriere());
		jonctions.add(new JonctionSimple());
		jonctions.add(new JonctionSimple());
		jonctions.add(new JonctionBarriere());
		
		
		
		return true;
	}
	
	
	public void iteration(){
		/* On traite tous les éléments de route du réseau */
		for (Jonction j:jonctions){
			j.deplacerVoiture();
			for(SegmentDeRoute seg:j.getSegments()){
				if (!seg.isTraite()){
					seg.deplacerVoiture();
					seg.setTraite(true);
				}
			}
		}
		
		/* On reset leur attribut "traite" pour la prochaine itération */
		for (Jonction j:jonctions){
			for(SegmentDeRoute seg:j.getSegments()){
				if (seg.isTraite())	seg.setTraite(false);
			}
		}
		
	}
	
	public ArrayList<Jonction> getJonctions() {
		return jonctions;
	}
	
	public void setJonctions(ArrayList<Jonction> jonctions) {
		this.jonctions = jonctions;
	}
	


}
