package edu.udo.cs.ls14.jf.analysis.reachabilitygraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fr.lip6.move.pnml.ptnet.Place;

public class Marking extends ArrayList<Place> {

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
		List<String> places = new ArrayList<String>();
		for (Place p : this) {
			places.add(p.getName().getText());
		}
		Collections.sort(places);
		return StringUtils.join(places.toArray(), " ");
	}

	public String toString() {
		return StringUtils.join(this, "+");
	}

}
