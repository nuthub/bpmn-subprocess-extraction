package edu.udo.cs.ls14.jf.fragmentmatching;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.bpmn2.FlowNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodePair;

public class FragmentPairFilterBehavior {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairFilterBehavior.class);

	public static FragmentMatching filter(FragmentMatching fragmentMatching,
			NodeMatching nodeMatching, ProcessAnalysis analysis1,
			ProcessAnalysis analysis2) {
		// LOG.debug("Have to compare behaviour profiles");
		List<FragmentPair> removePairs = new ArrayList<FragmentPair>();
		for (FragmentPair pair : fragmentMatching.getPairs()) {
			Set<FlowNode> nodes1 = FragmentUtil.getEventsAndActivites(pair
					.getA());
			Set<FlowNode> nodes2 = FragmentUtil.getEventsAndActivites(pair
					.getB());
			// LOG.debug("Comparing behaviour of " + pair.getValue0() + "("
			// + nodes1.size() + ") and " + pair.getValue1() + "("
			// + nodes2.size() + ") ");
			boolean match = behaviourProfilesMatch(nodes1, nodes2,
					ProcessAnalysisUtil.getBehavioralProfile(analysis1),
					ProcessAnalysisUtil.getBehavioralProfile(analysis2),
					nodeMatching.getPairs());
			if (match) {
				LOG.info("Fragments are behaviour equivalent: "
						+ FragmentUtil.toString(pair.getA()) + " / "
						+ FragmentUtil.toString(pair.getB()));
			} else {
				LOG.info("Fragments are not behaviour equivalent: "
						+ FragmentUtil.toString(pair.getA()) + " / "
						+ FragmentUtil.toString(pair.getB()));
				removePairs.add(pair);
			}

		}
		fragmentMatching.getPairs().removeAll(removePairs);
		return fragmentMatching;
	}

	private static boolean behaviourProfilesMatch(Set<FlowNode> nodes1,
			Set<FlowNode> nodes2, BehavioralProfile profile1,
			BehavioralProfile profile2, List<NodePair> nodeMatching) {
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
