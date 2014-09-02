package edu.udo.cs.ls14.jf.bpmn2pnml.petrinet;

/**
 * 
 * @author flake
 * 
 * @deprecated use PNML Framework
 */
@Deprecated
public class Place extends Node {

	private String srcId;
	private String tgtId;
	private boolean marked;

	public Place(String srcId, String tgtId, boolean marked) {
		super();
		this.srcId = srcId;
		this.tgtId = tgtId;
		this.marked = marked;
	}

	public String getSrcId() {
		return srcId;
	}

	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}

	public String getTgtId() {
		return tgtId;
	}

	public void setTgtId(String tgtId) {
		this.tgtId = tgtId;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean token) {
		this.marked = token;
	}

	@Override
	public String toString() {
		return getId();
	}

	public String getId() {
		return "P-" + srcId + "-" + tgtId;
	}

}
