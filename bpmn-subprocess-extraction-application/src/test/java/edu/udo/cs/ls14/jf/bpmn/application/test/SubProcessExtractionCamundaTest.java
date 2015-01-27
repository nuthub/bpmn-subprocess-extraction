package edu.udo.cs.ls14.jf.bpmn.application.test;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.app.SubProcessExtractionCamunda;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessTransformationUtil;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.registry.Registries;

public class SubProcessExtractionCamundaTest {
	protected static final String TARGETDIR = "/tmp/";

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void parallelism2() throws Exception {
		String resname1 = "parallelism1.bpmn";
		String resname2 = "parallelism2.bpmn";
		runTest("/bpmn/parallelGateway2/", resname1, resname2);
	}

	protected void runTest(String path, String resourceName1,
			String resourceName2) throws Exception {
		SubProcessExtractionCamunda app = new SubProcessExtractionCamunda();
		app.init();
		Definitions defs1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(path).getPath() + resourceName1);
		Definitions defs2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(path).getPath() + resourceName2);
		long start = System.currentTimeMillis();
		ProcessTransformation extraction = app.run(defs1, defs2);
		long end = System.currentTimeMillis();

		ProcessTransformationUtil.writeResults(TARGETDIR + "/" + path,
				extraction);
		System.out.println("Took " + (end - start)
				+ "ms for the extraction process");

	}
}
