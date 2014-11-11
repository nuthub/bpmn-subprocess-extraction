package edu.udo.cs.ls14.jf.application.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Ignore;
import org.junit.Test;

import edu.udo.cs.ls14.jf.application.Application;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.ProcessMatchingChain;

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

	// TODO: Make assertions
	private void runTest(String basename1, String basename2, String path)
			throws Exception {
		// hit the process
		Application app = new Application();
		File targetDir = new File("/tmp/applicationtest/");
		targetDir.mkdirs();
		Definitions definitions1 = ProcessLoader.getDefinitions(getClass()
				.getResource(
						"/edu/udo/cs/ls14/jf/bpmn/" + path + "/" + basename1
								+ ".bpmn"));
		Definitions definitions2 = ProcessLoader.getDefinitions(getClass()
				.getResource(
						"/edu/udo/cs/ls14/jf/bpmn/" + path + "/" + basename2
								+ ".bpmn"));

		ProcessMatching pMatching = ProcessMatchingChain.createProcessMatching(
				definitions1, definitions2);
		Map<String, Resource> result = app.extract(pMatching);
		System.out.println(app.getResourceSet().toContentString());
	}
}
