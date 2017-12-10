package com.TestsFonctionnels;
import com.ReseauRoutier.*;
import com.Regulation.*;

public class Test {
	
	
	public Test()
	{
		
	}
	
	public static void main(String[] args) {
			
		Reseau resal = new Reseau();
		resal.initReseau();
		
		//System.out.println("Réseau créé \n" + r.toString());
		
		CapteurVitesse c = new CapteurVitesse(1, 0);
		CapteurVitesse c2 = new CapteurVitesse(1, 1);
		CapteurVitesse c3 = new CapteurVitesse(2, 0);
		CapteurVitesse c4 = new CapteurVitesse(2, 1);
		
		Voiture v = new Voiture(3, 0); // (vmax, sens)
		Voiture v2 = new Voiture(5, 0);
		Voiture v3 = new Voiture(4, 1);
		
		v.addObserver(c);
		v.addObserver(c2);
		v.addObserver(c3);
		v.addObserver(c4);
		
		resal.insererVoiture(v, 1);
		resal.insererVoiture(v2, 2);
		resal.insererVoiture(v3, 2);
		resal.insererCapteur(c, 1);
		resal.insererCapteur(c2, 1);
		resal.insererCapteur(c3, 2);
		resal.insererCapteur(c4, 2);
		resal.iteration();
		resal.iteration();
		resal.iteration();
		resal.affichageVoitures();
	}
	
	
	

}
