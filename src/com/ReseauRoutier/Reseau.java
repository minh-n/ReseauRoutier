package com.ReseauRoutier;

import java.util.ArrayList;

public class Reseau {
	
	
	
	private ArrayList<SegmentDeRoute> segments;
	private ArrayList<Jonction> jonctions;
	
	

	public Reseau()
	{
		segments = new ArrayList<SegmentDeRoute>();
		jonctions = new ArrayList<Jonction>();

	}
	
	
	public ArrayList<SegmentDeRoute> getSegments() {
		return segments;
	}
	
	public void setSegments(ArrayList<SegmentDeRoute> segments) {
		this.segments = segments;
	}
	
	public ArrayList<Jonction> getJonctions() {
		return jonctions;
	}
	
	public void setJonctions(ArrayList<Jonction> jonctions) {
		this.jonctions = jonctions;
	}
	
	public boolean initReseau(){
		
		Jonction j1 = new Jonction();
		Jonction j2 = new Jonction();

		j2.addVoisin(j1);
		
		j2.afficherJonction(); // ne marche pas, voir fichier
		
		
		return true;
	}
	
//	private ArrayList<ElementRoute> elements;
//	
//	
//	
//	public Reseau()
//	{
//		elements = new ArrayList<ElementRoute>();
//	}
//	
//	
//
//
//	public ArrayList<ElementRoute> getElements() {
//		return elements;
//	}
//
//	public void setElements(ArrayList<ElementRoute> elements) {
//		this.elements = elements;
//	}
//
//
//	@Override
//	public String toString() {
//		return "Reseau [elements=" + elements + "]";
//	}

}
