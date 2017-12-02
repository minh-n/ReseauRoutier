package com.ReseauRoutier;

import java.util.ArrayList;

public class Jonction extends ElementRoute {
	
	private static int j_id = 1;
	private int id;
	private ArrayList<Voisin> voisins = new ArrayList<Voisin>();
	
	public Jonction()
	{
		setId(j_id);
		j_id+= 1;
	}

	/**
	 * ajout d'un voisin
	 * @param j
	 */
	public void addVoisin(Jonction j)
	{
		SegmentDeRoute nouvSegment = new SegmentDeRoute(5, 20);
		Voisin v = new Voisin(j, nouvSegment, 1, 2);
		this.voisins.add(v);
		j.getVoisins().add(new Voisin(this, nouvSegment, 2, 1));
	}
	
	
	/**
	 * ajout de plusieurs voisins contenus dans une liste
	 * @param jonctions
	 */
	public void addVoisin(ArrayList<Jonction> jonctions)
	{
		for (Jonction j:jonctions){
			SegmentDeRoute nouvSegment = new SegmentDeRoute(1, 10);
			Voisin v = new Voisin(j, nouvSegment, 1, 2);
			voisins.add(v);
			j.getVoisins().add(new Voisin(this, nouvSegment, 2, 1));
		}
	}

	public void afficherJonction() {
		System.out.println("Jonction n" + id);
		for (Voisin v : this.voisins){
			System.out.println(v.toString());
		}
	}
	
	/**
	 * Deplace les voitures du reseau selon leur sens
	 * 
	 */	
	public void deplacerVoiture()
	{
		
		
		for (Voisin v : voisins)
		{
			for (Voiture voit : v.getSegment().getMesVoitures())
				
			if (voit.getSens() == 1)
			{
				incrementerTroncon(v.getSegment(),voit);
			}
			
			else
			{
				decrementerTroncon(v.getSegment(),voit);
			}
		}
		
	
	}
	
	
	
	
	
	public void decrementerTroncon(SegmentDeRoute s, Voiture v)
	{
		v.setTronconActuel(s.getTroncons().get(v.getTronconActuel().getId() - v.getVitesse())); 
	}
	
	
	public void incrementerTroncon(SegmentDeRoute s, Voiture v)
	{
		v.setTronconActuel(s.getTroncons().get(v.getTronconActuel().getId() + v.getVitesse())); 
	}
	
	//TODO
	
	/*
	 * 
	 * d = depart+n
	 * if d > max(troncontailleMax)
	 * 
	 * on prend la distance entre depart et tronconTailleMax, et on l'enleve
	 * de d.
	 * 
	 * le reste on enleve 1 (jonction) et on le met sur la route suivante.
	 * ne pas oublier de update la voiture avec la nouvelle route 
	 * et les nouvelles jonctions (how? jsp)
	 * 
	 * 
	 * 
	 */
	
	
	/**
	 * TODO : conditions de jonction.
	 * 
	 * si jonction.getVoisins().size() == 1 c'est une barriere. Demi tour
	 * 
	 * si jonction.getVoisin().size() > 2 c'est un carrouf
	 * dans ce cas, on get un voisin random entre 0 et voisin.size();

	 * 
	 */
	
	
	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Voisin> getVoisins() {
		return voisins;
	}

	public void setVoisins(ArrayList<Voisin> voisins) {
		this.voisins = voisins;
	}
	
	
}
