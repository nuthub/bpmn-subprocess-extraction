package edu.udo.cs.ls14.jf.processmatching;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

public class ProcessMatcher {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessMatcher.class);

	public static ProcessMatching createProcessMatching(Definitions definitions1,
			Definitions definitions2) throws Exception {
		Process process1 = ProcessUtil
				.getProcessFromDefinitions(definitions1);
		Process process2 = ProcessUtil
				.getProcessFromDefinitions(definitions2);

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
		m.setFragmentMatching(getAllFragmentPairs(m.getAnalysis1(),
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

		return m;
	}

	private static FragmentMatching getAllFragmentPairs(
			ProcessAnalysis analysis1, ProcessAnalysis analysis2) {
		FragmentMatching matching = BpmnMatchingFactory.eINSTANCE
				.createFragmentMatching();
		ProcessStructureTree pst1 = ((ProcessStructureTree) analysis1
				.getResults().get("pst"));
		ProcessStructureTree pst2 = ((ProcessStructureTree) analysis2
				.getResults().get("pst"));
		for (Fragment f1 : pst1.getFragments()) {
			for (Fragment f2 : pst2.getFragments()) {
				FragmentPair pair = BpmnMatchingFactory.eINSTANCE
						.createFragmentPair();
				pair.setA(f1);
				pair.setB(f2);
				matching.getPairs().add(pair);
			}
		}
		return matching;
	}

}
