package edu.udo.cs.ls14.jf.unfolding.esparza;

import java.util.Set;

import fr.lip6.move.pnml.ptnet.Transition;

/**
 * An event is a pair (t, B), where t is a transition of the original net, and B
 * is the set of input conditions.
 * 
 * @author flake
 *
 */
public class Event extends Node {

	private Transition trans;
	private Set<Condition> inputConditions;

	public Event(Transition trans, Set<Condition> inputConditions) {
		super();
		this.trans = trans;
		this.inputConditions = inputConditions;
	}

	public Transition getTrans() {
		return trans;
	}

	public void setTrans(Transition trans) {
		this.trans = trans;
	}

	public Set<Condition> getInputConditions() {
		return inputConditions;
	}

	public void setInputConditions(Set<Condition> inputConditions) {
		this.inputConditions = inputConditions;
	}

	@Override
	public String toString() {
		return "Event [trans=" + trans + ", inputConditions=" + inputConditions
				+ "]";
	}

}
