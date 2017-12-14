package com.Regulation;

import java.util.Observable;

import com.ReseauRoutier.SegmentDeRoute;

public abstract class Semaphore{
	
	private static int c_id = 0;
	private int id;
	protected int sens;
	protected SegmentDeRoute route;
	
	public Semaphore(int sens, SegmentDeRoute route)
	{
		c_id++;
		this.id = c_id;
		this.sens = sens;
		this.route = route;
	}	

	/**
	 * applique la regulation du semaphore sur la route
	 */
	public abstract void regle();
	
	public int getSens() {
		return sens;
	}

	public int getId() {
		return id;
	}
}
