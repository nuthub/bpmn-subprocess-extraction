package edu.udo.cs.ls14.jf.conditionalprofile;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;

public class ConditionalProfile {
	private Map<FlowNode, Set<FormalExpression>> flowNodeConditions = null;

	public Map<FlowNode, Set<FormalExpression>> getFlowNodeConditions() {
		return flowNodeConditions;
	}

	public void setFlowNodeConditions(
			Map<FlowNode, Set<FormalExpression>> flowNodeConditions) {
		this.flowNodeConditions = flowNodeConditions;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("ConditionalProfile [");
		sb.append(System.getProperty("line.separator"));
		// fnc
		sb.append("  flowNodeConditions=[");
		sb.append(System.getProperty("line.separator"));
		for (Entry<FlowNode, Set<FormalExpression>> entry : flowNodeConditions
				.entrySet()) {
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
