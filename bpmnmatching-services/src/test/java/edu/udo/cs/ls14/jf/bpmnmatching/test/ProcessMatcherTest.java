package edu.udo.cs.ls14.jf.bpmnmatching.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.util.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.IProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzerImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.IProcessMatcher;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcherImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

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
		List<String> nodes = Arrays.asList("Start", "Task 1", "Task 2",
				"Task 3", "Task 4", "End");
		ProcessMatching matching = runTest(pathname, basename1, nodes,
				basename2, nodes);
		assertEquals(6, matching.getNodeMatching().getPairs().size());
		assertEquals(2, matching.getFragmentMatching().getPairs().size());
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
		assertEquals(5, matching.getNodeMatching().getPairs().size());
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
		assertEquals(12, matching.getNodeMatching().getPairs().size());
		assertEquals(4, matching.getFragmentMatching().getPairs().size());
	}

	private ProcessMatching runTest(String pathname, String basename1,
			List<String> nodes1, String basename2, List<String> nodes2)
			throws Exception {

		// analyze process1
		Definitions def1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(pathname + basename1 + ".bpmn")
						.getPath());
		IProcessAnalyzer analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis1 = analyzer.analyze(def1);
		// analyze process2
		Definitions def2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(pathname + basename2 + ".bpmn")
						.getPath());
		analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis2 = analyzer.analyze(def2);

		IProcessMatcher matcher = new ProcessMatcherImpl();
		ProcessMatching matching = matcher.match(analysis1, analysis2);
		return matching;
	}

}
