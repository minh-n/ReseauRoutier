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
		

		
	}
	
	
	

}
