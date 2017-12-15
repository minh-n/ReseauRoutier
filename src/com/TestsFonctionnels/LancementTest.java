package com.TestsFonctionnels;

import java.util.Scanner;

public class LancementTest {
		
	public LancementTest()
	{
		
	}
	
	public static void main(String[] args) {
				
		System.out.println("Bienvenue dans le programme de simulation routiere. \n");
		
		TestSimple t = new TestSimple();
		TestComplexe t2 = new TestComplexe();
		TestCarrefour t3 = new TestCarrefour();

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\nEntrez 1 pour le test simple,\n2 pour le test complexe, 3 pour le test carrefour (feux),\nun autre caractere pour sortir du programme :");
		
		String input = scanner.nextLine();
		
		System.out.println("Entry = " + input.toString());
		
		switch(input)
		{
			case "1":
				System.out.println("LANCEMENT DU TEST SIMPLE");			
				t.test();
				break;
				
			case "2": 
				System.out.println("LANCEMENT DU TEST COMPLEXE");
				t2.test();
				break;
		
			case "3": 
				System.out.println("LANCEMENT DU TEST CARREFOUR");
				t3.test();
				break;
				
			default:
				System.out.println("Fin du programme.");
				break;
		}

		scanner.close();

		System.out.println("**********################ FIN DU PROGRAMME ###############*********");
	}

}