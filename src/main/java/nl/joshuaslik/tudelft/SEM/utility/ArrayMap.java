package nl.joshuaslik.tudelft.SEM.utility;

import java.util.ArrayList;

/**
 * Created by Joshua on 23/10/2015.
 */
public class ArrayMap<E, F> {
	
	private ArrayList<E> keys;
	private ArrayList<F> vals;
	
	public ArrayMap() {
		keys = new ArrayList<>();
		vals = new ArrayList<>();
	}
	
	public void put(E key, F value) {
		keys.add(key);
		vals.add(value);
	}
	
	public F get(E key) {
		if (keys.contains(key)) {
			return vals.get(keys.indexOf(key));
		}
		return null;
	}
	
	public void remove(E key) {
		if(keys.contains(key)) {
			vals.remove(keys.indexOf(key));
			keys.remove(key);
		}
	}
	
	public ArrayList<E> getKeys() {
		return keys;
	}
}
