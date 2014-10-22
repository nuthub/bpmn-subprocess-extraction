package edu.udo.cs.ls14.jf.processmatching;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.resource.Resource;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.analysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.pst.Fragment;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class ProcessMatchingChain {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessMatchingChain.class);

	public static ProcessMatching getCandidates(Resource res1, Resource res2)
			throws Exception {
		// create matching object
		ProcessMatching matching = new ProcessMatching();

		// analyze process1
		Process process1 = ProcessLoader.getProcessFromResource(res1);
		LOG.debug("Analyze process 1: " + process1.getName());
		ProcessAnalysis analysis1 = ProcessAnalyzer.analyze(res1);
		matching.setAnalysis1(analysis1);

		// analyze process2
		Process process2 = ProcessLoader.getProcessFromResource(res2);
		LOG.debug("Analyze process 2: " + process2.getName());
		ProcessAnalysis analysis2 = ProcessAnalyzer.analyze(res2);
		matching.setAnalysis2(analysis2);

		// determine node correspondences
		LOG.debug("Matching nodes");
		Set<Pair<FlowNode, FlowNode>> nodeMappings = NodeMatcher
				.getCorrespondences(analysis1.getResource(),
						analysis2.getResource());
		matching.setNodeCorrespondences(nodeMappings);
		LOG.debug(nodeMappings.size() + " node correspondences: ");


		// get all possible Fragment Correspondences
		LOG.info("create all possible fragment correspondences.");
		Set<Pair<Fragment, Fragment>> allMappings = getAllMappings(matching);
		matching.setFragmentCorrespondences(allMappings);
		LOG.info(matching.getFragmentCorrespondences().size()
				+ " possible fragment correspondences.");

		// Filter out fragments, that are not node equivalent
		Set<Pair<Fragment, Fragment>> nodeEqMappings = InequivalentNodeFCFilter.filter
				(matching);
		matching.setFragmentCorrespondences(nodeEqMappings);

		// Filter out matches with non-equivalent behaviour
		LOG.info("Filter out matches with inequivalent behavioural profiles");
		Set<Pair<Fragment, Fragment>> behaviourEquivalentMatching = InequivalentBehaviourFCFilter
				.filter(matching);
		matching.setFragmentCorrespondences(behaviourEquivalentMatching);
		LOG.info(matching.getFragmentCorrespondences()
				.size() + " fragment correspondences left.");

		// Filter out pairs with non-matching conditions
		LOG.info("Filter out matches with inequivalent conditional profiles");
		Set<Pair<Fragment, Fragment>> conditionEquivalentMatching = InequivalentConditionsFCFilter
				.filter(matching);
		matching.setFragmentCorrespondences(conditionEquivalentMatching);
		LOG.info(matching.getFragmentCorrespondences()
				.size() + " fragment correspondences left.");

		// Filter out nested fragment matches
		LOG.info("Filter out nested matches.");
		Set<Pair<Fragment, Fragment>> filteredMatching = NestedFCFilter
				.filter(matching);
		matching.setFragmentCorrespondences(filteredMatching);
		LOG.info(matching.getFragmentCorrespondences().size()
				+ " fragment correspondences left.");

		// build sequence unions
		LOG.info("Union matches in sequence.");
		Set<Pair<Fragment, Fragment>> unionedMatching = SequentialFCJointer
				.joinSequences(matching);
		matching.setFragmentCorrespondences(unionedMatching);
		LOG.info(matching.getFragmentCorrespondences().size()
				+ " fragment correspondences left.");

		// filter out trivial fragments (|nodes| < 2)
		LOG.info("Filter out trivial matches.");
		Set<Pair<Fragment, Fragment>> candidates = TrivialFCFilter.filter(matching);
		matching.setFragmentCorrespondences(candidates);
		LOG.info(matching.getFragmentCorrespondences().size()
				+ " fragment correspondences left.");

		return matching;
	}

	private static Set<Pair<Fragment, Fragment>> getAllMappings(
			ProcessMatching matching) {
		Set<Pair<Fragment, Fragment>> pairs = new HashSet<Pair<Fragment, Fragment>>();
		for (Fragment f1 : matching.getAnalysis1().getPst().getFragments()) {
			for (Fragment f2 : matching.getAnalysis2().getPst().getFragments()) {
				pairs.add(Pair.with(f1, f2));
			}
		}
		return pairs;
	}

}
