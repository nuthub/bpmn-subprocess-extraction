package edu.udo.cs.ls14.jf.matching;

import org.eclipse.bpmn2.FlowElement;

public class Matching {

	private FlowElement first;
	private FlowElement second;

	public Matching(FlowElement first, FlowElement second) {
		this.first = first;
		this.second = second;
	}

	public FlowElement getFirst() {
		return first;
	}

	public void setFirst(FlowElement first) {
		this.first = first;
	}

	public FlowElement getSecond() {
		return second;
	}

	public void setSecond(FlowElement second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return "Matching (" + first.getName() + " ["
				+ first.getClass().getSimpleName() + "] , " + second.getName()
				+ " [" + second.getClass().getSimpleName() + "])";
	}

}
