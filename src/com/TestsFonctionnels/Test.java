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
		
		//Voiture v = new Voiture(3, 0); // (vmax, sens)
		Voiture v2 = new Voiture(5, 0);
		//Voiture v3 = new Voiture(4, 1);
		
		//resal.insérerVoiture(v, 1);
		resal.insérerVoiture(v2, 2);
		//resal.insérerVoiture(v3, 2);
		resal.iteration();
		resal.iteration();
		resal.iteration();
		resal.affichageVoitures();
	}
	
	
	

}
