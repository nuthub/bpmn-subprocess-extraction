package edu.udo.cs.ls14.jf.bpmnanalysis.conditionalprofile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.emf.common.util.BasicEList;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.FragmentUtil;

/**
 * Creates a conditional profile for a process or a fragment.
 * 
 * @author Julian Flake
 *
 */
public class ConditionalProfiler {

	/**
	 * Create Conditional Profile for given fragment from given model profile.
	 * 
	 * @param modelProfile
	 *            given conditional profile of complete model
	 * @param fragment
	 *            fragment the conditional profile should be created for
	 * @return conditional profile of fragment
	 */
	public static ConditionalProfile getFragmentProfile(
			ConditionalProfile modelProfile, Fragment fragment) {
		Set<FlowNode> fragmentNodes = FragmentUtil
				.getFlowElements(fragment,
						f -> f instanceof Activity || f instanceof Event)
				.stream().map(e -> (FlowNode) e).collect(Collectors.toSet());
		Set<SequenceFlow> fragmentFlows = FragmentUtil
				.getFlowElements(fragment, f -> f instanceof SequenceFlow)
				.stream().map(e -> (SequenceFlow) e)
				.collect(Collectors.toSet());
		Set<FormalExpression> fragmentExpressions = new HashSet<FormalExpression>();
		for (SequenceFlow flow : fragmentFlows) {
			if (flow.getConditionExpression() != null
					&& flow.getConditionExpression() instanceof FormalExpression) {
				fragmentExpressions.add((FormalExpression) flow
						.getConditionExpression());
			}
		}
		// Für alle Activities und Events des Fragments
		ConditionalProfile fragmentProfile = BpmnAnalysisFactory.eINSTANCE
				.createConditionalProfile();
		for (FlowNode node : fragmentNodes) {
			fragmentProfile.getRelations().put(node,
					new BasicEList<FormalExpression>());
			// get all expressions associated with element in
			// modelProfile, that are inside fragment
			for (FormalExpression exp : modelProfile.getRelations().get(node)) {
				if (fragmentExpressions.contains(exp)) {
					fragmentProfile.getRelations().get(node).add(exp);
				}
			}
		}
		return fragmentProfile;
	}

	/**
	 * Compute conditional profile for a given process model.
	 * 
	 * @param process
	 *            given process model
	 * @return Conditional Profile of process model
	 * @throws Exception
	 *             if a (syntactical) error of process model occurs
	 */
	public static ConditionalProfile generateProfile(Process process)
			throws Exception {
		ConditionalProfile cp = BpmnAnalysisFactory.eINSTANCE
				.createConditionalProfile();
		Map<SequenceFlow, FormalExpression> sequenceFlowConditions = new HashMap<SequenceFlow, FormalExpression>();
		Map<Expression, SequenceFlow> expressions = new HashMap<Expression, SequenceFlow>();

		// 1. Collect FormalExpressions for every Sequence Flow
		for (FlowElement element : process.getFlowElements()) {
			if (element instanceof Gateway) {
				// do nothing
			} else if (element instanceof FlowNode) {
				cp.getRelations().put((FlowNode) element,
						new BasicEList<FormalExpression>());
			} else if (element instanceof SequenceFlow) {
				Expression exp = ((SequenceFlow) element)
						.getConditionExpression();
				expressions.put(exp, (SequenceFlow) element);
				if (exp != null && exp instanceof FormalExpression) {
					sequenceFlowConditions.put((SequenceFlow) element,
							(FormalExpression) exp);
				}
			}
		}

		// 2. For every conditional flow, determine affected Nodes
		for (SequenceFlow flow : sequenceFlowConditions.keySet()) {
			Set<FlowNode> nodes = getAffectedNodes(flow,
					new HashSet<FlowNode>());
			for (FlowNode node : nodes) {
				cp.getRelations().get(node)
						.add(sequenceFlowConditions.get(flow));
			}
		}
		return cp;
	}

	private static Set<FlowNode> getAffectedNodes(SequenceFlow flow,
			Set<FlowNode> nodes) throws Exception {
		FlowNode tgt = flow.getTargetRef();
		if (tgt instanceof Event || tgt instanceof Activity) {
			nodes.add(tgt);
		} else if (tgt instanceof Gateway) {
			for (SequenceFlow seqFlow : ((Gateway) tgt).getOutgoing()) {
				nodes.addAll(getAffectedNodes(seqFlow, nodes));
			}
		}
		return nodes;
	}

}
