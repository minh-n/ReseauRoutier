package com.ReseauRoutier;

public class Voisin {
	
	private Jonction jonction;
	private SegmentDeRoute segment;

	
	
	public Voisin()
	{
		
	}
	
	public Voisin(Jonction j, SegmentDeRoute s, int dep, int arr)
	{
		this.jonction = j;
		this.segment = s;
		this.segment.setSens1(dep);
		this.segment.setSens2(arr);
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

}
