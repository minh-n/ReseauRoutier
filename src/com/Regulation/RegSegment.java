package com.Regulation;

import java.util.Observable;

import com.ReseauRoutier.SegmentDeRoute;

public class RegSegment extends Regulation {
	
	public RegSegment(SegmentDeRoute s)
	{
		this.elem = s;
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		
	}

}
