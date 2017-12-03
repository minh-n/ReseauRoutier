package com.Regulation;

public enum CouleurFeu {
	
	Rouge("Rouge"),
	Vert("Vert"),
	Orange("Orange");

	private String couleur;
	
	CouleurFeu(String c)
	{
		this.setCouleur(c);
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	@Override
	public String toString()
	{
		return couleur;
	}
}
