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
		Voiture v2 = new Voiture(4);
		Voiture v3 = new Voiture(5);
		
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
		System.out.println("Segment no : " + v.getRouteActuelle() + "; Troncon no : " + v.getTronconActuel().getId());
		
		System.out.println("----------------------\nPosition de la voiture 2 :");
		System.out.println("Segment no : " + v2.getRouteActuelle() + "; Troncon no : " + v2.getTronconActuel().getId());
		
		System.out.println("----------------------\nPosition de la voiture 3 :");
		System.out.println("Segment no : " + v3.getRouteActuelle() + "; Troncon no : " + v3.getTronconActuel().getId());
		
		
		
		//TODO gerer les collisions
		
		System.out.println("*****************************\nBOUGER (non fonctionnel, faites pas gaffe au sens)");
	
		
		System.out.println("++++++++++++++++PREMIERE ITERATION++++++++++++++++\n");

		r.getJonctions().get(0).deplacerVoiture();

	
		affichagePosition(v);
		affichagePosition(v2);
		affichagePosition(v3);

		System.out.println("\n");

		
		
		System.out.println("++++++++++++++++DEUXIEME ITERATION++++++++++++++++\n");

		r.getJonctions().get(0).deplacerVoiture();
		
		
		affichagePosition(v);
		affichagePosition(v2);
		affichagePosition(v3);

		System.out.println("\n");

		
		
		
		System.out.println("++++++++++++++++TROISIEME ITERATION++++++++++++++++\n");

		r.getJonctions().get(0).deplacerVoiture();
		r.getJonctions().get(1).deplacerVoiture();
		
		
		affichagePosition(v);
		affichagePosition(v2);
		affichagePosition(v3);

		System.out.println("\n");

		
		
		
		
		System.out.println("++++++++++++++++QUATRIEME ITERATION++++++++++++++++\n");

		r.getJonctions().get(0).deplacerVoiture();
		r.getJonctions().get(1).deplacerVoiture();
		r.getJonctions().get(2).deplacerVoiture();

		System.out.println("\n");


		
		
		
		
		
	}
	
	
	
	public static void affichagePosition(Voiture v)
	{
		System.out.println("Position de la voiture " + v.getId() + " : ");

		System.out.println("Segment no : " + v.getRouteActuelle().getId() + "; Troncon no : " + v.getTronconActuel().getId());
		
	}

}
