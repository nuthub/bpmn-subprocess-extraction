package edu.udo.cs.ls14.jf.processmatching;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnanalysis.NodePair;

public class NodeMatcher {

	public static NodeMatching getCorrespondences(Process process1,
			Process process2) throws Exception {
		NodeMatching matching = BpmnAnalysisFactory.eINSTANCE
				.createNodeMatching();
		NodeComparator comp = new NodeComparator();
		for (FlowElement e1 : process1.getFlowElements()) {
			if (e1 instanceof FlowNode) {
				for (FlowElement e2 : process2.getFlowElements()) {
					if (e2 instanceof FlowNode) {
						if (comp.isEquivalent((FlowNode) e1, (FlowNode) e2)) {
							NodePair pair = BpmnAnalysisFactory.eINSTANCE
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

	@Deprecated
	public static Set<Pair<FlowNode, FlowNode>> getCorrespondencesOld(
			Process process1, Process process2) throws Exception {
		Set<Pair<FlowNode, FlowNode>> mapping = new HashSet<Pair<FlowNode, FlowNode>>();
		NodeComparator comp = new NodeComparator();
		for (FlowElement e1 : process1.getFlowElements()) {
			if (e1 instanceof FlowNode) {
				for (FlowElement e2 : process2.getFlowElements()) {
					if (e2 instanceof FlowNode) {
						if (comp.isEquivalent((FlowNode) e1, (FlowNode) e2)) {
							mapping.add(Pair.with((FlowNode) e1, (FlowNode) e2));
						}
					}
				}
			}
		}
		return mapping;
	}

}
