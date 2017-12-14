package com.TestsFonctionnels;

import java.util.Scanner;

import com.Regulation.CapteurPresence;
import com.Regulation.CapteurVitesse;
import com.Regulation.PanneauLimitationV;
import com.Regulation.RegSimple;
import com.ReseauRoutier.JonctionReg;
import com.ReseauRoutier.Reseau;
import com.ReseauRoutier.Voiture;

public class TestSimple {

	

	public TestSimple()
	{
		
	}
	
	public void test()
	{

		Reseau res = new Reseau();
		res.initReseauSimple();
		
		CapteurPresence c = new CapteurPresence(0, 1); //(position, sens)
		
		Voiture v = new Voiture(3, 1); // (vmax = 3, sens = 1)

		res.insererCapteur(c, 3);
		
		PanneauLimitationV p = new PanneauLimitationV(1, 2, res.getRoute(1));
		PanneauLimitationV p2 = new PanneauLimitationV(0, 5, res.getRoute(2));
		PanneauLimitationV p3 = new PanneauLimitationV(1, 3, res.getRoute(4));
		
//		RegSimple r = new RegSimple(((JonctionReg) res.getJonction(3)));
//		res.addObserver(r);

		res.getRoute(1).ajoutSemaphore(p);
		res.getRoute(2).ajoutSemaphore(p2);
		res.getRoute(4).ajoutSemaphore(p3);
		
		
		
		res.insererVoiture(v, 1);		

		System.out.println("AFFICHAGE INITIAL DES VOITURES ! --------------------------------------\n\n");		
		
		res.affichageVoitures();

		System.out.println(" AFFICHAGE INITIAL TERMINE ! -------------------------------------------\n\n");

		
		boolean continuer = true;
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nPour afficher une iteration du programme, appuyez sur la touche 1 puis entrez :");
		String entry = scanner.nextLine();
	
		int i = 0;
		
		while(continuer)
		{
			switch(entry)
			{
			case "1":
				System.out.println("************ITERATION numero " + i + " !************");
				
				res.iteration();
				res.affichageVoitures();
				i++;

				System.out.println("\nProchaine iteration ? Entrez 1 pour continuer ou un autre charactere pour quitter :");

				entry = scanner.nextLine();
				break;

			default:
				System.out.println("Quitter le Test Simple ? Y/N");
				entry = scanner.nextLine();

				switch(entry)
				{
				case "N":
				case "n":
					System.out.println("\nPour afficher une iteration du programme, appuyez sur la touche 1 puis entrez :");
					break;
				default:
					System.out.println("Reponse " + entry + "detectee. Fin du Test Simple...");

					continuer = false;
					break;
				}
				break;

			}
		
		

		}
		scanner.close();
	}
	
}
