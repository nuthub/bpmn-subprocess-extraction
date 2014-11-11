package edu.udo.cs.ls14.jf.analysis.behaviorprofile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.Trace;

public class BehavioralProfiler {

	private static final Logger LOG = LoggerFactory
			.getLogger(BehavioralProfiler.class);

	public static EList<BehavioralRelation> generateProfile(Process process,
			List<Trace> traces) {

		Map<String, FlowNode> nodes = new HashMap<String, FlowNode>();
		for (FlowElement elem : process.getFlowElements()) {
			if (elem instanceof FlowNode) {
				nodes.put(elem.getId(), (FlowNode) elem);
			}
		}
		Matrix<FlowNode, Boolean> successors = new Matrix<FlowNode, Boolean>(
				false);
		for (Trace t : traces) {
			for (int i = 0; i < t.getNodes().size() - 1; i++) {
				if (!nodes.containsKey(t.getNodes().get(i).getId())) {
					LOG.debug(t.getNodes().get(i).getId() + " not contained in " + nodes);
				}
				if (!nodes.containsKey(t.getNodes().get(i + 1).getId())) {
					LOG.debug(t.getNodes().get(i + 1).getId() + " not contained in " + nodes);
				}
				successors.put(nodes.get(t.getNodes().get(i).getId()), nodes.get(t.getNodes().get(i + 1).getId()),
						true);
			}
		}
		// TODO: Bad smell? new BasicEList
		EList<BehavioralRelation> relations = new BasicEList<BehavioralRelation>();
		for (FlowNode a : successors.getKeys()) {
			for (FlowNode b : successors.getKeys()) {
				BehavioralRelation rel = BpmnAnalysisFactory.eINSTANCE
						.createBehavioralRelation();
				rel.setLeft(a);
				rel.setRight(b);
				rel.setRelation(get(successors, a, b));
				relations.add(rel);
			}
		}
		return relations;
	}

	public static BehavioralRelationType get(Matrix<FlowNode, Boolean> successors,
			FlowNode a, FlowNode b) {
		if (successors.get(a, b) && successors.get(b, a)) {
			return BehavioralRelationType.PARALLEL;
		}
		if (successors.get(a, b) && !successors.get(b, a)) {
			return BehavioralRelationType.DIRECT_SUCCESSOR;
		}
		if (!successors.get(a, b) && successors.get(b, a)) {
			return BehavioralRelationType.DIRECT_PREDECESSOR;
		}
		return BehavioralRelationType.NO_SUCCESSION;
	}

}
