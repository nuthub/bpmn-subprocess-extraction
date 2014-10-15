package edu.udo.cs.ls14.jf.application.test;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;
import org.javatuples.Pair;
import org.junit.Test;

import edu.udo.cs.ls14.jf.application.Application;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class ApplicationTest {

	@Test
	public void testSequenceSequence2() throws Exception {
		String basename1 = "sequence";
		String basename2 = "sequence2";
		runTest(basename1, basename2);
	}

	@Test
	public void testPM1PM2() throws Exception {
		String basename1 = "PM1-mit-Fragment1";
		String basename2 = "PM2-mit-Fragment1";
		runTest(basename1, basename2);
	}

	@Test
	public void testXorExampleLoopingXor() throws Exception {
		String basename1 = "xor-example";
		String basename2 = "looping-xor";
		runTest(basename1, basename2);
	}

	// TODO: Make assertions
	private void runTest(String basename1, String basename2) throws Exception {
		Resource res1 = ProcessLoader.getBpmnResource(getClass().getResource(
				"../../bpmn/" + basename1 + ".bpmn"));
		Resource res2 = ProcessLoader.getBpmnResource(getClass().getResource(
				"../../bpmn/" + basename2 + ".bpmn"));
		// hit the process
		Application app = new Application();
		Pair<String, Resource> model1 = Pair.with(basename1, res1);
		Pair<String, Resource> model2 = Pair.with(basename2, res2);
		File targetDir = new File("/tmp/applicationtest/");
		targetDir.mkdirs();
		app.matchAndExtract(model1, model2, targetDir);
	}
}
