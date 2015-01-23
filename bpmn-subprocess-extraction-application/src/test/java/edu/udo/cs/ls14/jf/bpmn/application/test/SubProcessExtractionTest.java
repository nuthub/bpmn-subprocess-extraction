package edu.udo.cs.ls14.jf.bpmn.application.test;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.application.SubProcessExtraction;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessTransformationUtil;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.registry.Registries;

public class SubProcessExtractionTest {

	private SubProcessExtraction app;

	private static final String TARGETDIR = "/tmp/";

	@Before
	public void setUp() {
		Registries.registerAll();
		app = new SubProcessExtraction();
		app.init();
	}

	@Test
	public void runPM1PM2() throws Exception {
		String resname1 = "parallelism1.bpmn";
		String resname2 = "parallelism2.bpmn";
		runTest("/bpmn/parallelGateway/", resname1, resname2);
	}

	private void runTest(String path, String resourceName1, String resourceName2)
			throws Exception {
		Definitions defs1 = EcoreUtil
				.copy(Bpmn2ResourceSet.getInstance().loadDefinitions(
						getClass().getResource(path).getPath() + resourceName1));
		Definitions defs2 = EcoreUtil
				.copy(Bpmn2ResourceSet.getInstance().loadDefinitions(
						getClass().getResource(path).getPath() + resourceName2));
		long start = System.currentTimeMillis();
		ProcessTransformation extraction = app
				.runSubProcessExtraction(defs1, defs2);
		long end = System.currentTimeMillis();
		// Write out
		// for (Entry<String, Definitions> result : extraction.getResults()
		// .entrySet()) {
		// System.out.println(result.getKey() + " => "
		// + result.getValue().getId());
		// }

		// System.out.println("Extraction: "
		// + ProcessExtractionUtil.toXml(extraction));
		// ProcessExtractionUtil.writeToFile("/tmp/results/" + key
		// + "/extraction.xml", extraction);
		ProcessTransformationUtil.writeResults(TARGETDIR + "/" + path, extraction);
		System.out.println("Took " + (end - start)
				+ "ms for the extraction process");

	}

}
