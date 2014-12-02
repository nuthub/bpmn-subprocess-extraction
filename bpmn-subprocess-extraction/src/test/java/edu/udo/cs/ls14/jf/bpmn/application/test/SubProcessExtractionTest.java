package edu.udo.cs.ls14.jf.bpmn.application.test;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.application.SubProcessExtraction;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessExtractionUtil;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;
import edu.udo.cs.ls14.jf.registry.Registries;

public class SubProcessExtractionTest {

	private SubProcessExtraction app;

	private static final String TARGETDIR = "/tmp/results/";

	@Before
	public void setUp() {
		Registries.registerAll();
		app = new SubProcessExtraction();
		app.init();
	}

	@Test
	public void runPM1PM2() throws Exception {
		String resname1 = "PM1-mit-Fragment1.bpmn";
		String resname2 = "PM2-mit-Fragment1.bpmn";
		runTest(TARGETDIR + "PM1PM2", "/edu/udo/cs/ls14/jf/bpmn/test/",
				resname1, resname2);
	}

	private void runTest(String targetDir, String path, String resourceName1,
			String resourceName2) throws Exception {
		Bpmn2ResourceSet resSet = new Bpmn2ResourceSet(getClass().getResource(
				path).getPath());
		Definitions defs1 = EcoreUtil.copy(resSet
				.loadDefinitions(resourceName1));
		Definitions defs2 = EcoreUtil.copy(resSet
				.loadDefinitions(resourceName2));
		long start = System.currentTimeMillis();
		ProcessExtraction extraction = app
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
		ProcessExtractionUtil.writeResults(targetDir + "/", extraction);
		System.out.println("Took " + (end - start)
				+ "ms for the extraction process");

	}

}
