package edu.udo.cs.ls14.jf.bpmn.application.test;

import java.util.UUID;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.application.SubProcessExtraction;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessExtractionUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessMatchingUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessMatcher;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;
import edu.udo.cs.ls14.jf.registry.Registries;
import edu.udo.cs.ls14.jf.transformation.ProcessExtractor;

public class ProcessExtractionHybridTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testExclusive() throws Exception {
		String basename1 = "looping-xor.bpmn";
		String basename2 = "xor-example.bpmn";
		runTest(basename1, basename2, "/bpmn/exclusiveGateway/",
				"exclusiveGateway");
	}

	@Test
	public void testParallelism() throws Exception {
		String basename1 = "parallelism1.bpmn";
		String basename2 = "parallelism2.bpmn";
		runTest(basename1, basename2, "/bpmn/parallelGateway/",
				"parallelGateway");
	}

	@Test
	public void testSequences() throws Exception {
		String basename1 = "sequence1.bpmn";
		String basename2 = "sequence2.bpmn";
		runTest(basename1, basename2, "/bpmn/sequences/", "sequences");
	}

	@Test
	public void testConditionalFlow() throws Exception {
		String basename1 = "conditionSequence1.bpmn";
		String basename2 = "conditionSequence2.bpmn";
		runTest(basename1, basename2, "/bpmn/conditionalFlow/",
				"conditionalFlow");
	}

	@Test
	@Deprecated
	public void testPM1PM2() throws Exception {
		String basename1 = "PM1-mit-Fragment1.bpmn";
		String basename2 = "PM2-mit-Fragment1.bpmn";
		runTest(basename1, basename2, "/edu/udo/cs/ls14/jf/bpmn/test/",
				"PM1PM2");
	}

	private void runTest(String name1, String name2, String path, String key)
			throws Exception {

		String targetDir = "/tmp/hybridtest/" + key + "/";

		// Pre
		Bpmn2ResourceSet resSet = Bpmn2ResourceSet.getInstance();
		Definitions def1 = EcoreUtil.copy(resSet.loadDefinitions(getClass()
				.getResource(path + "/").getPath() + name1));
		Definitions def2 = EcoreUtil.copy(resSet.loadDefinitions(getClass()
				.getResource(path + "/").getPath() + name2));

		resSet.createResource(
				URI.createFileURI(UUID.randomUUID().toString() + ".bpmn"))
				.getContents().add(def1);
		resSet.createResource(
				URI.createFileURI(UUID.randomUUID().toString() + ".bpmn"))
				.getContents().add(def2);

		SubProcessExtraction processEngine = new SubProcessExtraction();
		processEngine.init();

		// START
		// 1a. analyze model1
//		 ProcessAnalysis analysis1 = ProcessAnalyzer.analyze(def1);
		ProcessAnalysis analysis1 = processEngine.runProcessAnalysis(def1);
//		ProcessAnalysisUtil.writeToFile(targetDir + "analysis1.bpmnanalysis",
//				analysis1);

		// 1b. analyze model2
//		 ProcessAnalysis analysis2 = ProcessAnalyzer.analyze(def2);
		ProcessAnalysis analysis2 = processEngine.runProcessAnalysis(def2);
//		ProcessAnalysisUtil.writeToFile(targetDir + "analysis2.bpmnanalysis",
//				analysis2);

		// 2. match models
		ProcessMatching matching_ = processEngine.runProcessMatching(analysis1,
				analysis2);
		ProcessMatchingUtil.writeToFile(targetDir + "bpmn/matching.bpmnmatching",
				matching_);
		ProcessMatching matching = ProcessMatcher.match(analysis1, analysis2);
		ProcessMatchingUtil.writeToFile(targetDir + "java/matching.bpmnmatching",
				matching);

		// bis hier unproblematisch
		// ab hier problematisch

		// 3. extract models
		// BPMN variant (funktioniert nicht)
//		ProcessExtraction extraction1 = processEngine
//				.runProcessExtraction(matching);
//		ProcessExtractionUtil.writeToFile(targetDir + "bpmn/"
//				+ "extraction.xml", extraction1);
//		ProcessExtractionUtil.writeResults(targetDir + "bpmn/", extraction1);
		// pure Java variant (funktioniert)
		ProcessExtraction extraction2 = ProcessExtractor.extract(matching);
		ProcessExtractionUtil.writeResults(targetDir + "java/", extraction2);
		ProcessExtractionUtil.writeToFile(targetDir + "java/"
				+ "extraction.bpmntransformation", extraction2);

		// END

		// Post

		// TODO: assertions
	}
}
