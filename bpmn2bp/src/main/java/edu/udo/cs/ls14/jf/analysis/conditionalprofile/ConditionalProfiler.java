package edu.udo.cs.ls14.jf.analysis.conditionalprofile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class ConditionalProfiler {

	public static ConditionalProfile generateProfile(Resource resource)
			throws Exception {
		Map<FlowNode, Set<FormalExpression>> flowNodeConditions = new HashMap<FlowNode, Set<FormalExpression>>();

		Map<SequenceFlow, FormalExpression> sequenceFlowConditions = new HashMap<SequenceFlow, FormalExpression>();
		Map<Expression, SequenceFlow> expressions = new HashMap<Expression, SequenceFlow>();

		Process process = ProcessLoader.getProcessFromResource(resource);
		for (FlowElement element : process.getFlowElements()) {
			if (element instanceof Gateway) {
				// do nothing
			} else if (element instanceof FlowNode) {
				flowNodeConditions.put((FlowNode) element,
						new HashSet<FormalExpression>());
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
		for (SequenceFlow flow : sequenceFlowConditions.keySet()) {

			Set<FlowNode> nodes = getAffectedNodes(flow,
					new HashSet<FlowNode>());
			for (FlowNode node : nodes) {
				flowNodeConditions.get(node).add(
						sequenceFlowConditions.get(flow));
			}
		}
		ConditionalProfile cp = new ConditionalProfile();
		cp.setFlowNodeConditions(flowNodeConditions);
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
		} else {
			throw new Exception(
					"targetRef is not of type Event, Activity or Gateway! "
							+ tgt);
		}
		return nodes;
	}

}
