package edu.udo.cs.ls14.jf.pst;

import java.util.ArrayList;

import org.eclipse.bpmn2.SequenceFlow;

public class Fragment extends ArrayList<SequenceFlow>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6323721562347577151L;
	
	public Fragment(SequenceFlow first, SequenceFlow second) {
		super(2);
		add(first);
		add(second);
	}

	
	public SequenceFlow getEntry() {
		return get(0);
	}
	
	public SequenceFlow getExit() {
		return get(1);
	}
	
}
