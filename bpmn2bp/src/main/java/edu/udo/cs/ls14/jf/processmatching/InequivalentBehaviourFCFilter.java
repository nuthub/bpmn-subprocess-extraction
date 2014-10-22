package edu.udo.cs.ls14.jf.processmatching;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowNode;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfile;
import edu.udo.cs.ls14.jf.analysis.behaviorprofile.RelationType;
import edu.udo.cs.ls14.jf.analysis.pst.Fragment;

public class InequivalentBehaviourFCFilter {

	private static final Logger LOG = LoggerFactory
			.getLogger(InequivalentBehaviourFCFilter.class);

	public static Set<Pair<Fragment, Fragment>> filter(ProcessMatching matching) {
		// LOG.debug("Have to compare behaviour profiles");
		Set<Pair<Fragment, Fragment>> pairs = new HashSet<Pair<Fragment, Fragment>>();

		for (Pair<Fragment, Fragment> pair : matching
				.getFragmentCorrespondences()) {
			Set<FlowNode> nodes1 = getEventsAndActivites(pair.getValue0());
			Set<FlowNode> nodes2 = getEventsAndActivites(pair.getValue1());
//			LOG.debug("Comparing behaviour of " + pair.getValue0() + "("
//					+ nodes1.size() + ") and " + pair.getValue1() + "("
//					+ nodes2.size() + ") ");
			boolean match = behaviourProfilesMatch(nodes1, nodes2, matching
					.getAnalysis1().getBehavioralProfile(), matching
					.getAnalysis2().getBehavioralProfile(),
					matching.getNodeCorrespondences());
			if (match) {
				LOG.info("Behaviour equivalent fragments: " + pair.getValue0()
						+ " / " + pair.getValue1());
				pairs.add(Pair.with(pair.getValue0(), pair.getValue1()));
			} else {
				LOG.debug(pair.getValue0() + " and " + pair.getValue1()
						+ " are not behaviour equivalent.");
			}

		}
		return pairs;
	}

	private static boolean behaviourProfilesMatch(Set<FlowNode> nodes1,
			Set<FlowNode> nodes2, BehavioralProfile profileOfProcess1,
			BehavioralProfile profileOfProcess2,
			Set<Pair<FlowNode, FlowNode>> nodeMappings) {
		boolean profilesMatch = true;
		for (FlowNode n1 : nodes1) {
			for (FlowNode n2 : nodes1) {
//				LOG.debug("Comparing " + n1.getName() + " and " + n2.getName());
				RelationType r1 = profileOfProcess1.get(n1, n2);
				FlowNode targetNode1 = nodeMappings
						.stream()
						.filter(p -> p.getValue0().equals(n1)
								&& nodes2.contains(p.getValue1())).findAny()
						.get().getValue1();
				FlowNode targetNode2 = nodeMappings
						.stream()
						.filter(p -> p.getValue0().equals(n2)
								&& nodes2.contains(p.getValue1())).findAny()
						.get().getValue1();
				RelationType r2 = profileOfProcess2.get(targetNode1,
						targetNode2);
				if (r1 != r2) {
					profilesMatch = false;
				}
			}
		}
		return profilesMatch;
	}

	// TODO: move to Fragment
	private static Set<FlowNode> getEventsAndActivites(Fragment fragment) {
		return fragment.getContainedFlowNodes(f -> f instanceof Event
				|| f instanceof Activity);

	}

}
