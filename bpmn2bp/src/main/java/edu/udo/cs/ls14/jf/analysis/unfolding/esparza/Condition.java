package edu.udo.cs.ls14.jf.analysis.unfolding.esparza;

import fr.lip6.move.pnml.ptnet.Place;

/**
 * A condition is a pair (s, e), where s is a place of the original net and e
 * the input event.
 * 
 * @author flake
 *
 */
public class Condition extends Node {

	private Place place;
	private Event inputEvent;

	public Condition(Place place, Event inputEvent) {
		super();
		this.place = place;
		this.inputEvent = inputEvent;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Event getInputEvent() {
		return inputEvent;
	}

	public void setInputEvent(Event inputEvent) {
		this.inputEvent = inputEvent;
	}

	@Override
	public String toString() {
		return "Condition [place=" + place + ", inputEvent=" + inputEvent + "]";
	}

}
