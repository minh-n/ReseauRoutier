package com.ReseauRoutier;

public class Voisin {
	
	private Jonction jonction;
	private SegmentDeRoute segment;
	private int sens1;
	private int sens2;
	
	
	public Voisin()
	{
		
	}
	
	public Voisin(Jonction j, SegmentDeRoute s, int dep, int arr)
	{
		this.jonction = j;
		this.segment = s;
		this.setSens1(dep);
		this.setSens2(arr);
	}

	public Jonction getJonction() {
		return jonction;
	}
	public void setJonction(Jonction jonction) {
		this.jonction = jonction;
	}
	public SegmentDeRoute getSegment() {
		return segment;
	}
	public void setSegment(SegmentDeRoute segment) {
		this.segment = segment;
	}
	
	@Override
	public String toString() {
		return "Voisin [jonction=" + jonction.getId() + ", segment=" + segment.toString() + "]";
	}

	public int getSens1() {
		return sens1;
	}

	public void setSens1(int sens1) {
		this.sens1 = sens1;
	}

	public int getSens2() {
		return sens2;
	}

	public void setSens2(int sens2) {
		this.sens2 = sens2;
	}

}
