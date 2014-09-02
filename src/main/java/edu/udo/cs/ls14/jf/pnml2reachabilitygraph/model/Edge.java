package edu.udo.cs.ls14.jf.pnml2reachabilitygraph.model;

public class Edge {

	private String t;

	public Edge() {
		super();
	}

	public Edge(String t) {
		super();
		this.t = t;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	@Override
	public String toString() {
		return "Edge [" + t + "]";
	}

}
