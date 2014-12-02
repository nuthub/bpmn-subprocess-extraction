package edu.udo.cs.ls14.jf.bpmnapplication.test;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Before;
import org.junit.Test;

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

public class ProcessExtractionTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testSequenceSequence2() throws Exception {
		String basename1 = "sequence";
		String basename2 = "sequence2";
		runTest(basename1, basename2, "");
	}

	@Test
	public void testPM1PM2() throws Exception {
		String basename1 = "PM1-mit-Fragment1";
		String basename2 = "PM2-mit-Fragment1";
		runTest(basename1, basename2, "");
	}

	@Test
	public void testXorExampleLoopingXor() throws Exception {
		String basename1 = "xor-example";
		String basename2 = "looping-xor";
		runTest(basename1, basename2, "");
	}

	@Test
	public void testConditionSequenceConditionSequence2() throws Exception {
		String basename1 = "conditionSequence";
		String basename2 = "conditionSequence2";
		runTest(basename1, basename2, "conditionalFlow");
	}

	private void runTest(String name1, String name2, String path)
			throws Exception {
		runTest(name1, name2, path, path);
	}

	private void runTest(String name1, String name2, String path, String key)
			throws Exception {
		String targetDir = "/tmp/applicationtest/" + key + "/";

		// Pre
		Bpmn2ResourceSet resSet = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/" + path + "/").getPath());
		Definitions def1 = EcoreUtil.copy(resSet.loadDefinitions(name1
				+ ".bpmn"));
		Definitions def2 = EcoreUtil.copy(resSet.loadDefinitions(name2
				+ ".bpmn"));

		// START
		// 1a. analyze process1
		ProcessAnalysis analysis1 = ProcessAnalyzer.analyze(def1);
		ProcessAnalysisUtil.writeToFile(targetDir + "analysis1.xml", analysis1);
		// 1b. analyze process2
		ProcessAnalysis analysis2 = ProcessAnalyzer.analyze(def2);
		ProcessAnalysisUtil.writeToFile(targetDir + "analysis2.xml", analysis2);
		// 2. match process1 and process2
		ProcessMatching matching = ProcessMatcher.match(analysis1, analysis2);
		ProcessMatchingUtil.writeToFile(targetDir + "matching.xml", matching);
		// 3. extract processes
		ProcessExtraction extraction = ProcessExtractor.extract(matching);
		// END

		// Post
		ProcessExtractionUtil.writeToFile(targetDir + path + "extraction.xml",
				extraction);
		ProcessExtractionUtil.writeResults(targetDir + path, extraction);

		// TODO: assertions
	}
}
