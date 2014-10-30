package edu.udo.cs.ls14.jf.processmatching;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.resource.Resource;
import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;

public class NodeMatcher {

	public static Set<Pair<FlowNode, FlowNode>> getCorrespondences(Resource res1,
			Resource res2) throws Exception {
		Process process1 = ProcessLoader.getProcessFromResource(res1);
		Process process2 = ProcessLoader.getProcessFromResource(res2);
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
