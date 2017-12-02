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

		int distanceRestante = 0;
		System.out.println("Iteration des voitures partant de la jonction " + this.getId());
		
		for (Voisin voisinActuel : voisins)
		{
			for (Voiture voit : voisinActuel.getSegment().getMesVoitures())
			{

				distanceRestante = segmentSuffisant(voit, voisinActuel.getSegment());
				if ( distanceRestante < 1)
				{
					//on incremente seulement si la distance restante est suffisante
					System.out.println("On bouge la voiture " + voit.getId());

					if (voit.getSens() == 1)
					{
						incrementerTroncon(voisinActuel.getSegment(),voit);
					}
					else
					{
						decrementerTroncon(voisinActuel.getSegment(),voit);
					}
				}
				
				else
				{
					System.out.println("On passe au segment suivant !");
					voitureSegmentSuivant.add(voit);
					System.out.println("Cette voiture a actuellement la position : " + voit.getTronconActuel().getId());

					//get(0) est la route qui revient au voisinActuel. On prend donc get(1)
					
					//get(1) temporaire, on prendra un random entre 1 et getVoisins().size
					//voisinActuel.getSegment().getMesVoitures().remove(voit);
				}

			}
		}
		if ((voitureSegmentSuivant.isEmpty() == false))
		{
			for (Voiture voit : voitureSegmentSuivant)
			{		
				for (Voisin voisinActuel : voisins)
				{
					if(voisinActuel.getSegment().getMesVoitures().contains(voit))
					{
						voisinActuel.getSegment().suppressionVoiture(voit);
						if (voisinActuel.getJonction().getVoisins().size() <= 1)
						{
							System.out.println("FIN DE LA ROUTE!");
						}
						else
						{
							segmentSuivant(voit, voisinActuel.getJonction().getVoisins().get(1)); 
							System.out.println("On met la voiture " + voit.getId() + " dans le segment " + voisinActuel.getJonction().getVoisins().get(1).getSegment().getId());
						}
					}
				}
			}
		}
	}
	
	
	
	/**
	 * Indique s'il reste assez de place sur ce segment pour deplacer la voiture.
	 * @param v
	 * @return distanteRestante si elle n'est pas suffisante, -1 sinon.
	 */
	public int segmentSuffisant(Voiture v, SegmentDeRoute s)
	{
		int distanceRestante = s.getLongueur() - v.getTronconActuel().getId();
		
		if(v.getVitesse() >= distanceRestante)
		{
			System.out.println("\n--Pour la voiture " + v.getId() + " => Segment trop court !\n");
			return distanceRestante;
		}
		return -1;
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

		suivant.getSegment().ajoutVoiture(v, suivant.getSens1());
		
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
