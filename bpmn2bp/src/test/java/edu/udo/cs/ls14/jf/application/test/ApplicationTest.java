package edu.udo.cs.ls14.jf.application.test;

import java.io.File;
import java.util.Map;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Test;

import edu.udo.cs.ls14.jf.application.Application;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.ProcessMatcher;

public class ApplicationTest {

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

	private void runTest(String basename1, String basename2, String path)
			throws Exception {
		// hit the process
		File targetDir = new File("/tmp/applicationtest/");
		targetDir.mkdirs();
		Bpmn2ResourceSet resSet = new Bpmn2ResourceSet("src/test/resources/edu/udo/cs/ls14/jf/bpmn/" + path + "/");
		Definitions definitions1 = EcoreUtil.copy(resSet.loadDefinitions(basename1 + ".bpmn"));
		Definitions definitions2 = EcoreUtil.copy(resSet.loadDefinitions(basename2 + ".bpmn"));
	
		ProcessMatching pMatching = ProcessMatcher.createProcessMatching(
				definitions1, definitions2);
		System.out.println(definitions1);
		System.out.println(definitions2);
		Application app = new Application();
		Map<String, Resource> result = app.extract(pMatching);
		System.out.println(result);
		System.out.println(app.getResourceSet().toContentString());
	}
}
