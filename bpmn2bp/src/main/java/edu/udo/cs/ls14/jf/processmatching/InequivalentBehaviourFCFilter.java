package edu.udo.cs.ls14.jf.processmatching;

import java.util.Set;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.emf.common.util.EList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnanalysis.Analysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnanalysis.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnanalysis.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnanalysis.NodePair;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.FragmentUtil;

public class InequivalentBehaviourFCFilter {

	private static final Logger LOG = LoggerFactory
			.getLogger(InequivalentBehaviourFCFilter.class);

	public static FragmentMatching filter(FragmentMatching fragmentMatching,
			NodeMatching nodeMatching, Analysis analysis1, Analysis analysis2) {
		// LOG.debug("Have to compare behaviour profiles");
		FragmentMatching matching = BpmnAnalysisFactory.eINSTANCE
				.createFragmentMatching();

		for (FragmentPair pair : fragmentMatching.getPairs()) {
			Set<FlowNode> nodes1 = FragmentUtil.getEventsAndActivites(pair
					.getA());
			Set<FlowNode> nodes2 = FragmentUtil.getEventsAndActivites(pair
					.getB());
			// LOG.debug("Comparing behaviour of " + pair.getValue0() + "("
			// + nodes1.size() + ") and " + pair.getValue1() + "("
			// + nodes2.size() + ") ");
			boolean match = behaviourProfilesMatch(
					nodes1,
					nodes2,
					(BehavioralProfile) analysis1.getResults().get(
							"behavioralProfile"), (BehavioralProfile) analysis2
							.getResults().get("behavioralProfile"),
					nodeMatching.getPairs());
			if (match) {
				LOG.info("Behaviour equivalent fragments: " + pair.getA()
						+ " / " + pair.getB());
				matching.getPairs().add(pair);
			} else {
				LOG.debug(pair.getA() + " and " + pair.getB()
						+ " are not behaviour equivalent.");
			}

		}
		return matching;
	}

	private static boolean behaviourProfilesMatch(Set<FlowNode> nodes1,
			Set<FlowNode> nodes2, BehavioralProfile profile1,
			BehavioralProfile profile2, EList<NodePair> nodeMatching) {
		boolean profilesMatch = true;
		for (FlowNode n1 : nodes1) {
			for (FlowNode n2 : nodes1) {
				// LOG.debug("Comparing " + n1.getName() + " and " +
				// n2.getName());
				BehavioralRelationType r1 = getRelation(profile1, n1, n2);
				FlowNode targetNode1 = nodeMatching
						.stream()
						.filter(p -> p.getA().equals(n1)
								&& nodes2.contains(p.getB())).findAny().get()
						.getB();
				FlowNode targetNode2 = nodeMatching
						.stream()
						.filter(p -> p.getA().equals(n2)
								&& nodes2.contains(p.getB())).findAny().get()
						.getB();
				BehavioralRelationType r2 = getRelation(profile2, targetNode1,
						targetNode2);
				if (r1 != r2) {
					profilesMatch = false;
				}
			}
		}
		return profilesMatch;
	}

	private static BehavioralRelationType getRelation(
			BehavioralProfile profile, FlowNode n1, FlowNode n2) {
		for (BehavioralRelation r : profile.getRelations()) {
			if (r.getLeft().equals(n1) && r.getRight().equals(n2)) {
				return r.getRelation();
			}
		}
		return null;
	}

}
