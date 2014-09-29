package edu.udo.cs.ls14.jf.processmatching;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.analysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.behaviorprofile.BehavioralProfile;
import edu.udo.cs.ls14.jf.behaviorprofile.RelationType;
import edu.udo.cs.ls14.jf.pst.Fragment;

public class ProcessMatcher {
	private final static Logger LOG = LoggerFactory
			.getLogger(ProcessMatcher.class);

	public static ProcessMatching match(Process process1, Process process2)
			throws Exception {
		LOG.debug("Analyze process 1: " + process1.getName());
		ProcessAnalysis analysis1 = ProcessAnalyzer.analyze(process1);
		LOG.debug("Analyze process 2: " + process2.getName());
		ProcessAnalysis analysis2 = ProcessAnalyzer.analyze(process2);

		ProcessMatching matching = new ProcessMatching();
		// just set processes
		matching.setProcess1(process1);
		matching.setProcess2(process2);

		// create and save matching nodes
		LOG.debug("Matching nodes");
		Map<FlowNode, FlowNode> nodeMapping = NodeMatcher.getMapping(process1,
				process2);
		matching.setNodeCorrespondences(nodeMapping);

		// get equivalent Fragments
		Map<Fragment, Fragment> fragmentMapping = getMapping(process1,
				analysis1.getPst().getFragments(),
				analysis1.getBehavioralProfile(), process2, analysis2.getPst()
						.getFragments(), analysis2.getBehavioralProfile(),
				nodeMapping);
		matching.setFragmentCorrespondences(fragmentMapping);

		LOG.info("Done matching " + process1.getId() + " and "
				+ process2.getId());
		return matching;
	}

	/**
	 * Returns all fragment mappings
	 * 
	 * @param f1
	 *            , Set<Fragment> f2
	 * @param nodeMatchings
	 * @param p1
	 *            , BehavioralProfile p2
	 * @return
	 */
	private static Map<Fragment, Fragment> getMapping(Process process1,
			Set<Fragment> fragmentsOfProcess1,
			BehavioralProfile profileOfProcess1, Process process2,
			Set<Fragment> fragmentsOfProcess2,
			BehavioralProfile profileOfProcess2,
			Map<FlowNode, FlowNode> nodeMapping) {
		Map<Fragment, Fragment> mapping = new HashMap<Fragment, Fragment>();
		for (Fragment f1 : fragmentsOfProcess1) {
			LOG.debug("---");
			LOG.debug("Looking for candidates of process's 1 " + f1);
			// Get Events and Activities From f1
			Set<FlowNode> nodes1 = f1
					.getContainedFlowNodes(process1)
					.stream()
					.filter(n -> ((n instanceof Event) || (n instanceof Activity)))
					.collect(Collectors.toSet());
			LOG.debug("Relevant nodes are : "
					+ nodes1.stream().map(n -> n.getName())
							.collect(Collectors.toSet()));
			// if not all nodes are contained in node mapping, then this
			// fragment is of no interest
			if (nodeMapping.keySet().containsAll(nodes1)) {
				LOG.debug("All nodes are mapped, fragment has a node equivalent fragment in process 2.");
			} else {
				LOG.debug("Not all nodes are mapped, continue with next fragment of process 1.");
				continue;
			}
			// Check fragments of process2
			for (Fragment f2 : fragmentsOfProcess2) {
				// LOG.debug("Comparing with process's 2 " + f2);
				Set<FlowNode> nodes2 = f2
						.getContainedFlowNodes(process2)
						.stream()
						.filter(n -> ((n instanceof Event) || (n instanceof Activity)))
						.collect(Collectors.toSet());
				if (nodes2.equals(nodes1.stream()
						.map(n -> nodeMapping.get(n)).collect(Collectors.toSet()))) {
					LOG.debug("Found node matching fragments: " + f1 + " / "
							+ f2);
					LOG.debug("Have to compare profiles");
					boolean fragmentsHaveSameProfile = true;
					for (FlowNode n1p1 : nodes1) {
						for (FlowNode n2p1 : nodes1) {
							RelationType r1 = profileOfProcess1.get(n1p1, n2p1);
							RelationType r2 = profileOfProcess2.get(
									nodeMapping.get(n1p1),
									nodeMapping.get(n2p1));
							if (r1 != r2) {
								fragmentsHaveSameProfile = false;
							}
						}
					}
					if (fragmentsHaveSameProfile) {
						LOG.info("Found equivalent fragments: " + f1 + " / "
								+ f2);
						mapping.put(f1, f2);
					}
				}
			}
		}
		return mapping;
	}

}
