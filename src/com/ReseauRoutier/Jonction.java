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
	 * Parcours toutes les voitures ayant pour depart cette jonction
	 * et incremente leur position selon leur vitesse.
	 * 
	 */	
	public void deplacerVoiture()
	{
		
		ArrayList<Voiture> voitureSegmentSuivant = new ArrayList<Voiture>();
		ArrayList<Voisin> voisinSuivant = new ArrayList<Voisin>();

		System.out.println("Iteration des voitures partant de la jonction " + this.getId());
		for (Voisin voisinActuel : voisins)
		{
			for (Voiture voit : voisinActuel.getSegment().getMesVoitures())
			{

				if (segmentSuffisant(voit, voisinActuel.getSegment()))
				{
					System.out.println("On bouge la voiture " + voit.getId());

					if (voit.getSens() == 1)
					{
						decrementerTroncon(voisinActuel.getSegment(),voit);
					}
					
					else
					{
						incrementerTroncon(voisinActuel.getSegment(),voit);
					}
				}
				
				else
				{
					System.out.println("On passe au segment suivant !");
					voitureSegmentSuivant.add(voit);
					if(voisinActuel.getJonction().getVoisins().size() > 0) //on exclut le cas de la barriere
					{
						voisinSuivant.add(voisinActuel.getJonction().getVoisins().get(1));
					}
					else
					{
						voisinSuivant.add(voisinActuel.getJonction().getVoisins().get(0));

					}
					//get(0) est la route qui revient au voisinActuel. On prend donc get(1)
					
					//get(1) temporaire, on prendra un random entre 1 et getVoisins().size
					//voisinActuel.getSegment().getMesVoitures().remove(voit);
				}

			}
		}
		if (!voitureSegmentSuivant.isEmpty() && !voisinSuivant.isEmpty())
		{
			for (Voiture voit : voitureSegmentSuivant)
			{		
				for (Voisin voisinActuel : voisins)
				{
					voisinActuel.getSegment().suppressionVoiture(voit);
				}
				
			segmentSuivant(voit, voisinSuivant.get(0)); 
			}
		}

	}
	
	
	
	/**
	 * Indique s'il reste assez de place sur ce segment pour deplacer la voiture.
	 * @param v
	 * @return
	 */
	public boolean segmentSuffisant(Voiture v, SegmentDeRoute s)
	{
		int distanceRestante = s.getLongueur() - v.getTronconActuel().getId();
		
		if(v.getVitesse() >= distanceRestante)
		{
			System.out.println("\n--Pour la voiture " + v.getId() + " => Segment trop court !\n");
			return false;
		}
		return true;
	}
	
	/**
	 * deplace une voiture de la fin de ce segment vers le segment suivant
	 * @param v la voiture a deplacer
	 * @return true s'il reste de la place sur le segment de route suivant
	 * @return false sinon.
	 */
	public boolean segmentSuivant(Voiture v, Voisin suivant)
	{
		//voisinActuel.getSegment().suppressionVoiture(v);

		suivant.getSegment().ajoutVoiture(v);
		return false;
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
