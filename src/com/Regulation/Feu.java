package com.Regulation;

public abstract class Feu{
	
	private int sens;
	private CouleurFeu couleur;
	
	public Feu(int s)
	{
		this.sens = s;
	}
	
	public abstract void changerCouleur(CouleurFeu couleur);

	public int getSens() {
		return sens;
	}

	public void setSens(int sens) {
		this.sens = sens;
	}

	public CouleurFeu getCouleur() {
		return couleur;
	}

	public void setCouleur(CouleurFeu couleur) {
		this.couleur = couleur;
	}

}
