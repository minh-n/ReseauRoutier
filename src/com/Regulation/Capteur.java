package com.Regulation;

import java.util.Observable;
import java.util.Observer;

import com.ReseauRoutier.ElementRoute;
import com.ReseauRoutier.SegmentDeRoute;
import com.ReseauRoutier.Voiture;

public abstract class Capteur implements Observer{
	
	private static int c_id = 0;
	private int id;
	private int positionDansRoute = 0;
	private int sens = 0;
	private SegmentDeRoute route;
	
	public Capteur(int pos, int sens)
	{
		c_id++;
		this.id = c_id;
		this.positionDansRoute = pos;
		this.sens = sens;
	}
	
	@Override
	public abstract void update(Observable o, Object arg);
	
	public void addObs()
	{
		if(getSens() == 0)
		{
			for(Voiture v:getRoute().getVoituresSens0())
			{
				v.addObserver(this);
			}
		}
		else
		{
			for(Voiture v:getRoute().getVoituresSens1())
			{
				v.addObserver(this);
			}
		}
	}

	public int getPositionDansRoute() {
		return positionDansRoute;
	}

	public void setPositionDansRoute(int positionDansRoute) {
		this.positionDansRoute = positionDansRoute;
	}

	public int getSens() {
		return sens;
	}

	public void setSens(int sens) {
		this.sens = sens;
	}

	public SegmentDeRoute getRoute() {
		return route;
	}

	public void setRoute(SegmentDeRoute route) {
		this.route = route;
	}

	public static int getC_id() {
		return c_id;
	}

	public static void setC_id(int c_id) {
		Capteur.c_id = c_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
