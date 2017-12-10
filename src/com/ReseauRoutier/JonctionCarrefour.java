package com.ReseauRoutier;

public class JonctionCarrefour extends Jonction{

	/*
	 @Override
	    public Segment segSuivant(Vehicule v) throws ErreurModele 
	    {
	        if (sesAcces.size() == 1 && sesAcces.get(0) == v.getSaRoute()) 
	        {
	        	throw new ErreurModele("Le seul segment suivant possible est le segment actuel.");
	        }//Pour eviter de tomber dans une boucle infnie

	        int indice;

	        do 
	        {
	            Random aleatoire = new Random();
	            indice = aleatoire.nextInt(sesAcces.size() - 1);
	        }
	        while (sesAcces.get(indice) == v.getRoutePrec()); //On cherche un segment aleatoirement, mais different du segment actuel sur lequel se trouve la voiture

	        return sesAcces.get(indice);
	    }
*/
	
	
		@Override
		public String toString() 
		{
			//for(Voisin v : voisins) .... 
			
			return "";
		}	
	
}
