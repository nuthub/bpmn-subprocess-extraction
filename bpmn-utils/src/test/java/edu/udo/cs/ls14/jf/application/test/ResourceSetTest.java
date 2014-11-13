package edu.udo.cs.ls14.jf.application.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;

public class ResourceSetTest {

	private Bpmn2ResourceSet resSet;

	@Test
	public void testResourceSet() throws IOException {
		resSet = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/utils/test").getPath());

		String filename1 = "PM1-mit-Fragment1.bpmn";
		Resource resource1 = resSet.loadResource(filename1);

		String filename2 = "PM2-mit-Fragment1.bpmn";
		Resource resource2 = resSet.loadResource(filename2);

		System.out.println(resSet.toContentString());

		assertEquals(2, resSet.getResources().size());
		assertEquals(resource1, resSet.getResource(filename1));
		assertEquals(resource2, resSet.getResource(filename2));

		assertNull(resSet.getResource("not-existent.bpmn"));
	}
}
