package edu.udo.cs.ls14.jf.processmatching;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowNode;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.behaviorprofile.BehavioralProfile;
import edu.udo.cs.ls14.jf.behaviorprofile.RelationType;
import edu.udo.cs.ls14.jf.pst.Fragment;

public class FragmentMatcher {
	private final static Logger LOG = LoggerFactory
			.getLogger(FragmentMatcher.class);

	/**
	 * Returns all fragment correspondences
	 * 
	 * @param process1
	 * @param fragmentsOfProcess1
	 * @param profileOfProcess1
	 * @param process2
	 * @param fragmentsOfProcess2
	 * @param profileOfProcess2
	 * @param nodeMapping
	 * @return
	 */
	public static Set<Pair<Fragment, Fragment>> getCorrespondences(
			ProcessAnalysis analysis1, ProcessAnalysis analysis2,
			Set<Pair<FlowNode, FlowNode>> nodeMappings) {

		Set<Pair<Fragment, Fragment>> mapping = new HashSet<Pair<Fragment, Fragment>>();
		for (Fragment f1 : analysis1.getPst().getFragments()) {
			LOG.debug("---");
			LOG.debug("Checking if process's 1 " + f1 + " is a candidate");
			// Get Events and Activities From f1
			Set<FlowNode> nodes1 = f1
					.getContainedFlowNodes(n -> n instanceof Event
							|| n instanceof Activity);
			// Wenn nicht f. jeden Knoten u \in nodes1 ein Paar(u,v) existiert,
			// dann ist Fragment uninteressant
			if (!nodeMappings.stream().map(p -> p.getValue0())
					.collect(Collectors.toSet()).containsAll(nodes1)) {
				LOG.debug("Not all nodes are mapped, continue with next fragment of process 1.");
				continue;
			}
			LOG.debug("All nodes are contained in mapping.");
			LOG.debug("Relevant nodes are : "
					+ nodes1.stream().map(n -> n.getName())
							.collect(Collectors.toSet()));
			// Check fragments of process2
			for (Fragment f2 : analysis2.getPst().getFragments()) {
				Set<FlowNode> nodes2 = f2
						.getContainedFlowNodes(n -> n instanceof Event
								|| n instanceof Activity);
				// fragmente sind "node matching" wenn
				// für jeden Knoten u \in nodes1 und
				// jeden Knoten v \in nodes2
				// ein Paar (u,v) in nodeMappings existiert
				Set<Pair<FlowNode, FlowNode>> relevantMappings = nodeMappings
						.stream()
						.filter(p -> nodes1.contains(p.getValue0())
								&& nodes2.contains(p.getValue1()))
						.collect(Collectors.toSet());
				if (!nodes2.equals(relevantMappings.stream()
						.map(p -> p.getValue1()).collect(Collectors.toSet()))
						|| !nodes1.equals(relevantMappings.stream()
								.map(p -> p.getValue0())
								.collect(Collectors.toSet()))) {
					continue;
				}
				LOG.debug("Found node matching fragments: ");
				LOG.debug("Process 1: "
						+ f1
						+ nodes1.stream().map(n -> n.getName())
								.collect(Collectors.toSet()));
				LOG.debug("Process 2: "
						+ f2
						+ nodes2.stream().map(n -> n.getName())
								.collect(Collectors.toSet()));
				// Compare behaviour profile
				LOG.debug("Have to compare behaviour profiles");
				boolean behaviourMatches = behaviourProfilesMatch(nodes1,
						nodes2, analysis1.getBehavioralProfile(),
						analysis2.getBehavioralProfile(), nodeMappings);
				if (behaviourMatches) {
					LOG.info("Behaviour equivalent fragments: " + f1 + " / "
							+ f2);
					mapping.add(Pair.with(f1, f2));
				} else {
					LOG.debug(f1 + " and " + f2
							+ " are not behaviour equivalent.");
				}
			}
		}
		return mapping;
	}

	private static boolean behaviourProfilesMatch(Set<FlowNode> nodes1,
			Set<FlowNode> nodes2, BehavioralProfile profileOfProcess1,
			BehavioralProfile profileOfProcess2,
			Set<Pair<FlowNode, FlowNode>> nodeMappings) {
		boolean profilesMatch = true;
		for (FlowNode n1 : nodes1) {
			for (FlowNode n2 : nodes1) {
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

}
