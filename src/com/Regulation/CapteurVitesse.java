package com.Regulation;

import java.util.Observable;

import com.ReseauRoutier.Voiture;

public class CapteurVitesse extends Capteur{

	public CapteurVitesse()
	{
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof Voiture)
		{
			System.out.println("Changement de vitesse");
		}
		
	}

	
}
