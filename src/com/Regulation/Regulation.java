package com.Regulation;

import java.util.Observable;
import java.util.Observer;

import com.ReseauRoutier.ElementRoute;

public abstract class Regulation implements Observer{
	
	protected ElementRoute elem;
	
	public Regulation()
	{
	}
	
	@Override
	public abstract void update(Observable o, Object arg);
	

}
