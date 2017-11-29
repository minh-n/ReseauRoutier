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

	public Capteur getCapteur() {
		return capteur;
	}

	public void setCapteur(Capteur capteur) {
		this.capteur = capteur;
	}

	public Semaphore getSemaphore() {
		return semaphore;
	}

	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
	}
}
