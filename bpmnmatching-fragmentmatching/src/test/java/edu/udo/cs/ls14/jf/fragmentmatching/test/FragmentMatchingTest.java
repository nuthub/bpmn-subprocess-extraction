package edu.udo.cs.ls14.jf.fragmentmatching.test;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzerImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.nodematching.NodePairFilter;
import edu.udo.cs.ls14.jf.fragmentmatching.FragmentPairFilterBehavior;
import edu.udo.cs.ls14.jf.fragmentmatching.FragmentPairFilterConditions;
import edu.udo.cs.ls14.jf.fragmentmatching.FragmentPairFilterNestings;
import edu.udo.cs.ls14.jf.fragmentmatching.FragmentPairFilterNodes;
import edu.udo.cs.ls14.jf.fragmentmatching.FragmentPairJointerSequential;
import edu.udo.cs.ls14.jf.registry.Registries;

/**
 * <ol>
 * <li>ProcessAnalyzer.analyze(definitions1)</li>
 * <li>ProcessAnalyzer.analyze(definitions2)</li>
 * <li>ProcessMatchingFactory.createFullMatching(...)</li>
 * <li>NodePairFilter.filter(...)</li>
 * <li>FragmentPairFilterNodes.filter(...)</li>
 * <li>FragmentPairFilterBehavior.filter(...)</li>
 * <li>FragmentPairFilterConditions.filter(...)</li>
 * <li>FragmentPairFilterNestings.filter(...)</li>
 * <li>FragmentPairJointerSequential.joinSequences(...)</li>
 * <li>FragmentPairFilterTrivial.filter(...)</li>
 * <li>FragmentPairRankerSize.rankFragments(...)</li>
 * </ol>
 * 
 * @author flake
 *
 */
public class FragmentMatchingTest {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentMatchingTest.class);

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testComplete() throws Exception {
		runTest("/bpmn/complete/", "complete1", "complete2");
	}

	@Test
	public void testCompleteLabelles() throws Exception {
		runTest("/bpmn/completeLabelled/", "complete1labelled", "complete2labelled");
	}

	@Test
	public void testParallel() throws Exception {
		runTest("/bpmn/parallelGateway/", "parallelism1", "parallelism2");
	}

	@Test
	public void testExclusive() throws Exception {
		runTest("/bpmn/exclusiveGateway/", "xor-example", "looping-xor");
	}

	@Test
	public void testShortLoops() throws Exception {
		runTest("/bpmn/shortLoop/", "parallel", "shortloop");
	}

	@Test
	public void testConstructors() {
		new FragmentPairFilterBehavior();
		new FragmentPairFilterConditions();
		new FragmentPairFilterNestings();
		new FragmentPairFilterNodes();
		new FragmentPairJointerSequential();
	}
	
	private void runTest(String pathname, String basename1, String basename2)
			throws Exception {

		// analyze process1
		Definitions def1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(pathname + basename1 + ".bpmn")
						.getPath());
		ProcessAnalyzer analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis1 = analyzer.analyze(def1);
		// analyze process2
		Definitions def2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(pathname + basename2 + ".bpmn")
						.getPath());
		analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis2 = analyzer.analyze(def2);

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

	}
}
