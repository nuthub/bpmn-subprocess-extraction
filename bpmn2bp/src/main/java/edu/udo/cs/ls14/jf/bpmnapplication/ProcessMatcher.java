package edu.udo.cs.ls14.jf.bpmnapplication;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.nodematching.NodeMatcher;
import edu.udo.cs.ls14.jf.processmatching.FragmentPairBuilder;
import edu.udo.cs.ls14.jf.processmatching.FragmentRankerSize;
import edu.udo.cs.ls14.jf.processmatching.InequivalentBehaviourFCFilter;
import edu.udo.cs.ls14.jf.processmatching.InequivalentConditionsFCFilter;
import edu.udo.cs.ls14.jf.processmatching.InequivalentNodeFCFilter;
import edu.udo.cs.ls14.jf.processmatching.NestedFCFilter;
import edu.udo.cs.ls14.jf.processmatching.SequentialFCJointer;
import edu.udo.cs.ls14.jf.processmatching.TrivialFCFilter;

public class ProcessMatcher {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessMatcher.class);

	public static ProcessMatching createProcessMatching(
			Definitions definitions1, Definitions definitions2)
			throws Exception {
		Process process1 = ProcessUtil.getProcessFromDefinitions(definitions1);
		Process process2 = ProcessUtil.getProcessFromDefinitions(definitions2);

		// create processmatching object
		ProcessMatching m = BpmnMatchingFactory.eINSTANCE
				.createProcessMatching();
		// analyze process1
		LOG.debug("Analyze process 1: " + process1.getName());
		m.setAnalysis1(ProcessAnalyzer.analyze(definitions1));

		// analyze process2
		LOG.debug("Analyze process 2: " + process2.getName());
		m.setAnalysis2(ProcessAnalyzer.analyze(definitions2));

		// determine node correspondences
		LOG.debug("Matching nodes");
		m.setNodeMatching(NodeMatcher.getCorrespondences(process1, process2));
		LOG.debug(m.getNodeMatching().getPairs().size()
				+ " node correspondences.");

		// get all possible Fragment Correspondences
		LOG.info("create all possible fragment correspondences.");
		m.setFragmentMatching(FragmentPairBuilder.getAllPairs(m.getAnalysis1(),
				m.getAnalysis2()));
		LOG.info(m.getFragmentMatching().getPairs().size()
				+ " possible fragment correspondences.");

		// Filter out fragments, that are not node equivalent
		LOG.info("Filter out matches that are not node equivalent.");
		m.setFragmentMatching(InequivalentNodeFCFilter.filter(
				m.getNodeMatching(), m.getFragmentMatching()));
		LOG.info(m.getFragmentMatching().getPairs().size()
				+ " fragment correspondences left.");

		// Filter out matches with non-equivalent behaviour
		LOG.info("Filter out matches with inequivalent behavioural profiles.");
		m.setFragmentMatching(InequivalentBehaviourFCFilter.filter(
				m.getFragmentMatching(), m.getNodeMatching(), m.getAnalysis1(),
				m.getAnalysis2()));
		LOG.info(m.getFragmentMatching().getPairs().size()
				+ " fragment correspondences left.");

		// Filter out pairs with non-matching conditions
		LOG.info("Filter out matches with inequivalent conditional profiles");
		m.setFragmentMatching(InequivalentConditionsFCFilter.filter(
				m.getFragmentMatching(), m.getNodeMatching(), m.getAnalysis1(),
				m.getAnalysis2()));
		LOG.info(m.getFragmentMatching().getPairs().size()
				+ " fragment correspondences left.");

		// Filter out nested fragment matches
		LOG.info("Filter out nested matches.");
		m.setFragmentMatching(NestedFCFilter.filter(m.getFragmentMatching()));
		LOG.info(m.getFragmentMatching().getPairs().size()
				+ " fragment correspondences left.");

		// build sequence unions
		LOG.info("Union matches in sequence.");
		m.setFragmentMatching(SequentialFCJointer.joinSequences(m
				.getFragmentMatching()));
		LOG.info(m.getFragmentMatching().getPairs().size()
				+ " fragment correspondences left.");

		// filter out trivial fragments (|nodes| < 2)
		LOG.info("Filter out trivial matches.");
		m.setFragmentMatching(TrivialFCFilter.filter(m.getFragmentMatching()));
		LOG.info(m.getFragmentMatching().getPairs().size()
				+ " fragment correspondences left.");

		// determine ranking in fragment pairs
		LOG.info("Determining order in fragment pairs");
		FragmentRankerSize ranker = new FragmentRankerSize();
		m.setFragmentMatching(ranker.rankFragments(m.getFragmentMatching()));

		return m;
	}
}
