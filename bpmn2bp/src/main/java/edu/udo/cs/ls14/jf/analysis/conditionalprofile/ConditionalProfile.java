package edu.udo.cs.ls14.jf.analysis.conditionalprofile;

import java.util.HashMap;
import java.util.Set;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;

public class ConditionalProfile extends HashMap<FlowNode, Set<FormalExpression>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5558382440946440541L;

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("ConditionalProfile [");
		sb.append(System.getProperty("line.separator"));
		// fnc
		sb.append("  flowNodeConditions=[");
		sb.append(System.getProperty("line.separator"));
		for (Entry<FlowNode, Set<FormalExpression>> entry : entrySet()) {
			sb.append("    " + entry.getKey().getId() + " => ");
			for (FormalExpression exp : entry.getValue()) {
				sb.append(exp.getBody() + ", ");
			}
			sb.append(System.getProperty("line.separator"));
		}
		sb.append("  ]");
		sb.append(System.getProperty("line.separator"));

		sb.append("]");
		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}
}
