package edu.umb.cs681.hw15;

import java.util.HashMap;
import java.util.Map;

public class DJIAQuoteObservable extends Observable implements Runnable {
	
	private Map<String,Float> map=new HashMap<String, Float>();
	
	public void changeQuote(String s,float q) {
		map.put(s, q);
		setChanged();
		notifyObservers(new DJIAEvent(q));
		
	}

	public Map<String, Float> getMap() {
		return map;
	}

	public void setMap(Map<String, Float> map) {
		this.map = map;
	}
	public void run() {
		addObserver((Observable o,Object obj)->{System.out.println("Added new Observer");});

		changeQuote("Paul", 9000);
	}

	

}
