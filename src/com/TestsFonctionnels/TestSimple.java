package com.TestsFonctionnels;

import com.Regulation.CapteurPresence;
import com.Regulation.CapteurVitesse;
import com.Regulation.Panneau;
import com.ReseauRoutier.Reseau;
import com.ReseauRoutier.Voiture;

public class TestSimple {

	

	public TestSimple()
	{
		
	}
	
	public void test()
	{
		Reseau resal = new Reseau();
		resal.initReseauSimple();
		Voiture v = new Voiture(3, 1); // (vmax, sens)

		resal.insererVoiture(v, 1);
	
				
		System.out.println("AFFICHAGE INITIAL DES VOITURES ! --------------------------------------\n\n");		
		
		resal.affichageVoitures();

		System.out.println(" AFFICHAGE INITIAL TERMINE ! -------------------------------------------\n\n");
		resal.iteration();
		resal.affichageVoitures();

		resal.iteration();
		resal.affichageVoitures();

		resal.iteration();
		resal.affichageVoitures();
		
		resal.iteration();
		resal.affichageVoitures();
		
		resal.iteration();
		resal.affichageVoitures();
		
		resal.iteration();
		resal.affichageVoitures();

		resal.iteration();
		resal.affichageVoitures();
		

		resal.iteration();
		resal.affichageVoitures();
		

		resal.iteration();
		resal.affichageVoitures();

		resal.iteration();
		resal.affichageVoitures();

		resal.iteration();
		resal.affichageVoitures();
		
		
	}
	
}
