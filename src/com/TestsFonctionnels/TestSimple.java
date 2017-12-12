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
		resal.initReseau();
		
		//System.out.println("Réseau créé \n" + r.toString());
		
		CapteurVitesse c = new CapteurVitesse(1, 0);
		CapteurVitesse c2 = new CapteurVitesse(1, 1);
		CapteurPresence c3 = new CapteurPresence(2, 0);
		CapteurPresence c4 = new CapteurPresence(2, 1);
		
		Panneau p = new Panneau(2, 0, 2);
		Panneau p2 = new Panneau(1, 0, 2);
		
		Voiture v = new Voiture(3, 0); // (vmax, sens)
		Voiture v2 = new Voiture(5, 0);
		Voiture v3 = new Voiture(4, 1);
		
		resal.insererVoiture(v, 1);
		resal.insererVoiture(v2, 2);
		resal.insererVoiture(v3, 2);
		resal.insererCapteur(c, 1);
		resal.insererCapteur(c2, 1);
		resal.insererCapteur(c3, 2);
		resal.insererCapteur(c4, 2);
		resal.insererCapteur(p, 2);
		resal.insererCapteur(p2, 1);
		
		resal.iteration();
		resal.iteration();
		resal.iteration();
		resal.affichageVoitures();
	}
	
}
