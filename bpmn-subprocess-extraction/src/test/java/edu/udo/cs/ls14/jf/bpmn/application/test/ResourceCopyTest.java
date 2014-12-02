package edu.udo.cs.ls14.jf.bpmn.application.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;

public class ResourceCopyTest {

	@Test
	public void testResSetCopy() throws FileNotFoundException, IOException {
		Bpmn2ResourceSet resSetIn = new Bpmn2ResourceSet(getClass()
				.getResource("/bpmn/").getPath());
		String basename1 = "PM1-mit-Fragment1.bpmn";
		// Resource resource = resSet.loadResource(basename1);
		// System.out.println(resource);
		Definitions definitions = resSetIn.loadDefinitions(basename1);
		System.out.println(definitions);
		Bpmn2ResourceSet resSetOut = new Bpmn2ResourceSet("/tmp/resSet");
		Resource resOut = resSetOut.createResource("resOut.bpmn", definitions);
		resOut.save(null);

	}

}
