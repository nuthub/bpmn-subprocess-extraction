package edu.udo.cs.ls14.jf.bpmnanalysis.behaviorprofile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.Trace;
import edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile;

/**
 * Creates a Behavioral Profile from traces.
 * 
 * @author Julian Flake
 */
public class BehavioralProfiler {

	private static final Logger LOG = LoggerFactory
			.getLogger(BehavioralProfiler.class);

	/**
	 * Generate succession relation and behavioral profile of given process
	 * using the given Traces.
	 * 
	 * @param process
	 *            given process
	 * @param traceProfile
	 *            given Traces
	 * @return behavioral profile
	 */
	public static BehavioralProfile generateProfile(Process process,
			TraceProfile traceProfile) {

		Map<String, FlowNode> nodes = new HashMap<String, FlowNode>();
		for (FlowElement elem : process.getFlowElements()) {
			if (elem instanceof Event || elem instanceof Activity) {
				nodes.put(elem.getId(), (FlowNode) elem);
			}
		}
		Matrix<FlowNode, Boolean> successors = new Matrix<FlowNode, Boolean>(
				false);
		for (Trace t : traceProfile.getTraces()) {
			for (int i = 0; i < t.getNodes().size() - 1; i++) {
				if (!nodes.containsKey(t.getNodes().get(i).getId())) {
					LOG.debug(t.getNodes().get(i).getId()
							+ " not contained in " + nodes);
					continue;
				}
				if (!nodes.containsKey(t.getNodes().get(i + 1).getId())) {
					LOG.debug(t.getNodes().get(i + 1).getId()
							+ " not contained in " + nodes);
					continue;
				}
				successors.put(nodes.get(t.getNodes().get(i).getId()),
						nodes.get(t.getNodes().get(i + 1).getId()), true);
			}
		}
		List<BehavioralRelation> relations = new ArrayList<BehavioralRelation>();
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
		BehavioralProfile profile = BpmnAnalysisFactory.eINSTANCE
				.createBehavioralProfile();
		profile.getRelations().addAll(relations);
		return profile;
	}

	/**
	 * Return the relation type (1 out of 4)
	 * 
	 * @param successors
	 *            succession relation
	 * @param a
	 *            left hand side of relation
	 * @param b
	 *            right hand side of relation
	 * @return relation type
	 */
	public static BehavioralRelationType get(
			Matrix<FlowNode, Boolean> successors, FlowNode a, FlowNode b) {
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
