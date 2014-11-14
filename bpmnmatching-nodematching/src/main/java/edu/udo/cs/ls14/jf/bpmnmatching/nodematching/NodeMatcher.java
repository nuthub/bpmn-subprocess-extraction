package edu.udo.cs.ls14.jf.bpmnmatching.nodematching;

import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodePair;

public class NodeMatcher {

	public static NodeMatching getCorrespondences(Process process1,
			Process process2) throws Exception {
		NodeMatching matching = BpmnMatchingFactory.eINSTANCE
				.createNodeMatching();
		NodeComparator comp = new NodeComparator();
		for (FlowElement e1 : process1.getFlowElements()) {
			if (e1 instanceof FlowNode) {
				for (FlowElement e2 : process2.getFlowElements()) {
					if (e2 instanceof FlowNode) {
						if (comp.isEquivalent((FlowNode) e1, (FlowNode) e2)) {
							NodePair pair = BpmnMatchingFactory.eINSTANCE
									.createNodePair();
							pair.setA((FlowNode) e1);
							pair.setB((FlowNode) e2);
							matching.getPairs().add(pair);
						}
					}
				}
			}
		}
		return matching;

	}

}
