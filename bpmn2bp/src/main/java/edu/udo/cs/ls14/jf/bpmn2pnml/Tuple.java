package edu.udo.cs.ls14.jf.bpmn2pnml;

public class Tuple<U, V> {

	private U first;
	private V second;
	
	public Tuple(U first, V second) {
		this.first = first;
		this.second = second;
	}

	public U getFirst() {
		return first;
	}

	public void setFirst(U first) {
		this.first = first;
	}

	public V getSecond() {
		return second;
	}

	public void setSecond(V second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}

}
