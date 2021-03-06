package edu.udo.cs.ls14.jf.bpmnmatching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.fragmentmatching.FragmentPairFilterBehavior;
import edu.udo.cs.ls14.jf.bpmnmatching.fragmentmatching.FragmentPairFilterConditions;
import edu.udo.cs.ls14.jf.bpmnmatching.fragmentmatching.FragmentPairFilterNestings;
import edu.udo.cs.ls14.jf.bpmnmatching.fragmentmatching.FragmentPairFilterNodes;
import edu.udo.cs.ls14.jf.bpmnmatching.fragmentmatching.FragmentPairJointerSequential;
import edu.udo.cs.ls14.jf.bpmnmatching.nodematching.NodePairFilter;
import edu.udo.cs.ls14.jf.bpmnmatching.util.ProcessMatchingFactory;

/**
 * 
 * Calls all necessary steps to determine valid minimal Fragment matching.
 * 
 * @author Julian Flake
 *
 */
public class ProcessMatcherImpl implements IProcessMatcher {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessMatcherImpl.class);

	/**
	 * {@inheritDoc}
	 */
	public ProcessMatching match(ProcessAnalysis analysis1,
			ProcessAnalysis analysis2) throws Exception {

		// create ProcessMatching object
		LOG.info("create ProcessMatching with all possible fragment and node correspondences.");
		// ProcessMatching m = ProcessMatchingFactory.createFullMatching(
		// analysis1, analysis2);
		ProcessMatching m = ProcessMatchingFactory.createEmptyMatching(
				analysis1, analysis2);
		m.setNodeMatching(ProcessMatchingFactory.getFullNodeMatching(m
				.getAnalysis1().getDefinitions(), m.getAnalysis2()
				.getDefinitions()));
		m.setFragmentMatching(ProcessMatchingFactory.getFullFragmentMatching(
				m.getAnalysis1(), m.getAnalysis2()));

		LOG.info(m.getNodeMatching().getPairs().size()
				+ " possible node correspondences.");
		LOG.info(m.getFragmentMatching().getPairs().size()
				+ " possible fragment correspondences.");

		// filter out node correspondences with inequivalent nodes
		LOG.debug("Matching nodes");
		m.setNodeMatching(NodePairFilter.filter(m.getNodeMatching()));
		LOG.debug(m.getNodeMatching().getPairs().size()
				+ " node correspondences.");

		// Filter out fragments, that are not node equivalent
		LOG.info("Filter out matches that are not node equivalent.");
		m.setFragmentMatching(FragmentPairFilterNodes.filter(
				m.getFragmentMatching(), m.getNodeMatching()));
		LOG.info(m.getFragmentMatching().getPairs().size()
				+ " fragment correspondences left.");

		// Filter out matches with non-equivalent behaviour
		LOG.info("Filter out matches with inequivalent behavioural profiles.");
		m.setFragmentMatching(FragmentPairFilterBehavior.filter(
				m.getFragmentMatching(), m.getNodeMatching(), m.getAnalysis1(),
				m.getAnalysis2()));
		LOG.info(m.getFragmentMatching().getPairs().size()
				+ " fragment correspondences left.");

		// Filter out pairs with non-matching conditions
		LOG.info("Filter out matches with inequivalent conditional profiles");
		m.setFragmentMatching(FragmentPairFilterConditions.filter(
				m.getFragmentMatching(), m.getNodeMatching(), m.getAnalysis1(),
				m.getAnalysis2()));
		LOG.info(m.getFragmentMatching().getPairs().size()
				+ " fragment correspondences left.");

		// Filter out nested fragment matches
		LOG.info("Filter out nested matches.");
		m.setFragmentMatching(FragmentPairFilterNestings.filter(m
				.getFragmentMatching()));
		LOG.info(m.getFragmentMatching().getPairs().size()
				+ " fragment correspondences left.");

		// build sequence unions
		LOG.info("Union matches in sequence.");
		m.setFragmentMatching(FragmentPairJointerSequential.join(m
				.getFragmentMatching()));
		LOG.info(m.getFragmentMatching().getPairs().size()
				+ " fragment correspondences left.");

		// filter out trivial fragments (|nodes| < 2)
//		LOG.info("Filter out trivial matches.");
//		m.setFragmentMatching(FragmentPairFilterTrivial.filter(m
//				.getFragmentMatching()));
//		LOG.info(m.getFragmentMatching().getPairs().size()
//				+ " fragment correspondences left.");

		return m;
	}
}
