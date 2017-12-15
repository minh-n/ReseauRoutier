package com.TestsFonctionnels;

import java.util.Scanner;

import com.Regulation.CapteurPresence;
import com.Regulation.PanneauLimitationV;
import com.ReseauRoutier.Reseau;
import com.ReseauRoutier.Voiture;

public class TestCarrefour {

	
	
	public TestCarrefour()
	{
		
	}
	
	public void test()
	{

		Reseau res = new Reseau();
		res.initReseauCarrefour();

		Voiture v = new Voiture(2, 1); // (vmax = 3, sens = 1)
		Voiture v2 = new Voiture(2, 1); // (vmax = 3, sens = 1)
		Voiture v3 = new Voiture(2, 1); // (vmax = 3, sens = 1)
		Voiture v4 = new Voiture(3, 1); // (vmax = 3, sens = 1)
		Voiture v5 = new Voiture(3, 1); // (vmax = 3, sens = 1)


		res.insererVoiture(v, 1);		
		res.insererVoiture(v2, 1);		
		res.insererVoiture(v3, 2);		
		res.insererVoiture(v4, 3);	
		res.insererVoiture(v5, 4);		


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
