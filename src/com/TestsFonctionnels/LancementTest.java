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
		
		
		Scanner scanner = new Scanner(System.in);
		boolean cont = true;

		System.out.println("\nEntrez 1 pour le test simple, 2 pour le test complexe, 3 pour sortir du programme :");
		String input = scanner.nextLine();
		
		System.out.println("Entry = " + input.toString());
		
		
		while(cont)
		{
			switch(input)
			{
			case "1":
				System.out.println("LANCEMENT DU TEST SIMPLE" + cont + input);			
				t.test();
				System.out.println("FIN DU TEST SIMPLE. Continuer/Entry" + cont + input);
				break;
				
			case "2": 
				System.out.println("LANCEMENT DU TEST COMPLEXE");
				t2.test();

				break;
				
			default:
				System.out.println("Fin du prog TESTTESTSETSET");
//				System.out.println("Quitter le programme ? Y/N");
//				input = scanner.nextLine();
//
//				switch(input)
//				{
//				case "n":
//				case "N":
//					System.out.println("\nEntrez 1 pour le test simple, 2 pour le test complexe, 3 pour sortir du programme :");
//					break;
//				default:
//					System.out.println("Reponse " + input + "detectee. Fin du programme...");
//					cont = false;
//					break;
//				}
//				input = scanner.nextLine();
				
				break;
			}
			
//			if(entry.compareTo("1") == 0){
//
//			}
//			else
//			{
//				System.out.println("LANCEMENT DU TEST COMPLEXE");
//				t2.test();
//			}
		}

		scanner.close();

		System.out.println("**********################ FIN DU PROGRAMME ###############*********");
	}

}
