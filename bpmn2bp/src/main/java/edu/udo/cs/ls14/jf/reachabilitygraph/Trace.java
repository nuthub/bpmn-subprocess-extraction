package edu.udo.cs.ls14.jf.reachabilitygraph;

import java.util.ArrayList;
import java.util.List;

public class Trace extends ArrayList<String> implements List<String> {

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
