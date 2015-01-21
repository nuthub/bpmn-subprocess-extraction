package edu.udo.cs.ls14.jf.bpmnapplication.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessExtractionUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessMatchingUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessMatcher;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;
import edu.udo.cs.ls14.jf.registry.Registries;
import edu.udo.cs.ls14.jf.transformation.ProcessExtractor;

public class ProcessExtractionHybridTest {

	private static final String TARGET_DIR = "/tmp/";

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testComplete() throws Exception {
		String pathname = "/bpmn/complete/";
		String basename1 = "complete1";
		String basename2 = "complete2";
		List<String> nodes = Arrays.asList("n_start", "T1", "T2", "T3", "T4",
				"T5", "T6", "E1", "E2", "E3", "E4", "n_end");
		ProcessExtraction extraction = runTest(pathname, basename1, nodes, basename2, nodes);
		assertEquals(4, extraction.getResults().size());
	}

	@Test
	public void testCompleteLabelled() throws Exception {
		String pathname = "/bpmn/completeLabelled/";
		String basename1 = "complete1labelled";
		String basename2 = "complete2labelled";
		List<String> nodes1 = Arrays.asList("n_start", "Anspruch pruefen", "Lieferschein erstellen", "Rechnung erstellen", "Ware verpacken",
				"Betrag erstatten", "Gutschein ausstellen", "E1", "E2", "E3", "E4", "n_end");
		List<String> nodes2 = Arrays.asList("n_start", "Anspruch pruefen", "Lieferschein anfertigen", "Rechnung anfertigen", "Artikel verpacken",
				"Betrag erstatten", "Gutschein ausstellen", "E1", "E2", "E3", "E4", "n_end");
		ProcessExtraction extraction = runTest(pathname, basename1, nodes1, basename2, nodes2);
		assertEquals(4, extraction.getResults().size());
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
	public void testExclusiveGateway() throws Exception {
		String basename1 = "looping-xor";
		String basename2 = "xor-example";
		List<String> nodes = Arrays.asList();
		runTest("/bpmn/exclusiveGateway/", basename1, nodes, basename2, nodes);
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

	@Test
	public void testSequences() throws Exception {
		String basename1 = "sequence1";
		String basename2 = "sequence2";
		List<String> nodes = Arrays.asList();
		runTest("/bpmn/sequences/", basename1, nodes, basename2, nodes);
	}

	private ProcessExtraction runTest(String pathname, String basename1,
			List<String> nodes1, String basename2, List<String> nodes2)
			throws Exception {
		String targetDir = (TARGET_DIR + pathname + "/").replaceAll("//", "/");
		new File(targetDir).mkdirs();
		// Pre
		//
		// SubProcessExtraction processEngine = new SubProcessExtraction();
		// processEngine.init();

		// START
		// 1a. analyze model1
		Definitions def1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(pathname + basename1 + ".bpmn")
						.getPath());
		ProcessAnalysis analysis1 = ProcessAnalyzer.analyzeAndDebug(def1,
				pathname, basename1, targetDir, nodes1);
		ProcessAnalysisUtil.writeToFile(targetDir + basename1 + "-analysis."
				+ BpmnAnalysisPackage.eNAME, analysis1);
		// ProcessAnalysis analysis1 = processEngine.runProcessAnalysis(def1);

		// 1b. analyze model2
		Definitions def2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(pathname + basename2 + ".bpmn")
						.getPath());
		ProcessAnalysis analysis2 = ProcessAnalyzer.analyzeAndDebug(def2,
				pathname, basename2, targetDir, nodes2);
		ProcessAnalysisUtil.writeToFile(targetDir + basename2 + "-analysis."
				+ BpmnAnalysisPackage.eNAME, analysis2);
		// ProcessAnalysis analysis2 = processEngine.runProcessAnalysis(def2);

		// 2. match models
		// ProcessMatching matching_ =
		// processEngine.runProcessMatching(analysis1,
		// analysis2);
		// ProcessMatchingUtil.writeToFile(targetDir +
		// "bpmn/matching.bpmnmatching",
		// matching_);
		ProcessMatching matching = ProcessMatcher.match(analysis1, analysis2);
		ProcessMatchingUtil.writeToFile(targetDir + basename1 + "_" + basename2
				+ "_matching." + BpmnMatchingPackage.eNAME, matching);

		// 3. extract models
		// BPMN variant (funktioniert nicht)
		// ProcessExtraction extraction1 = processEngine
		// .runProcessExtraction(matching);
		// ProcessExtractionUtil.writeToFile(targetDir + "bpmn/"
		// + "extraction.bpmntransformation", extraction1);
		// ProcessExtractionUtil.writeResults(targetDir + "bpmn/", extraction1);
		// pure Java variant (funktioniert)
		ProcessExtraction extraction = ProcessExtractor.extract(matching);
		ProcessExtractionUtil.writeToFile(targetDir + basename1 + "_"
				+ basename2 + "_extraction." + BpmnTransformationPackage.eNAME,
				extraction);
		ProcessExtractionUtil.writeResults(targetDir, extraction);

		// END

		// TODO: assertions
		return extraction;
	}
}
