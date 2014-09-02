package edu.udo.cs.ls14.jf.pnml2reachabilitygraph.model;

public class Edge {

	private Marking src;
	private String t;
	private Marking tgt;

	public Edge() {
		super();
	}
	
	@Deprecated
	public Edge(Marking src, String t, Marking tgt) {
		super();
		this.src = src;
		this.t = t;
		this.tgt = tgt;
	}
	
	public Edge(String t) {
		super();
		this.t = t;
	}
	
	@Deprecated
	public Marking getSrc() {
		return src;
	}

	@Deprecated
	public void setSrc(Marking src) {
		this.src = src;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	@Deprecated
	public Marking getTgt() {
		return tgt;
	}

	@Deprecated
	public void setTgt(Marking tgt) {
		this.tgt = tgt;
	}

	@Override
	public String toString() {
		return "Edge [" + src + ", " + t + ", " + tgt + "]";
	}

}
