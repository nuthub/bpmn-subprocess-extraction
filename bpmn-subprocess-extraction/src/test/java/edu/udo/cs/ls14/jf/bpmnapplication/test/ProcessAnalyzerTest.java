package edu.udo.cs.ls14.jf.bpmnapplication.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.registry.Registries;

public class ProcessAnalyzerTest {
	private static final String TARGET_DIR = "/tmp/";

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	private int getPstSize(ProcessAnalysis analysis) {
		return ((ProcessStructureTree) analysis.getResults().get("pst"))
				.getFragments().size();
	}

	private int getCpSize(ProcessAnalysis analysis) {
		return ((ConditionalProfile) analysis.getResults().get(
				"conditionalProfile")).getRelations().size();
	}

	/**
	 * Evaluation test model 1 TODO: assertions
	 * 
	 * @throws Exception
	 */
	@Test
	public void testComplete1() throws Exception {
		String pathname = "/bpmn/complete/";
		String basename = "complete1";
		List<String> nodes = Arrays.asList("n_start", "T1", "T2", "T3", "T4",
				"T5", "T6", "E1", "E2", "E3", "E4", "n_end");
		ProcessAnalysis analysis = runTest(pathname, basename, nodes);
		assertNotNull(analysis);
	}

	/**
	 * Evaluation test model 2 TODO: assertions
	 * 
	 * @throws Exception
	 */
	@Test
	public void testComplete2() throws Exception {
		String pathname = "/bpmn/complete/";
		String basename = "complete2";
		List<String> nodes = Arrays.asList("n_start", "T1", "T2", "T3", "T4",
				"T5", "T6", "E1", "E2", "E3", "E4", "n_end");
		ProcessAnalysis analysis = runTest(pathname, basename, nodes);
		assertNotNull(analysis);
	}

	@Test
	public void testAnalysisParallelism1() throws Exception {
		String pathname = "/bpmn/parallelGateway/";
		String basename = "parallelism1";
		List<String> nodes = Arrays.asList();
		ProcessAnalysis analysis = runTest(pathname, basename, nodes);
		assertEquals(6, getPstSize(analysis));
		assertEquals(7, getCpSize(analysis));
	}

	@Test
	public void testAnalysisParallelism2() throws Exception {
		String pathname = "/bpmn/parallelGateway/";
		String basename = "parallelism1";
		List<String> nodes = Arrays.asList();
		ProcessAnalysis analysis = runTest(pathname, basename, nodes);
		assertEquals(6, getPstSize(analysis));
		assertEquals(7, getCpSize(analysis));
	}

	@Test
	public void testAnalysisConditionSequence1() throws Exception {
		String pathname = "/bpmn/conditionalFlow/";
		String basename = "conditionSequence1";
		List<String> nodes = Arrays.asList();
		ProcessAnalysis analysis = runTest(pathname, basename, nodes);
		assertEquals(6, getPstSize(analysis));
		assertEquals(6, getCpSize(analysis));
	}

	@Test
	public void testAnalysisConditionSequence2() throws Exception {
		String pathname = "/bpmn/conditionalFlow/";
		String basename = "conditionSequence2";
		List<String> nodes = Arrays.asList();
		ProcessAnalysis analysis = runTest(pathname, basename, nodes);
		assertEquals(6, getPstSize(analysis));
		assertEquals(6, getCpSize(analysis));
	}

	private ProcessAnalysis runTest(String pathname, String basename,
			List<String> nodes) throws Exception {
		Definitions def = Bpmn2ResourceSet.getInstance()
				.loadDefinitions(
						getClass().getResource(pathname + basename + ".bpmn")
								.getPath());
		ProcessAnalysis analysis = ProcessAnalyzer.analyzeAndDebug(def,
				pathname, basename, TARGET_DIR, nodes);
		return analysis;
	}

}
