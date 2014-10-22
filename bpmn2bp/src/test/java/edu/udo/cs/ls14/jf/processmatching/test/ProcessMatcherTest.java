package edu.udo.cs.ls14.jf.processmatching.test;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.function.Predicate;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.analysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.processmatching.NestedFCFilter;
import edu.udo.cs.ls14.jf.processmatching.ProcessMatcher;
import edu.udo.cs.ls14.jf.processmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.SequentialFCJointer;
import edu.udo.cs.ls14.jf.processmatching.TrivialFCFilter;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class ProcessMatcherTest {

	@Test
	public void testPm1WithPm2() throws Exception {
		String basename1 = "PM1-mit-Fragment1";
		String basename2 = "PM2-mit-Fragment1";
		runTest(basename1, basename2, 3, 4, 1, 1, 1);
	}

	@Test
	public void testPm1WithPm3() throws Exception {
		String basename1 = "PM1-mit-Fragment1";
		String basename2 = "PM3-mit-Fragment2";
		runTest(basename1, basename2, 5, 5, 5, 5, 0);
	}

	@Test
	public void testPm2WithPm3() throws Exception {
		String basename1 = "PM2-mit-Fragment1";
		String basename2 = "PM3-mit-Fragment2";
		runTest(basename1, basename2, 5, 5, 5, 5, 0);
	}

	@Test
	public void testSequenceWithSequence2() throws Exception {
		String basename1 = "sequence";
		String basename2 = "sequence2";
		runTest(basename1, basename2, 5, 5, 5, 3, 1);
	}

	/**
	 * 4 steps:
	 * 
	 * <ol>
	 * <li>match</li>
	 * <li>filterNestedCorrespondences</li>
	 * <li>buildUnions</li>
	 * <li>removeTrivialFragments</li>
	 * </ol>
	 * 
	 * @author flake
	 *
	 */
	private ProcessMatching runTest(String basename1, String basename2,
			int nodeCorrespondences, int allFragmentCorrespondences,
			int filteredFragmentCorrespondences,
			int unionedFragmentCorrespondences,
			int nonTrivialFragmentCorrespondences) throws Exception {
		System.out.println("Testing " + basename1 + " with " + basename2);
		URL url1 = getClass().getResource("../../bpmn/" + basename1 + ".bpmn");
		URL url2 = getClass().getResource("../../bpmn/" + basename2 + ".bpmn");
		Resource res1 = ProcessLoader.getBpmnResource(url1);
		Resource res2 = ProcessLoader.getBpmnResource(url2);

		// analyze resources
		ProcessAnalysis analysis1 = ProcessAnalyzer.analyze(res1);
		ProcessAnalysis analysis2 = ProcessAnalyzer.analyze(res2);

		// Find all matches
		System.out.println("Find all matches.");
		ProcessMatching allMatching = ProcessMatcher.match(analysis1, analysis2);
		// Filter out nested fragment matches
		System.out.println("Filter out nested matches.");
		ProcessMatching filteredMatching = NestedFCFilter.filter(allMatching);
		// build sequence unions
		System.out.println("Union matches in sequence.");
		ProcessMatching unionedMatching = SequentialFCJointer
				.joinSequences(filteredMatching);
		// filter out trivial fragments (|nodes| < 2)
		System.out.println("Filter out trivial matches.");
		ProcessMatching nonTrivialMatching = TrivialFCFilter
				.filter(unionedMatching);

		// output all
		System.out.println(basename1 + " => " + basename2);
		System.out.println("All matches:");
		printMatching(allMatching);

		// output filtered
		System.out.println("non-nested matches:");
		printMatching(filteredMatching);

		// output unioned
		System.out.println("Sequence-unioned matches:");
		printMatching(unionedMatching);

		// output non-trivial
		System.out.println("non-trivial matches:");
		printMatching(nonTrivialMatching);

		// assertions
		assertEquals(nodeCorrespondences, allMatching.getNodeCorrespondences()
				.size());
		assertEquals(allFragmentCorrespondences, allMatching
				.getFragmentCorrespondences().size());
		assertEquals(filteredFragmentCorrespondences, filteredMatching
				.getFragmentCorrespondences().size());
		assertEquals(unionedFragmentCorrespondences, unionedMatching
				.getFragmentCorrespondences().size());
		assertEquals(nonTrivialFragmentCorrespondences, nonTrivialMatching
				.getFragmentCorrespondences().size());

		return allMatching;
	}

	private void printMatching(ProcessMatching matching) {
		Predicate<FlowNode> filter = n -> n instanceof Event
				|| n instanceof Activity;
		matching.getFragmentCorrespondences()
				.stream()
				.forEach(
						p -> System.out.println(p.getValue0()
								+ " with "
								+ p.getValue0().getContainedFlowNodes(filter)
										.size()
								+ " As/Es corresponds to "
								+ p.getValue1()
								+ " with "
								+ p.getValue1().getContainedFlowNodes(filter)
										.size() + " As/Es."));

	}
}
