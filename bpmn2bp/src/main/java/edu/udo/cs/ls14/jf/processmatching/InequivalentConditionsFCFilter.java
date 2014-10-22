package edu.udo.cs.ls14.jf.processmatching;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.SequenceFlow;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.conditionalprofile.ConditionalProfile;
import edu.udo.cs.ls14.jf.pst.Fragment;

public class InequivalentConditionsFCFilter {

	private static final Logger LOG = LoggerFactory
			.getLogger(InequivalentConditionsFCFilter.class);

	public static ProcessMatching filter(ProcessMatching matching) {
		Set<Pair<Fragment, Fragment>> filteredMapping = new HashSet<Pair<Fragment, Fragment>>();
		// ConditionalProfile p1 = matching.
		for (Pair<Fragment, Fragment> mapping : matching
				.getFragmentCorrespondences()) {
			Fragment f1 = mapping.getValue0();
			Fragment f2 = mapping.getValue1();
			LOG.debug("-------------");
			LOG.debug("Checking if " + f1 + " and " + f2
					+ " have the same conditional profile.");
			Set<FlowNode> nodes1 = getNodes(f1);
			Set<FlowNode> nodes2 = getNodes(f2);
			Set<SequenceFlow> edges1 = getEdges(f1);
			Set<SequenceFlow> edges2 = getEdges(f2);
			ConditionalProfile cp1 = matching.getAnalysis1()
					.getConditionalProfile();
			ConditionalProfile cp2 = matching.getAnalysis2()
					.getConditionalProfile();
			// Hole relevante FNCs
			Map<FlowNode, Set<FormalExpression>> fnc1 = getFragmentFnc(cp1,
					nodes1, edges1);
			Map<FlowNode, Set<FormalExpression>> fnc2 = getFragmentFnc(cp2,
					nodes2, edges2);
			boolean allFragmentConditionsMatch = true;
			// compare conditions
			// Für alle Knoten n1 von Prozess1
			for (FlowNode n1 : nodes1) {
				// hole korrespondierenden Knoten n2
				FlowNode n2 = getMatchingNode(matching, n1);
				LOG.debug("Now comparing conditions of:");
				LOG.debug("n1 = " + n1);
				LOG.debug("n2 = " + n2);
				LOG.debug("n1 has " + fnc1.get(n1).size() + " conditions in "
						+ f1);
				LOG.debug("n2 has " + fnc2.get(n2).size() + " conditions in "
						+ f2);
				// suche Match
				boolean nodeConditionsMatch = false;
				// bedingungsloser Match
				if (fnc1.get(n1).size() == 0 && fnc2.get(n2).size() == 0) {
					LOG.debug("There are no conditions, so n1 and n2 match.");
					nodeConditionsMatch = true;
				}
				// Für alle Conditions von n1
				for (FormalExpression exp1 : fnc1.get(n1)) {
					LOG.debug("Search match for " + exp1 + " in "
							+ fnc2.get(n2));
					// check for NPE
					if (exp1.getBody() == null) {
						LOG.warn(exp1 + " has null body!");
						continue;
					}
					// prüfe, ob Entsprechung in fnc2 vorhanden
					for (FormalExpression exp2 : fnc2.get(n2)) {
						LOG.debug("Comparing with " + exp2);
						if (exp1.getBody().equals(exp2.getBody())) {
							LOG.debug("Match: " + exp1.getBody() + " / "
									+ exp2.getBody() + " !");
							nodeConditionsMatch = true;
						}
					}
				}
				// Wenn keine Entsprechung gefunden, abbrechen, Conditional
				// Profiles sind unterschiedlich
				if (!nodeConditionsMatch) {
					allFragmentConditionsMatch = false;
					continue;
				}
			}
			if (allFragmentConditionsMatch) {
				LOG.info("Conditions equivalent fragments: " + f1 + " / " + f2);
				filteredMapping.add(mapping);
			} else {
				LOG.debug(f1 + " and " + f2 + " are not conditions equivalent.");
			}
		}
		ProcessMatching newMatching = matching.clone();
		newMatching.setFragmentCorrespondences(filteredMapping);
		return newMatching;
	}

	private static Map<FlowNode, Set<FormalExpression>> getFragmentFnc(
			ConditionalProfile cp, Set<FlowNode> nodes, Set<SequenceFlow> edges) {
		Map<FlowNode, Set<FormalExpression>> fnc = cp.getFlowNodeConditions();
		Map<FlowNode, Set<FormalExpression>> fnc2 = new HashMap<FlowNode, Set<FormalExpression>>();
		// Für alle Knoten des Fragments
		for (FlowNode node : nodes) {
			Set<FormalExpression> exps = new HashSet<FormalExpression>();
			// Für alle Condition Expressions für den Knoten
			for (FormalExpression exp : fnc.get(node)) {
				// Wenn SequenceFlow der Expression in Fragment enthalten
				if (edgeSetContainsExpression(edges, exp)) {
					exps.add(exp);
				}
			}
			fnc2.put(node, exps);
		}
		return fnc2;
	}

	private static boolean edgeSetContainsExpression(Set<SequenceFlow> edges,
			FormalExpression exp) {
		for (SequenceFlow flow : edges) {
			if (exp.equals(flow.getConditionExpression())) {
				return true;
			}
		}
		return false;
	}

	private static FlowNode getMatchingNode(ProcessMatching matching,
			FlowNode node) {
		for (Pair<FlowNode, FlowNode> pair : matching.getNodeCorrespondences()) {
			if (pair.getValue0().getId().equals(node.getId())) {
				return pair.getValue1();
			}
		}
		return null;
	}

	private static Set<FlowNode> getNodes(Fragment f) {
		return f.getContainedFlowNodes(n -> n instanceof Event
				|| n instanceof Activity);
	}

	private static Set<SequenceFlow> getEdges(Fragment fragment) {
		return fragment
				.getContainedFlowElements(
						flow -> flow instanceof SequenceFlow
								&& !flow.equals(fragment.getEntry())
								&& !flow.equals(fragment.getExit())).stream()
				.map(flow -> (SequenceFlow) flow).collect(Collectors.toSet());
	}
}
