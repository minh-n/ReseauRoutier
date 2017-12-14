package com.Regulation;

import java.util.Observable;
import java.util.Observer;

import com.ReseauRoutier.JonctionReg;
import com.ReseauRoutier.Reseau;

public abstract class Regulation implements Observer{
	
	JonctionReg jonction;
	
	public Regulation(JonctionReg jonction, Reseau reseau)
	{
		this.jonction = jonction;
		this.jonction.setRegulation(this);
		reseau.addObserver(this);
	}
	
	public Regulation(JonctionReg jonction)
	{
		this.jonction = jonction;
	}
	
	@Override
	public abstract void update(Observable o, Object arg);
	

}
