package com.Regulation;

import java.util.Observable;
import java.util.Observer;

public abstract class Regulation implements Observer{
	
	@Override
	public abstract void update(Observable o, Object arg);
	

}
