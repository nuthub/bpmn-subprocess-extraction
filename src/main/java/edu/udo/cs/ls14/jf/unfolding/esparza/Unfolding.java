package edu.udo.cs.ls14.jf.unfolding.esparza;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.lip6.move.pnml.ptnet.Place;
import fr.lip6.move.pnml.ptnet.Transition;

public class Unfolding extends BranchingProcess {

	private Set<Event> events;
	private Set<Condition> conditions;

	private Map<Place, Set<Condition>> places;
	private Map<Transition, Set<Event>> transitions;

	public Unfolding() {
		super();
		events = new HashSet<Event>();
		conditions = new HashSet<Condition>();
		places = new HashMap<Place, Set<Condition>>();
		transitions = new HashMap<Transition, Set<Event>>();
	}

	public Set<Event> getEvents() {
		return events;
	}

	public Set<Condition> getConditions() {
		return conditions;
	}

	public Map<Place, Set<Condition>> getPlaces() {
		return places;
	}

	public Map<Transition, Set<Event>> getTransitions() {
		return transitions;
	}

	public void addEvent(Event event) {
		this.events.add(event);
		Transition t = event.getTrans();
		if (!this.transitions.containsKey(t)) {
			transitions.put(t, new HashSet<Event>());
		}
		transitions.get(t).add(event);
	}

	public void addCondition(Condition condition) {
		this.conditions.add(condition);
		Place p = condition.getPlace();
		if (!this.places.containsKey(p)) {
			places.put(p, new HashSet<Condition>());
		}
		places.get(p).add(condition);
	}

	@Override
	public String toString() {
		return "Unfolding [events=" + events + ", conditions=" + conditions
				+ "]";
	}
}
