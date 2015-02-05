package edu.udo.cs.ls14.jf.bpmn.application.test;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.app.SubProcessExtraction;
import edu.udo.cs.ls14.jf.bpmn.app.SubProcessExtractionCamunda;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessTransformationUtil;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.registry.Registries;

public class SubProcessExtractionCamundaTest {

	private static final String TARGETDIR = "/tmp/";
	private SubProcessExtraction app = null;

	@Before
	public void setUp() {
		Registries.registerAll();
		app = new SubProcessExtractionCamunda();
		app.init();
	}

	@Test
	public void testComplete() throws Exception {
		String resname1 = "complete1.bpmn";
		String resname2 = "complete2.bpmn";
		runTest("/bpmn/complete/", resname1, resname2);
		// TODO: Assertions
	}

	@Test
	public void testCompleteLabelled() throws Exception {
		String resname1 = "complete1labelled.bpmn";
		String resname2 = "complete2labelled.bpmn";
		runTest("/bpmn/completeLabelled/", resname1, resname2);
		// TODO: Assertions
	}

	@Test
	public void testEvaluation() throws Exception {
		String resname1 = "evaluation1.bpmn";
		String resname2 = "evaluation2.bpmn";
		runTest("/bpmn/evaluation/", resname1, resname2);
		// TODO: Assertions
	}

	@Test
	public void testConditionalFlow() throws Exception {
		String resname1 = "conditionSequence1.bpmn";
		String resname2 = "conditionSequence2.bpmn";
		runTest("/bpmn/conditionalFlow/", resname1, resname2);
		// TODO: Assertions
	}

	@Test
	public void testEventBasedGateway() throws Exception {
		String resname1 = "event-based-gateway-exclusive.bpmn";
		String resname2 = "event-based-gateway-parallel.bpmn";
		runTest("/bpmn/eventBasedGateway/", resname1, resname2);
		// TODO: Assertions
	}

	@Test
	public void testLoops() throws Exception {
		String resname1 = "looping-events-example.bpmn";
		String resname2 = "looping-xor.bpmn";
		runTest("/bpmn/loops/", resname1, resname2);
		// TODO: Assertions
	}

	@Test
	public void testSequences() throws Exception {
		String resname1 = "sequence1.bpmn";
		String resname2 = "sequence2.bpmn";
		runTest("/bpmn/sequences/", resname1, resname2);
		// TODO: Assertions
	}

	@Test
	public void testShortLoop() throws Exception {
		String resname1 = "shortloop.bpmn";
		String resname2 = "parallel.bpmn";
		runTest("/bpmn/shortLoop/", resname1, resname2);
		// TODO: Assertions
	}

	@Test
	public void testParallelism() throws Exception {
		String resname1 = "parallelism1.bpmn";
		String resname2 = "parallelism2.bpmn";
		runTest("/bpmn/parallelGateway/", resname1, resname2);
		// TODO: Assertions
	}

	private ProcessTransformation runTest(String path, String resName1,
			String resName2) throws Exception {
		// Load models
		Definitions defs1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(path).getPath() + resName1);
		Definitions defs2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(path).getPath() + resName2);

		// Run process
		long start = System.currentTimeMillis();
		ProcessTransformation transformation = app.run(defs1, defs2);
		long end = System.currentTimeMillis();

		// Write results
		ProcessTransformationUtil.writeResults(TARGETDIR + "/" + path,
				transformation);
		System.out.println("Took " + (end - start) + "ms for complete process");
		return transformation;
	}
}
