package edu.udo.cs.ls14.jf.pnml2reachabilitygraph.model;

import java.util.ArrayList;

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
	
	public String toString() {
		return StringUtils.join(this,"+");
	}

}
