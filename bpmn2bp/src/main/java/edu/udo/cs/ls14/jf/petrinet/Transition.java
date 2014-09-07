package edu.udo.cs.ls14.jf.petrinet;

/**
 * 
 * @author flake
 * 
 * @deprecated use PNML Framework
 */
@Deprecated
public class Transition extends Node {

	private String id;
	private String label;

	public Transition(String id) {
		super();
		this.id = id;
	}

	public Transition(String id, String label) {
		super();
		this.id = id;
		this.label = label;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return getId() + "[" + getLabel() + "]";
	}

	
}
