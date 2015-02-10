package edu.udo.cs.ls14.jf.bpmntransformation.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.transformation.ProcessTransformer;
import edu.udo.cs.ls14.jf.bpmn.transformation.ProcessTransformerImpl;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessMatchingUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessTransformationUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzerImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcher;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcherImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.registry.Registries;

public class ProcessTransformerTest {

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
		ProcessTransformation extraction = runTest(pathname, basename1, nodes, basename2, nodes);
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
		ProcessTransformation extraction = runTest(pathname, basename1, nodes1, basename2, nodes2);
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

	private ProcessTransformation runTest(String pathname, String basename1,
			List<String> nodes1, String basename2, List<String> nodes2)
			throws Exception {
		String targetDir = (TARGET_DIR + pathname + "/").replaceAll("//", "/");
		new File(targetDir).mkdirs();
		// START
		// 1a. analyze model1
		Definitions def1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(pathname + basename1 + ".bpmn")
						.getPath());
		ProcessAnalyzer analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis1 = analyzer.analyze(def1);
		ProcessAnalysisUtil.writeToFile(targetDir + basename1 + "."
				+ BpmnAnalysisPackage.eNAME, analysis1);

		// 1b. analyze model2
		Definitions def2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(pathname + basename2 + ".bpmn")
						.getPath());
		analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis2 = analyzer.analyze(def2);
		ProcessAnalysisUtil.writeToFile(targetDir + basename2 + "."
				+ BpmnAnalysisPackage.eNAME, analysis2);
		// 2. match models
		ProcessMatcher matcher = new ProcessMatcherImpl();
		ProcessMatching matching = matcher.match(analysis1, analysis2);
		ProcessMatchingUtil.writeToFile(targetDir + basename1 + "_" + basename2
				+ "." + BpmnMatchingPackage.eNAME, matching);
		// 3. extract models
		ProcessTransformer transformer = new ProcessTransformerImpl();
		ProcessTransformation extraction = transformer.transform(matching);
		ProcessTransformationUtil.writeToFile(targetDir + basename1 + "_"
				+ basename2 + "." + BpmnTransformationPackage.eNAME,
				extraction);
		ProcessTransformationUtil.writeResults(targetDir, extraction);
		// END

		// TODO: assertions
		return extraction;
	}
}
