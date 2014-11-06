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

import edu.udo.cs.ls14.jf.analysis.pst.FragmentOld;

public class InequivalentNodeFCFilterOld {

	private final static Logger LOG = LoggerFactory
			.getLogger(InequivalentNodeFCFilterOld.class);

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
	public static Set<Pair<FragmentOld, FragmentOld>> filter(ProcessMatching matching) {
		Set<Pair<FlowNode, FlowNode>> nodeMappings = matching
				.getNodeCorrespondences();
		Set<Pair<FragmentOld, FragmentOld>> pairs = new HashSet<Pair<FragmentOld, FragmentOld>>();

		for (Pair<FragmentOld, FragmentOld> pair : matching
				.getFragmentCorrespondencesOld()) {
			LOG.debug("Checking pair " + pair);
			// Get nodes of Fragments
			Set<FlowNode> nodes1 = getEventsAndActivites(pair.getValue0());
			Set<FlowNode> nodes2 = getEventsAndActivites(pair.getValue1());
			Set<Pair<FlowNode, FlowNode>> relevantMappings = nodeMappings
					.stream()
					.filter(p -> nodes1.contains(p.getValue0())
							&& nodes2.contains(p.getValue1()))
					.collect(Collectors.toSet());
			Set<FlowNode> nodes1Mapped = relevantMappings.stream()
					.map(p -> p.getValue0()).collect(Collectors.toSet());
			Set<FlowNode> nodes2Mapped = relevantMappings.stream()
					.map(p -> p.getValue1()).collect(Collectors.toSet());
			if (!nodes1.equals(nodes1Mapped) || !nodes2.equals(nodes2Mapped)) {
				LOG.debug("Fragments are not node equivalent.");
				continue;
			}
			LOG.info("Fragments are node equivalent: ");
			LOG.debug("Process 1: "
					+ pair.getValue0()
					+ nodes1.stream().map(n -> n.getName())
							.collect(Collectors.toSet()));
			LOG.debug("Process 2: "
					+ pair.getValue1()
					+ nodes2.stream().map(n -> n.getName())
							.collect(Collectors.toSet()));
			pairs.add(pair);
		}
		return pairs;
	}

	// TODO: move to Fragment
	private static Set<FlowNode> getEventsAndActivites(FragmentOld fragment) {
		return fragment.getContainedFlowNodes(n -> n instanceof Event
				|| n instanceof Activity);

	}
}
