package com.ReseauRoutier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Jonction extends ElementRoute {
	
	private static int j_id = 1;
	private int id;
	private HashMap<Jonction, SegmentDeRoute> aiguillage;
	
	public Jonction()
	{
		aiguillage = new HashMap<>();
		setId(j_id);
		j_id+= 1;
	}

	public Jonction(ArrayList<Jonction> voisins)
	{
		aiguillage = new HashMap<>();
		setId(j_id);
		j_id+= 1;
		
		//on cree un segment de route qui connecte cette jonction (this)
		//et un des voisins (j)
		for (Jonction j:voisins){
			SegmentDeRoute nouvSegment = new SegmentDeRoute(1, 10);
			aiguillage.put(j, nouvSegment);
			j.getAiguillage().put(this, nouvSegment);
		}
	}
	
	
	public void addVoisin(Jonction voisin)
	{
		SegmentDeRoute nouvSegment = new SegmentDeRoute(1, 10);
		

		
		aiguillage.put(voisin, nouvSegment);
		System.out.println("segment créé ! ");

		SegmentDeRoute a = aiguillage.get(voisin);
		System.out.println("a = ");
		a.afficherSegment();
		

		//on cree un segment de route qui connecte cette jonction (this)
		//et le voisin voisin
		voisin.getAiguillage().put(this, nouvSegment);
	}


	public HashMap<Jonction, SegmentDeRoute> getAiguillage() {
		return aiguillage;
	}

	public void setAiguillage(HashMap<Jonction, SegmentDeRoute> aiguillage) {
		this.aiguillage = aiguillage;
	}

	/**-
	 * STACK OVERFLOW pour certaines raisons.
	 */
	@Override
	public String toString() {
		return aiguillage.toString();
	}
	

	// NE MARCHE PAS !!!!!!!!!!! on ne peut pas afficher entry ou stack overflow 
	
	public void afficherJonction() {
		System.out.println("Jonction n°" + id +"\n"
				+ "Voisin 1 : " );
		
		Iterator<HashMap.Entry<Jonction, SegmentDeRoute>> it = aiguillage.entrySet().iterator();
		while (it.hasNext()) {
			  HashMap.Entry<Jonction, SegmentDeRoute> entry = it.next();

			
			}
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
