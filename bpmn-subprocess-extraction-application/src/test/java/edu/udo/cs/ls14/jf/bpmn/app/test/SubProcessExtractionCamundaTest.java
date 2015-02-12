package edu.udo.cs.ls14.jf.bpmn.app.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.app.ISubProcessExtraction;
import edu.udo.cs.ls14.jf.bpmn.app.ISubProcessExtractionProfiling;
import edu.udo.cs.ls14.jf.bpmn.app.SubProcessExtractionCamunda;
import edu.udo.cs.ls14.jf.bpmn.util.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

public class SubProcessExtractionCamundaTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testAppParallelism() throws Exception {
		ISubProcessExtraction app = new SubProcessExtractionCamunda();
		Bpmn2ResourceSet.getInstance().clear();
		Definitions defs1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(
						"/bpmn/parallelGateway/parallelism1.bpmn").getFile());
		Definitions defs2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(
						"/bpmn/parallelGateway/parallelism2.bpmn").getFile());
		// Run process
		ProcessTransformation transformation = app.run(defs1, defs2);
		assertEquals(3, transformation.getResults().size());
	}

	@Test
	public void testProfilingParallelism() throws Exception {
		// Load Models
		Bpmn2ResourceSet.getInstance().clear();
		Definitions defs1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(
						"/bpmn/parallelGateway/parallelism1.bpmn").getFile());
		Definitions defs2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(
						"/bpmn/parallelGateway/parallelism2.bpmn").getFile());
		ISubProcessExtractionProfiling app = new SubProcessExtractionCamunda();
		ProcessTransformation transformation = app.runAndProfile(defs1, defs2);
		assertEquals(3, transformation.getResults().size());

	}
}
