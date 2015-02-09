package edu.udo.cs.ls14.jf.analysis.reachabilitygraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import fr.lip6.move.pnml.ptnet.Place;

public class Marking extends HashSet<Place> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4047290110729119826L;

	private boolean initialMarking = false;

	public boolean isInitialMarking() {
		return initialMarking;
	}

	public void setInitialMarking(boolean initialMarking) {
		this.initialMarking = initialMarking;
	}

	public String getId() {
		List<String> labels = new ArrayList<String>();
		for (Place p : this) {
			labels.add(p.getId());
		}
		return labels.toString();
	}

	public String getDotLabel() {
		Map<Integer, String> places2 = new HashMap<Integer, String>();
		for (Place p : this) {
			places2.put(Integer.parseInt(p.getName().getText().substring(1)), p
					.getName().getText());
		}
		SortedSet<Integer> keys = new TreeSet<Integer>(places2.keySet());
		List<String> places = new ArrayList<String>();
		for (int i : keys) {
			places.add(places2.get(i));
		}
		return StringUtils.join(places.toArray(), " ");
	}

	public String toString() {
		return StringUtils.join(this, "+");
	}

}
