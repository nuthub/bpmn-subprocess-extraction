package edu.udo.cs.ls14.jf.bpmnapplication.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessMatcher;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.registry.Registries;

public class ProcessMatcherTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testConditionalFlow() throws Exception {
		String pathname = "/bpmn/conditionalFlow/";
		String basename1 = "conditionSequence1";
		String basename2 = "conditionSequence2";
		System.out.println("Testing " + basename1 + " with " + basename2);
		List<String> nodes = Arrays.asList("Start", "Task 1",
				"Task 2", "Task 3",
				"Task 4", "End");
		ProcessMatching matching = runTest(pathname, basename1, nodes,
				basename2, nodes);
		assertEquals(4, matching.getNodeMatching().getPairs().size());
		assertEquals(1, matching.getFragmentMatching().getPairs().size());
	}

	@Test
	public void testParallelGateway() throws Exception {
		String pathname = "/bpmn/parallelGateway/";
		String basename1 = "parallelism1";
		String basename2 = "parallelism2";
		System.out.println("Testing " + basename1 + " with " + basename2);
		List<String> nodes1 = Arrays.asList("Start", "Task A",
				"Lieferschein erstellen", "Rechnung erstellen",
				"Waren verpacken", "Task B", "End");
		List<String> nodes2 = Arrays.asList("Start", "Task C",
				"Lieferschein erstellen", "Rechnung erstellen",
				"Ware verpacken", "Task D", "End");
		ProcessMatching matching = runTest(pathname, basename1, nodes1,
				basename2, nodes2);
		assertEquals(3, matching.getNodeMatching().getPairs().size());
		assertEquals(1, matching.getFragmentMatching().getPairs().size());
	}

	/**
	 * Evaluation model 1 + evaluation model 2
	 * 
	 * @throws Exception
	 */
	@Test
	public void testComplete() throws Exception {
		String pathname = "/bpmn/complete/";
		String basename1 = "complete1";
		String basename2 = "complete2";
		List<String> nodes = Arrays.asList("n_start", "T1", "T2", "T3", "T4",
				"T5", "T6", "E1", "E2", "E3", "E4", "n_end");
		ProcessMatching matching = runTest(pathname, basename1, nodes,
				basename2, nodes);
		assertEquals(10, matching.getNodeMatching().getPairs().size());
		assertEquals(2, matching.getFragmentMatching().getPairs().size());
	}

	private ProcessMatching runTest(String pathname, String basename1,
			List<String> nodes1, String basename2, List<String> nodes2)
			throws Exception {

		// analyze process1
		ProcessAnalysis analysis1 = ProcessAnalyzer.analyzeAndDebug(pathname,
				basename1, "/tmp/", nodes1);
		// analyze process2
		ProcessAnalysis analysis2 = ProcessAnalyzer.analyzeAndDebug(pathname,
				basename2, "/tmp/", nodes2);

		ProcessMatching matching = ProcessMatcher.match(analysis1, analysis2);
		return matching;
	}
//
//	private void printMatching(ProcessMatching matching) {
//		Predicate<FlowElement> filter = n -> n instanceof Event
//				|| n instanceof Activity;
//		matching.getFragmentMatching()
//				.getPairs()
//				.stream()
//				.forEach(
//						p -> System.out.println(p.getA()
//								+ " with "
//								+ FragmentUtil
//										.getFlowElements(p.getA(), filter)
//										.size()
//								+ " As/Es corresponds to "
//								+ p.getB()
//								+ " with "
//								+ FragmentUtil
//										.getFlowElements(p.getB(), filter)
//										.size() + " As/Es."));
//
//	}
}
