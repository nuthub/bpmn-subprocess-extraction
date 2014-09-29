package edu.udo.cs.ls14.jf.utils.bpmn;

import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.StartEvent;

/**
 * TODO: break down to graph notion
 * 
 * @author flake
 *
 */
public class NodeFinder {

	/*
	 * TODO: check for multiple start nodes
	 */
	public static StartEvent getStartNode(Process process) {
		for (FlowElement elem : process.getFlowElements()) {
			if (elem instanceof StartEvent) {
				return (StartEvent) elem;
			}
		}
		return null;
	}

	/*
	 * TODO: check for multiple end nodes
	 */
	public static EndEvent getEndNode(Process process) {
		for (FlowElement elem : process.getFlowElements()) {
			if (elem instanceof EndEvent) {
				return (EndEvent) elem;
			}
		}
		return null;
	}

	public static FlowElement getElement(Process process, String id) {
		if (id == null || id.isEmpty()) {
			return null;
		}
		for (FlowElement f : process.getFlowElements()) {
			if (f.getId().equals(id)) {
				return f;
			}
		}
		return null;
	}
}
