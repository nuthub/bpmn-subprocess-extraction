package edu.udo.cs.ls14.jf.analysis.reachabilitygraph;

import java.util.ArrayList;
import java.util.List;

public class Trace extends ArrayList<String> implements List<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3394826206326636581L;
	private boolean finished;

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public String toString() {
		String ret = super.toString();
		if (!finished) {
			ret += " (...)";
		}
		return ret;
	}
}
