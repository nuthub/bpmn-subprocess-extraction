package edu.udo.cs.ls14.jf.matching;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;

public class Matcher {

	public Set<Matching> getMatches(Process p, Process q) {
		Set<Matching> matches = new HashSet<Matching>();
		Comparator comp = new Comparator();
		for (FlowElement e1 : p.getFlowElements()) {
			if (e1 instanceof FlowNode) {
				for (FlowElement e2 : q.getFlowElements()) {
					if (e2 instanceof FlowNode) {
						if (comp.isEquivalent((FlowNode) e1, (FlowNode) e2)) {
							matches.add(new Matching(e1, e2));
						}
					}
				}
			}
		}
		return matches;
	}
}
