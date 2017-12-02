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
		Voiture v2 = new Voiture(6);
		Voiture v3 = new Voiture(10);
		
		r.getJonctions().get(0).getVoisins().get(0).getSegment().ajoutVoiture(v,1);	
		r.getJonctions().get(0).getVoisins().get(0).getSegment().ajoutVoiture(v2,1);	
		r.getJonctions().get(0).getVoisins().get(0).getSegment().ajoutVoiture(v3,1);	

		System.out.println("Voiture 1 ajoutee sur la route :");
		System.out.println(v.getRouteActuelle().toString());
		

		System.out.println("\nVoiture 2 ajoutee sur la route :");
		System.out.println(v2.getRouteActuelle().toString());
		

		System.out.println("\nVoiture 3 ajoutee sur la route :");
		System.out.println(v3.getRouteActuelle().toString());
		
		System.out.println("\n\n***********************************\n----------------------\nPosition de la voiture 1 :");
		
		affichagePosition(v);
		affichagePosition(v2);
		affichagePosition(v3);
		
		voituresPresentes(r.getJonctions().get(0));
		voituresPresentes(r.getJonctions().get(1));
		voituresPresentes(r.getJonctions().get(2));

		
		
		/**
		 * DEPLACMENTS
		 * 
		 * 
	
		 */
		
		//TODO gerer les collisions
		
		System.out.println("*****************************\nBOUGER (non fonctionnel, faites pas gaffe au sens)");
		System.out.println("++++++++++++++++PREMIERE ITERATION++++++++++++++++\n");

		r.getJonctions().get(0).deplacerVoiture();
		voituresPresentes(r.getJonctions().get(0));
		voituresPresentes(r.getJonctions().get(1));
		voituresPresentes(r.getJonctions().get(2));

		affichagePosition(v);
		affichagePosition(v2);
		affichagePosition(v3);

		System.out.println("\n");
		
		System.out.println("++++++++++++++++DEUXIEME ITERATION++++++++++++++++\n");

		r.getJonctions().get(0).deplacerVoiture();
		voituresPresentes(r.getJonctions().get(0));
		voituresPresentes(r.getJonctions().get(1));
		voituresPresentes(r.getJonctions().get(2));
		
		affichagePosition(v);
		affichagePosition(v2);
		affichagePosition(v3);

		System.out.println("\n");
		
		System.out.println("++++++++++++++++TROISIEME ITERATION++++++++++++++++\n");

		r.getJonctions().get(0).deplacerVoiture();
		voituresPresentes(r.getJonctions().get(0));
		voituresPresentes(r.getJonctions().get(1));
		voituresPresentes(r.getJonctions().get(2));

		affichagePosition(v);
		affichagePosition(v2);
		affichagePosition(v3);

		System.out.println("\n");
		
		System.out.println("++++++++++++++++QUATRIEME ITERATION++++++++++++++++\n");

		r.getJonctions().get(0).deplacerVoiture();
		//r.getJonctions().get(1).deplacerVoiture();
		//r.getJonctions().get(2).deplacerVoiture();

		//r.getJonctions().get(2).deplacerVoiture(); pour certaines raisons ca fait un indexoutofbound exception

		voituresPresentes(r.getJonctions().get(0));
		voituresPresentes(r.getJonctions().get(1));
		voituresPresentes(r.getJonctions().get(2));

		//....
		
		/**TODO
		 * BUG : pour certaines raisons,
		 * quand on deplace les voitures de la jonction 1, celles de la jonction 0 le sont aussi
		 * possiblement : la suppression se fait pas  
		 */

		affichagePosition(v);
		affichagePosition(v2);
		affichagePosition(v3);
		
		System.out.println("\n");
		
		
		System.out.println("++++++++++++++++CINQIUEME ITERATION++++++++++++++++\n");

		r.getJonctions().get(0).deplacerVoiture();
		voituresPresentes(r.getJonctions().get(0));
		voituresPresentes(r.getJonctions().get(1));
		voituresPresentes(r.getJonctions().get(2));

		affichagePosition(v);
		affichagePosition(v2);
		affichagePosition(v3);

		System.out.println("\n");
		
	}
	
	
	
	public static void affichagePosition(Voiture v)
	{
		System.out.println("Position de la voiture " + v.getId() + " : ");

		System.out.println("Segment no : " + v.getRouteActuelle().getId() + "; Troncon no : " + v.getTronconActuel().getId());
		
	}
	
	public static void voituresPresentes(Jonction j)
	{
		System.out.println("Voici les voitures provenant ou allant vers j" + j.getId() + ".");
		for (Voisin v : j.getVoisins())
		{
			for (Voiture voit : v.getSegment().getMesVoitures())
			{
				System.out.println("Voiture " + voit.getId());
			}
		}
	}

}
