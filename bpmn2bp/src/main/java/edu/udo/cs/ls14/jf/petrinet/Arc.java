package edu.udo.cs.ls14.jf.petrinet;

/**
 * 
 * @author flake
 * 
 * @deprecated use PNML Framework
 */
@Deprecated
public class Arc {

	private String source;
	private String target;

	public Arc(String source, String target) {
		super();
		this.source = source;
		this.target = target;
	}

	public String getSource() {
		return source;
	}

	public String getTarget() {
		return target;
	}

	@Override
	public String toString() {
		return "Arc-" + source + "---" + target + "";
	}

}
