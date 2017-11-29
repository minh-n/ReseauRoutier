package com.TestsFonctionnels;
import com.ReseauRoutier.*;
import com.Regulation.*;

public class Test {
	
	
	public Test()
	{
		
	}
	
	public static void main(String[] args) {
			
		Reseau r = new Reseau();
		r.initReseau();
		
		System.out.println("*******____\\\\\\\\\\FIN DE LA CREATION DU RESEAU////////_______*************\n\n\n\n\n\n"
				+ "\n\n\n\nAJOUT VOITURES --------------------- \n");
		
		Voiture v = new Voiture(2);
		Voiture v2 = new Voiture(3);
		Voiture v3 = new Voiture(1);
		
		r.getJonctions().get(0).getVoisins().get(0).getSegment().ajoutVoiture(v);	
		r.getJonctions().get(0).getVoisins().get(0).getSegment().ajoutVoiture(v2);	
		r.getJonctions().get(0).getVoisins().get(0).getSegment().ajoutVoiture(v3);	

		System.out.println("Voiture 1 ajoutee sur la route :");
		System.out.println(v.getRouteActuelle().toString());
		

		System.out.println("\nVoiture 2 ajoutee sur la route :");
		System.out.println(v2.getRouteActuelle().toString());
		

		System.out.println("\nVoiture 3 ajoutee sur la route :");
		System.out.println(v3.getRouteActuelle().toString());
		
		System.out.println("\n\n***********************************\n----------------------\nPosition de la voiture 1 :");
		System.out.println("Troncon no : " + v.getTronconActuel().getId());
		
		System.out.println("----------------------\nPosition de la voiture 2 :");
		System.out.println("Troncon no : " + v2.getTronconActuel().getId());	
		
		System.out.println("----------------------\nPosition de la voiture 3 :");
		System.out.println("Troncon no : " + v3.getTronconActuel().getId());
		
		
		
		
		//TODO gerer les collisions
		
		System.out.println("*****************************\nBOUGER (non fonctionnel)");
		System.out.println("\nOn avance de vitesse = 2");
		v.avancer();
		
		System.out.println("Position de la voiture :");
		System.out.println("Troncon no : " + v.getTronconActuel().getId());
		
		System.out.println("\nOn avance de vitesse = 2");
		v.avancer();
		
		System.out.println("Position de la voiture :");
		System.out.println("Troncon no : " + v.getTronconActuel().getId());
		
		
	}

}
