package com.ReseauRoutier;

import java.util.ArrayList;

public class Reseau {
	
	
	
	private ArrayList<Jonction> jonctions;
	
	

	public Reseau()
	{
		jonctions = new ArrayList<Jonction>();

	}
	
	public ArrayList<Jonction> getJonctions() {
		return jonctions;
	}
	
	public void setJonctions(ArrayList<Jonction> jonctions) {
		this.jonctions = jonctions;
	}
	
	public boolean initReseau(){
		
	
		//Rotue de 5 jonctions
		Jonction j1 = new Jonction(); //depart
		Jonction j2 = new Jonction(); //deuxieme
		Jonction j3 = new Jonction(); //carrefour
		
		Jonction j4 = new Jonction(); //embranchement carrefour
		Jonction j5 = new Jonction(); //embranchement carrefour
		Jonction j6 = new Jonction(); //embranchement carrefour
		
		Jonction j7 = new Jonction(); //voisin de J6
		
		jonctions.add(j1);
		jonctions.add(j2);
		jonctions.add(j3);
		jonctions.add(j4);
		jonctions.add(j5);
		jonctions.add(j6);
		jonctions.add(j7);

		//ArrayList des elem du carrefour
		ArrayList<Jonction> jonctionsRoute = new ArrayList<Jonction>();
		jonctionsRoute.add(j4);
		jonctionsRoute.add(j5);
		jonctionsRoute.add(j6);

		/*CREATION RESEAU*/
		
		j2.addVoisin(j1); //creation lien entre j1 et j2
		j2.addVoisin(j3); //creation lien entre j2 et j3
		
		
		System.out.println("Voisins de J1 ! \n --------------------------");

		j1.afficherJonction();
		
		System.out.println("\nVoisins de J1 fini ! \n-----------------------------");
		
		System.out.println("Voisins de J2 ! \n --------------------------");

		j2.afficherJonction();
		
		System.out.println("\nVoisins de J2 fini ! \n-----------------------------");
				
		System.out.println("Voisins de J3, un carrefour ! \n --------------------------");

		j3.addVoisin(jonctionsRoute); //creation lien entre j3 et j4, j5, j6
		j3.afficherJonction();
		System.out.println("\nVoisins de J3 fini ! \n-----------------------------");

		
		j6.addVoisin(j7); //creation lien entre j6 et j7
		
		System.out.println("Voisins de J6 ! \n --------------------------");

		j6.afficherJonction();
		
		System.out.println("\nVoisins de J6 fini ! \n-----------------------------");
		
		System.out.println("Voisins de J7 ! \n --------------------------");

		j7.afficherJonction();
		
		System.out.println("\nVoisins de J7 fini ! \n-----------------------------");
		
		
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
