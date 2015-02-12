package edu.udo.cs.ls14.jf.bpmn.app.test.profiling;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.app.ISubProcessExtractionProfiling;
import edu.udo.cs.ls14.jf.bpmn.app.SubProcessExtractionJava;
import edu.udo.cs.ls14.jf.bpmn.util.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

/**
 * Results aren't written to disc.
 * 
 * @author flake
 *
 */
public class SubProcessExtractionJavaProfilingTest {

	private ISubProcessExtractionProfiling app = null;

	@Before
	public void setUp() {
		Registries.registerAll();
		app = new SubProcessExtractionJava();
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
	public void testParallelism() throws Exception {
		String resname1 = "parallelism1.bpmn";
		String resname2 = "parallelism2.bpmn";
		runTest("/bpmn/parallelGateway/", resname1, resname2);
		// TODO: Assertions
	}

	@Test
	public void testShortLoop() throws Exception {
		String resname1 = "shortloop.bpmn";
		String resname2 = "parallel.bpmn";
		runTest("/bpmn/shortLoop/", resname1, resname2);
		// TODO: Assertions
	}

	private ProcessTransformation runTest(String path, String resName1,
			String resName2) throws Exception {
		// Load models
		Definitions defs1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(path).getPath() + resName1);
		Definitions defs2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(path).getPath() + resName2);

		// run process
		// first run
		app.runAndProfile(defs1, defs2);
		// second run
		app.runAndProfile(defs1, defs2);
		// third run
		long start = System.currentTimeMillis();
		ProcessTransformation transformation = app.runAndProfile(defs1, defs2);
		long end = System.currentTimeMillis();

		// write results
		System.out.println("Done running with " + resName1  + " and " + resName2);
		System.out.println(transformation.getResults().size() + " results.");
		System.out.println("Took " + (end - start) + "ms for complete process");
		return transformation;
	}
}
