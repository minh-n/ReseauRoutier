package com.ReseauRoutier;

import com.Regulation.*;

public class Troncon {

	private static int t_id = 0;
	private int id;
	private Capteur capteur;

	public Troncon()
	{
		setId(t_id);
		t_id +=1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString()
	{
		return "Troncon id = " + id;
	}

	public Capteur getCapteur() {
		return capteur;
	}

	public void setCapteur(Capteur capteur) {
		this.capteur = capteur;
	}


	public static int getT_id() {
		return t_id;
	}

	public static void setT_id(int t_id) {
		Troncon.t_id = t_id;
	}
}
