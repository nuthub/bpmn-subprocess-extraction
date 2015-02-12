package edu.udo.cs.ls14.jf.bpmn.app.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.app.ISubProcessExtractionDebug;
import edu.udo.cs.ls14.jf.bpmn.app.ISubProcessExtractionProfiling;
import edu.udo.cs.ls14.jf.bpmn.app.SubProcessExtractionJava;
import edu.udo.cs.ls14.jf.bpmn.util.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

public class SubProcessExtractionJavaTest {

	private static final String TARGETDIR = System
			.getProperty("java.io.tmpdir") + "/test-app/";

	@Before
	public void setUp() {
		Registries.registerAll();
		Bpmn2ResourceSet.getInstance().clear();

	}

	@Test
	public void testDebugParallelism() throws Exception {
		ISubProcessExtractionDebug app = new SubProcessExtractionJava();
		String resname1 = getClass().getResource(
				"/bpmn/parallelGateway/parallelism1.bpmn").getFile();
		String resname2 = getClass().getResource(
				"/bpmn/parallelGateway/parallelism2.bpmn").getFile();
		ProcessTransformation transformation = app.runDebug(resname1, resname2,
				TARGETDIR);
		assertEquals(3, transformation.getResults().size());
	}

	@Test
	public void testProfilingParallelism() throws Exception {
		ISubProcessExtractionProfiling app = new SubProcessExtractionJava();
		String resname1 = getClass().getResource(
				"/bpmn/parallelGateway/parallelism1.bpmn").getFile();
		Definitions defs1 = Bpmn2ResourceSet.getInstance().loadDefinitions(resname1);
		String resname2 = getClass().getResource(
				"/bpmn/parallelGateway/parallelism2.bpmn").getFile();
		Definitions defs2 = Bpmn2ResourceSet.getInstance().loadDefinitions(resname2);
		ProcessTransformation transformation = app.runAndProfile(defs1, defs2);
		assertEquals(3, transformation.getResults().size());
	}
}
