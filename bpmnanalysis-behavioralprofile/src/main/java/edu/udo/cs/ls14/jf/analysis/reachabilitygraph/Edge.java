package edu.udo.cs.ls14.jf.analysis.reachabilitygraph;

/**
 * Edge implementation of an edge in reachability graph.
 * 
 * @author Julian Flake
 *
 */
public class Edge {

	private String t;

	/**
	 * Creates a new edge for Transition with ID or Name t
	 * 
	 * @param t
	 *            ID or name
	 */
	public Edge(String t) {
		super();
		this.t = t;
	}

	/**
	 * Return ID or Name of this edge
	 * 
	 * @return ID or Name of edge
	 */
	public String getT() {
		return t;
	}

}
