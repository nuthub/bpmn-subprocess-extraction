package edu.udo.cs.ls14.jf.analysis.behaviorprofile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Trace;

public class BehavioralProfiler {

	private static final Logger LOG = LoggerFactory
			.getLogger(BehavioralProfiler.class);

	public static BehavioralProfile generateProfile(Process process, Set<Trace> traces) {
		
		Map<String, FlowNode> nodes = new HashMap<String, FlowNode>();
		for (FlowElement elem : process.getFlowElements()) {
			if (elem instanceof FlowNode) {
				nodes.put(elem.getId(), (FlowNode) elem);
			}
		}
		Matrix<FlowNode, Boolean> successors = new Matrix<FlowNode, Boolean>(false);
		for (Trace t : traces) {
			for (int i = 0; i < t.size() - 1; i++) {
				if (!nodes.containsKey(t.get(i))) {
					LOG.debug(t.get(i) + " not contained in " + nodes);
				}
				if (!nodes.containsKey(t.get(i + 1))) {
					LOG.debug(t.get(i + 1) + " not contained in " + nodes);
				}
				successors.put(nodes.get(t.get(i)), nodes.get(t.get(i + 1)),
						true);
			}
		}
		BehavioralProfile bp = new BehavioralProfile();
		for (FlowNode a : successors.getKeys()) {
			for (FlowNode b : successors.getKeys()) {
				bp.put(Pair.with(a.getId(), b.getId()), get(successors, a, b));
			}
		}
		return bp;
	}

	public static RelationType get(Matrix<FlowNode, Boolean> successors, FlowNode a, FlowNode b) {
		if (successors.get(a, b) && successors.get(b, a)) {
			return RelationType.PARALLEL;
		}
		if (successors.get(a, b) && !successors.get(b, a)) {
			return RelationType.DIRECT_SUCCESSOR;
		}
		if (!successors.get(a, b) && successors.get(b, a)) {
			return RelationType.DIRECT_PREDECESSOR;
		}
		return RelationType.NO_SUCCESSION;
	}

}
