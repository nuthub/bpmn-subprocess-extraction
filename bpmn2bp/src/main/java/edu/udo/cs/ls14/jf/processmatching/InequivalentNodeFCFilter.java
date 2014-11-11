package edu.udo.cs.ls14.jf.processmatching;

import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.FlowNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodePair;

public class InequivalentNodeFCFilter {

	private final static Logger LOG = LoggerFactory
			.getLogger(InequivalentNodeFCFilter.class);

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
	public static FragmentMatching filter(NodeMatching nodeMatching,
			FragmentMatching matchingIn) {

		FragmentMatching matchingOut = BpmnMatchingFactory.eINSTANCE
				.createFragmentMatching();

		for (FragmentPair pair : matchingIn.getPairs()) {
			LOG.debug("Checking pair " + pair);
			// Get nodes of Fragments
			Set<FlowNode> nodes1 = FragmentUtil.getEventsAndActivites(pair
					.getA());
			Set<FlowNode> nodes2 = FragmentUtil.getEventsAndActivites(pair
					.getB());
			Set<NodePair> relevantMappings = nodeMatching
					.getPairs()
					.stream()
					.filter(p -> nodes1.contains(p.getA())
							&& nodes2.contains(p.getB()))
					.collect(Collectors.toSet());
			Set<FlowNode> nodes1Mapped = relevantMappings.stream()
					.map(p -> p.getA()).collect(Collectors.toSet());
			Set<FlowNode> nodes2Mapped = relevantMappings.stream()
					.map(p -> p.getB()).collect(Collectors.toSet());
			if (!nodes1.equals(nodes1Mapped) || !nodes2.equals(nodes2Mapped)) {
				LOG.debug("Fragments are not node equivalent.");
				continue;
			}
			LOG.info("Fragments are node equivalent: " + pair.getA() + " / "
					+ pair.getB());
			LOG.debug("Nodes of Process 1: "
					+ nodes1.stream().map(n -> n.getName())
							.collect(Collectors.toSet()));
			LOG.debug("Nodes of Process 2: "
					+ nodes2.stream().map(n -> n.getName())
							.collect(Collectors.toSet()));
			matchingOut.getPairs().add(pair);
		}
		return matchingOut;
	}

}
