package com.Regulation;

import java.util.Observable;

import com.ReseauRoutier.SegmentDeRoute;

public abstract class Semaphore extends Observable{
	
	protected int sens;
	protected SegmentDeRoute route;
	
	public Semaphore(int sens, SegmentDeRoute route)
	{
		this.sens = sens;
		this.route = route;
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	}
}
