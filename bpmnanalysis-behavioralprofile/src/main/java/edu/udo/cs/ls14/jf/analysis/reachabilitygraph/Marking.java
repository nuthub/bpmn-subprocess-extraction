package edu.udo.cs.ls14.jf.analysis.reachabilitygraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import fr.lip6.move.pnml.ptnet.Place;

/**
 * A Marking of a petri net represented as a set of places.
 * 
 * @author Julian Flake
 *
 */
public class Marking extends HashSet<Place> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4047290110729119826L;

	private boolean initialMarking = false;

	/**
	 * Return true, if this marking has been set as initial.
	 * 
	 * @return true, if this marking is initial
	 */
	public boolean isInitialMarking() {
		return initialMarking;
	}

	/**
	 * Set if this marking is an initial marking
	 * 
	 * @param initialMarking
	 *            whether this marking is initial or not
	 */
	public void setInitialMarking(boolean initialMarking) {
		this.initialMarking = initialMarking;
	}

	/**
	 * Return a generated ID.
	 * 
	 * @return ID
	 */
	public String getId() {
		List<String> labels = new ArrayList<String>();
		for (Place p : this) {
			labels.add(p.getId());
		}
		Collections.sort(labels);
		return labels.toString();
	}

	/**
	 * Create a readable label
	 * 
	 * @return label of this marking
	 */
	public String getLabel() {
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

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return StringUtils.join(this, "+");
	}

}
