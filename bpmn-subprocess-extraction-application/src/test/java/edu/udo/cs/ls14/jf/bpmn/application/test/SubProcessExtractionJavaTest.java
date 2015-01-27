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
	private Bpmn2ResourceSet resSet;

	@Before
	public void setUp() {
		Registries.registerAll();
		resSet = Bpmn2ResourceSet.getInstance();

	}

	protected SubProcessExtraction app;

	@Test
	public void runParallelism() throws Exception {
		String resname1 = "parallelism1.bpmn";
		String resname2 = "parallelism2.bpmn";
		runTest("/bpmn/parallelGateway/", resname1, resname2);
	}

	@Test
	public void runParallelism2() throws Exception {
		String resname1 = "parallelism1.bpmn";
		String resname2 = "parallelism2.bpmn";
		runTest("/bpmn/parallelGateway2/", resname1, resname2);
	}

	@Test
	public void runCompleteLabelled() throws Exception {
		String resname1 = "complete1labelled.bpmn";
		String resname2 = "complete2labelled.bpmn";
		runTest("/bpmn/completeLabelled/", resname1, resname2);
	}

	protected void runTest(String path, String resName1, String resName2)
			throws Exception {
		Definitions defs1 = resSet.loadDefinitions(getClass().getResource(path)
				.getPath() + resName1);
		Definitions defs2 = resSet.loadDefinitions(getClass().getResource(path)
				.getPath() + resName2);

		SubProcessExtractionJava app = new SubProcessExtractionJava();
		app.init();

		long start = System.currentTimeMillis();
		ProcessTransformation extraction = app.run(defs1, defs2);
		long end = System.currentTimeMillis();

		ProcessTransformationUtil.writeResults(TARGETDIR + "/" + path,
				extraction);
		System.out.println("Took " + (end - start)
				+ "ms for the extraction process");

	}
}
