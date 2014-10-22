package edu.udo.cs.ls14.jf.processmatching;

import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.analysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class ProcessMatchingChain {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessMatchingChain.class);

	public static ProcessMatching getCandidates(Resource res1, Resource res2)
			throws Exception {
		// analyze process1
		Process process1 = ProcessLoader.getProcessFromResource(res1);
		LOG.debug("Analyze process 1: " + process1.getName());
		ProcessAnalysis analysis1 = ProcessAnalyzer.analyze(res1);

		// analyze process2
		Process process2 = ProcessLoader.getProcessFromResource(res2);
		LOG.debug("Analyze process 2: " + process2.getName());
		ProcessAnalysis analysis2 = ProcessAnalyzer.analyze(res2);

		// Find all matches
		LOG.info("Find all matches.");
		ProcessMatching allMatching = ProcessMatcher.match(analysis1, analysis2);
		LOG.info(allMatching.getFragmentCorrespondences().size()
				+ " fragment correspondences left.");

		// Filter out pairs with non-matching conditions
		LOG.info("Filter out matches with inequivalent conditional profiles");
		ProcessMatching conditionEquivalentMatching = InequivalentConditionsFCFilter
				.filter(allMatching);
		LOG.info(conditionEquivalentMatching.getFragmentCorrespondences()
				.size() + " fragment correspondences left.");

		// Filter out nested fragment matches
		LOG.info("Filter out nested matches.");
		ProcessMatching filteredMatching = NestedFCFilter
				.filter(conditionEquivalentMatching);
		LOG.info(filteredMatching.getFragmentCorrespondences().size()
				+ " fragment correspondences left.");

		// build sequence unions
		LOG.info("Union matches in sequence.");
		ProcessMatching unionedMatching = SequentialFCJointer
				.joinSequences(filteredMatching);
		LOG.info(unionedMatching.getFragmentCorrespondences().size()
				+ " fragment correspondences left.");

		// filter out trivial fragments (|nodes| < 2)
		LOG.info("Filter out trivial matches.");
		ProcessMatching candidates = TrivialFCFilter.filter(unionedMatching);
		LOG.info(candidates.getFragmentCorrespondences().size()
				+ " fragment correspondences left.");

		return candidates;

	}

}
