package com.ReseauRoutier;

import java.util.ArrayList;

public class Reseau {
	
	
	
	private ArrayList<Jonction> jonctions;
	
	

	public Reseau()
	{
		jonctions = new ArrayList<Jonction>();

	}
	
	public ArrayList<Jonction> getJonctions() {
		return jonctions;
	}
	
	public void setJonctions(ArrayList<Jonction> jonctions) {
		this.jonctions = jonctions;
	}
	
	public boolean initReseau(){
	
		return true;
	}
	

}
