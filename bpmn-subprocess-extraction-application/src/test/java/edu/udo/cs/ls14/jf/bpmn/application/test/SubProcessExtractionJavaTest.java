package edu.udo.cs.ls14.jf.bpmn.application.test;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.app.SubProcessExtraction;
import edu.udo.cs.ls14.jf.bpmn.app.SubProcessExtractionJava;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessTransformationUtil;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.registry.Registries;

public class SubProcessExtractionJavaTest {

	protected static final String TARGETDIR = "/tmp/";

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testParallelism() throws Exception {
		String resname1 = "parallelism1.bpmn";
		String resname2 = "parallelism2.bpmn";
		runTest("/bpmn/parallelGateway/", resname1, resname2);
		// TODO: Assertions
	}

	@Test
	public void testCompleteLabelled() throws Exception {
		String resname1 = "complete1labelled.bpmn";
		String resname2 = "complete2labelled.bpmn";
		runTest("/bpmn/completeLabelled/", resname1, resname2);
		// TODO: Assertions
	}

	private ProcessTransformation runTest(String path, String resourceName1,
			String resourceName2) throws Exception {
		// Load models
		Definitions defs1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(path).getPath() + resourceName1);
		Definitions defs2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(path).getPath() + resourceName2);

		// Create application
		SubProcessExtraction app = new SubProcessExtractionJava();
		app.init();

		// run process
		long start = System.currentTimeMillis();
		ProcessTransformation transformation = app.run(defs1, defs2);
		long end = System.currentTimeMillis();

		// write results
		ProcessTransformationUtil.writeResults(TARGETDIR + "/" + path,
				transformation);
		System.out.println("Took " + (end - start) + "ms for complete process");
		return transformation;
	}
}
