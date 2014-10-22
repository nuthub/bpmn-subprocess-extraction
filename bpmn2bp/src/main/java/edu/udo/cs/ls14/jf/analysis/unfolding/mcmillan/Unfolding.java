package edu.udo.cs.ls14.jf.analysis.unfolding.mcmillan;

import java.util.HashSet;
import java.util.Set;

public class Unfolding {

	private Set<Place> places;
	private Set<Transition> transitions;

	public Unfolding() {
		super();
		places = new HashSet<Place>();
		transitions = new HashSet<Transition>();
	}
	
	public Set<Place> getPlaces() {
		return places;
	}

	public void setPlaces(Set<Place> places) {
		this.places = places;
	}

	public Set<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(Set<Transition> transitions) {
		this.transitions = transitions;
	}

	public void addPlace(Place place) {
		this.places.add(place);
	}

	public void addTransition(Transition transition) {
		this.transitions.add(transition);
	}
}
