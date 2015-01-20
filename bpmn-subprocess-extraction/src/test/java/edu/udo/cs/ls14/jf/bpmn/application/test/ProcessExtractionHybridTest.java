package edu.udo.cs.ls14.jf.bpmn.application.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessExtractionUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessMatcher;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;
import edu.udo.cs.ls14.jf.registry.Registries;
import edu.udo.cs.ls14.jf.transformation.ProcessExtractor;

public class ProcessExtractionHybridTest {

	private String TARGET_DIR = "/tmp/";

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testExclusiveGateway() throws Exception {
		String basename1 = "looping-xor";
		String basename2 = "xor-example";
		List<String> nodes = Arrays.asList();
		runTest("/bpmn/exclusiveGateway/", basename1, nodes, basename2, nodes);
	}

	@Test
	public void testSequences() throws Exception {
		String basename1 = "sequence1";
		String basename2 = "sequence2";
		List<String> nodes = Arrays.asList();
		runTest("/bpmn/sequences/", basename1, nodes, basename2, nodes);
	}

	/**
	 * Entstandene Modelle lassen sich im BPMN2-Modeler nicht öffnen
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConditionalFlow() throws Exception {
		String basename1 = "conditionSequence1";
		String basename2 = "conditionSequence2";
		List<String> nodes = Arrays.asList();
		runTest("/bpmn/conditionalFlow/", basename1, nodes, basename2, nodes);
	}

	@Test
	public void testParallelism() throws Exception {
		String basename1 = "parallelism1";
		String basename2 = "parallelism2";
		List<String> nodes1 = Arrays.asList("Start", "Lieferschein erstellen",
				"Rechnung erstellen", "Waren verpacken", "End");
		List<String> nodes2 = Arrays.asList("Start", "Lieferschein anfertigen",
				"Rechnung anfertigen", "Artikel verpacken", "End");
		runTest("/bpmn/parallelGateway/", basename1, nodes1, basename2, nodes2);
	}

	@Test(expected=Exception.class)
	public void testParallelismYaoqiang() throws Exception {
		String basename1 = "parallelism1-yaoqiang";
		String basename2 = "parallelism2-yaoqiang";
		List<String> nodes1 = Arrays.asList("Start", "Lieferschein erstellen",
				"Rechnung erstellen", "Waren verpacken", "End");
		List<String> nodes2 = Arrays.asList("Start", "Lieferschein anfertigen",
				"Rechnung anfertigen", "Artikel verpacken", "End");
		runTest("/bpmn/parallelGateway/", basename1, nodes1, basename2, nodes2);
	}

	@Test
	public void testComplete() throws Exception {
		String basename1 = "complete1";
		String basename2 = "complete2";
		List<String> nodes = Arrays.asList("n_start", "T1", "T2", "T3", "T4",
				"T5", "T6", "E1", "E2", "E3", "E4", "n_end");

		runTest("/bpmn/complete/", basename1, nodes, basename2, nodes);
	}

	@Test(expected=Exception.class)
	public void testCompleteYaoqiang() throws Exception {
		String basename1 = "complete1-yaoqiang";
		String basename2 = "complete2-yaoqiang";
		List<String> nodes = Arrays.asList("n_start", "T1", "T2", "T3", "T4",
				"T5", "T6", "E1", "E2", "E3", "E4", "n_end");

		runTest("/bpmn/complete/", basename1, nodes, basename2, nodes);
	}

	private void runTest(String path, String name1, List<String> nodes1,
			String name2, List<String> nodes2) throws Exception {

		// Pre
		//
		// SubProcessExtraction processEngine = new SubProcessExtraction();
		// processEngine.init();

		// START
		// 1a. analyze model1
		ProcessAnalysis analysis1 = ProcessAnalyzer.analyzeAndDebug(path,
				name1, TARGET_DIR, nodes1);
//		ProcessAnalysisUtil.writeToFile(targetDir
//				+ "java/analysis1.bpmnanalysis", analysis1);
		// ProcessAnalysis analysis1 = processEngine.runProcessAnalysis(def1);

		// 1b. analyze model2
		ProcessAnalysis analysis2 = ProcessAnalyzer.analyzeAndDebug(path,
				name2, TARGET_DIR, nodes2);
		// ProcessAnalysisUtil.writeToFile(targetDir
		// + "java/analysis2.bpmnanalysis", analysis2);
		// ProcessAnalysis analysis2 = processEngine.runProcessAnalysis(def2);

		// 2. match models
		// ProcessMatching matching_ =
		// processEngine.runProcessMatching(analysis1,
		// analysis2);
		// ProcessMatchingUtil.writeToFile(targetDir +
		// "bpmn/matching.bpmnmatching",
		// matching_);
		ProcessMatching matching = ProcessMatcher.match(analysis1, analysis2);
		// ProcessMatchingUtil.writeToFile(targetDir
		// + "java/matching1.bpmnmatching", matching);

		// 3. extract models
		// BPMN variant (funktioniert nicht)
		// ProcessExtraction extraction1 = processEngine
		// .runProcessExtraction(matching);
		// ProcessExtractionUtil.writeToFile(targetDir + "bpmn/"
		// + "extraction.bpmntransformation", extraction1);
		// ProcessExtractionUtil.writeResults(targetDir + "bpmn/", extraction1);
		// pure Java variant (funktioniert)
		ProcessExtraction extraction2 = ProcessExtractor.extract(matching);
		// ProcessExtractionUtil.writeToFile(targetDir + "java/"
		// + "extraction.bpmntransformation", extraction2);
		ProcessExtractionUtil.writeResults(TARGET_DIR + path, extraction2);

		// END

		// Post

		// TODO: assertions
	}
}
