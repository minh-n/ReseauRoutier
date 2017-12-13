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
		
		while(!entry.equals(1) || !entry.equals(2))
		{
			if(!entry.equals(1)){t.test();}
			else{t2.test();}
			entry = scanner.nextLine();
		}

		
		scanner.close();

		System.out.println("**********################ FIN DU PROGRAMME ###############*********");
	}

}
