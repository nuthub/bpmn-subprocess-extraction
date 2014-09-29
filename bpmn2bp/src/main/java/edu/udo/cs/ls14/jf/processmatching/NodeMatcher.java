package edu.udo.cs.ls14.jf.processmatching;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;

public class NodeMatcher {

	public static Map<FlowNode, FlowNode> getMapping(Process process1,
			Process process2) {
		Map<FlowNode, FlowNode> mapping = new HashMap<FlowNode, FlowNode>();
		NodeComparator comp = new NodeComparator();
		for (FlowElement e1 : process1.getFlowElements()) {
			if (e1 instanceof FlowNode) {
				for (FlowElement e2 : process2.getFlowElements()) {
					if (e2 instanceof FlowNode) {
						if (comp.isEquivalent((FlowNode) e1, (FlowNode) e2)) {
							mapping.put((FlowNode) e1, (FlowNode) e2);
						}
					}
				}
			}
		}
		return mapping;
	}
}
