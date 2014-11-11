package edu.udo.cs.ls14.jf.processmatching;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.SequenceFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodePair;

public class InequivalentConditionsFCFilter {

	private static final Logger LOG = LoggerFactory
			.getLogger(InequivalentConditionsFCFilter.class);

	/**
	 * TODO: könnte eleganter gelöst werden / funktional
	 * 
	 * @param matchingIn
	 * @return
	 */
	public static FragmentMatching filter(FragmentMatching matchingIn,
			NodeMatching nodeMatching, ProcessAnalysis analysis1, ProcessAnalysis analysis2) {
		FragmentMatching matchingOut = BpmnMatchingFactory.eINSTANCE
				.createFragmentMatching();
		for (FragmentPair pair : matchingIn.getPairs()) {
			Fragment f1 = pair.getA();
			Fragment f2 = pair.getB();
			LOG.debug("-------------");
			LOG.debug("Checking if " + f1 + " and " + f2
					+ " have the same conditional profile.");
			Set<FlowNode> nodes1 = FragmentUtil.getEventsAndActivites(f1);
			Set<FlowNode> nodes2 = FragmentUtil.getEventsAndActivites(f2);
			Set<SequenceFlow> edges1 = FragmentUtil.getEdges(f1);
			Set<SequenceFlow> edges2 = FragmentUtil.getEdges(f2);
			ConditionalProfile cp1 = (ConditionalProfile) analysis1
					.getResults().get("conditionalProfile");
			ConditionalProfile cp2 = (ConditionalProfile) analysis2
					.getResults().get("conditionalProfile");
			// Hole relevante FNCs
			Map<FlowNode, Set<FormalExpression>> fnc1 = getFragmentFnc(cp1,
					nodes1, edges1);
			Map<FlowNode, Set<FormalExpression>> fnc2 = getFragmentFnc(cp2,
					nodes2, edges2);
			boolean allConditionsMatch = true;
			// compare conditions
			// Für alle Knoten n1 von Prozess1
			for (FlowNode n1 : nodes1) {
				// hole korrespondierenden Knoten n2
				FlowNode n2 = getMatchingNode(matchingIn, nodeMatching, n1);
				LOG.debug("Now comparing conditions of:");
				LOG.debug("n1 = " + n1);
				LOG.debug("n2 = " + n2);
				LOG.debug("n1 has " + fnc1.get(n1).size() + " conditions in "
						+ f1);
				LOG.debug("n2 has " + fnc2.get(n2).size() + " conditions in "
						+ f2);
				// suche Match
				boolean nodeConditionsMatch = true;
				// bedingungsloser Match
				if (fnc1.get(n1).size() == 0 && fnc2.get(n2).size() == 0) {
					LOG.debug("There are no conditions to match, so n1 and n2 have a condition match.");
					nodeConditionsMatch = true;
				}
				if (fnc1.get(n1).size() != fnc2.get(n2).size()) {
					LOG.debug("The amounts of nodes' conditions differ, so they cannot have a condition match.");
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
					boolean expressionMatchFound = false;
					for (FormalExpression exp2 : fnc2.get(n2)) {
						LOG.debug("Comparing with " + exp2);
						if (exp1.getBody().equals(exp2.getBody())) {
							LOG.debug("Match: " + exp1.getBody() + " / "
									+ exp2.getBody() + " !");
							expressionMatchFound = true;
						}
					} // forall expressions of n2
					if (!expressionMatchFound) {
						nodeConditionsMatch = false;
						LOG.debug("No matching expression could be found.");
					}
				} // forall expressions of n1
					// Wenn keine Entsprechung gefunden, abbrechen, Conditional
					// Profiles sind unterschiedlich
				if (!nodeConditionsMatch) {
					allConditionsMatch = false;
					LOG.debug("Not all nodes' conditions have correspondences.");
					// continue;
				}
			} // forall nodes of fragment1
			if (allConditionsMatch) {
				LOG.info("Conditions equivalent fragments: " + f1 + " / " + f2);
				matchingOut.getPairs().add(pair);
			} else {
				LOG.debug(f1 + " and " + f2 + " are not conditions equivalent.");
			}
		}
		return matchingOut;
	}

	private static Map<FlowNode, Set<FormalExpression>> getFragmentFnc(
			ConditionalProfile cp, Set<FlowNode> nodes, Set<SequenceFlow> edges) {
		Map<FlowNode, List<FormalExpression>> fnc = cp.getRelations();
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

	private static FlowNode getMatchingNode(FragmentMatching matchingIn,
			NodeMatching nodeMatching, FlowNode node) {
		for (NodePair pair : nodeMatching.getPairs()) {
			if (pair.getA().getId().equals(node.getId())) {
				return pair.getB();
			}
		}
		return null;
	}

}
