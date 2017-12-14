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

		System.out.println("\nEntrez 1 pour le test simple, 2 pour le test complexe, 3 pour sortir du programme :");
		String entry = scanner.nextLine();
		
		System.out.println("Entry = " + entry.toString());
		while((entry.compareTo("1") == 0) || (entry.compareTo("2") == 0))
		{
			if(entry.compareTo("1") == 0){
				System.out.println("LANCEMENT DU TEST SIMPLE");			
				t.test();
			}
			else
			{
				System.out.println("LANCEMENT DU TEST COMPLEXE");
				t2.test();
			}
			entry = scanner.nextLine();
		}

		scanner.close();

		System.out.println("**********################ FIN DU PROGRAMME ###############*********");
	}

}
