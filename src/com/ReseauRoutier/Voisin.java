package com.ReseauRoutier;

public class Voisin {
	
	private Jonction jonction;
	private SegmentDeRoute segment;
	private int sensDep;
	private int sensArr;
	
	
	public Voisin()
	{
		
	}
	
	public Voisin(Jonction j, SegmentDeRoute s, int dep, int arr)
	{
		this.jonction = j;
		this.segment = s;
		this.setSensDep(dep);
		this.setSensArr(arr);
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
		return "Voisin [jonction=" + jonction.getId() + ", segment=" + segment.toString() + ", dep=" + sensDep + "]";
	}

	public int getSensDep() {
		return sensDep;
	}

	public void setSensDep(int sensDep) {
		this.sensDep = sensDep;
	}

	public int getSensArr() {
		return sensArr;
	}

	public void setSensArr(int sensArr) {
		this.sensArr = sensArr;
	}

}
