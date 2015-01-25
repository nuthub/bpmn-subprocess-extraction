package edu.udo.cs.ls14.jf.bpmn.application.test;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.registry.Registries;
@Ignore

public class ResourceCopyTest {

	@Before
	public void setUp() {
		Registries.registerBpmn();
	}

	@Test
	public void testResSetCopy() throws Exception {
		String basename = "parallelism1";
		Definitions definitions = Bpmn2ResourceSet.getInstance()
				.loadDefinitions(
						getClass().getResource(
								"/bpmn/parallelGateway/" + basename + ".bpmn")
								.getPath());
		System.out.println(definitions);
		Resource resOut = Bpmn2ResourceSet.getInstance().createResource(
				"/tmp/bpmn/parallelGateway/copied-" + basename + ".bpmn",
				definitions);
		resOut.save(null);

	}
}
