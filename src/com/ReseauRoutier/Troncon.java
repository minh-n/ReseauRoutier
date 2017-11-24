package com.ReseauRoutier;

import com.Regulation.*;

public class Troncon {

	private int id;
	private Capteur capteur;
	private Semaphore semaphore;
	

	public Troncon()
	{
	}
	
	public Troncon(int id)
	{
		setId(id);
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
}
