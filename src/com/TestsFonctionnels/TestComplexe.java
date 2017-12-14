package com.TestsFonctionnels;

import java.util.Scanner;

import com.Regulation.CapteurPresence;
import com.Regulation.CapteurVitesse;
import com.ReseauRoutier.Reseau;
import com.ReseauRoutier.Voiture;

public class TestComplexe {

	
	public TestComplexe(){
		
	}
	
	public void test()
	{
		Reseau res = new Reseau();
		res.initReseau();
				
		CapteurVitesse c = new CapteurVitesse(1, 0);
		CapteurVitesse c2 = new CapteurVitesse(1, 1);
		CapteurPresence c3 = new CapteurPresence(2, 0);
		CapteurPresence c4 = new CapteurPresence(2, 1);
//		Panneau p = new Panneau(2, 0, 2);
//		Panneau p2 = new Panneau(1, 0, 2);

		Voiture v = new Voiture(3, 0); // (vmax, sens). Le sens peut etre 0 (sens0) ou un entier quelconque (sens1). 
		Voiture v2 = new Voiture(5, 0);
		Voiture v3 = new Voiture(4, 1);
		Voiture v4 = new Voiture(10, 1);

		res.insererVoiture(v, 1);
		res.insererVoiture(v2, 2);
		res.insererVoiture(v3, 2);
		res.insererVoiture(v4, 4);

		res.insererCapteur(c, 1);
		res.insererCapteur(c2, 4);
		res.insererCapteur(c3, 5);
		res.insererCapteur(c4, 6);
//		res.insererCapteur(p, 2);
//		res.insererCapteur(p2, 1);
				
		System.out.println("AFFICHAGE INITIAL DES VOITURES ! --------------------------------------\n\n");		
		
		res.affichageVoitures();

		System.out.println(" AFFICHAGE INITIAL TERMINE ! -------------------------------------------\n\n");
		
		Scanner scanner = new Scanner(System.in);

		System.out.println("\nPour afficher une iteration du programme, appuyez sur la touche 1 puis entrez :");
		String entry = scanner.nextLine();
		int i = 0;
		
		while((entry.compareTo("1") == 0))
		{
			System.out.println("************ITERATION numero " + i + " !************\n");
			res.iteration();
			System.out.println("************AFFICHAGE DES VOITURES !************");

			res.affichageVoitures();
			System.out.println("\nProchaine iteration ? Entrez 1 :");

			entry = scanner.nextLine();
			System.out.println("\n*********FIN DE L'ITERATION numero " + i + " !*********");
			i++;

		}

		scanner.close();

	}
}
