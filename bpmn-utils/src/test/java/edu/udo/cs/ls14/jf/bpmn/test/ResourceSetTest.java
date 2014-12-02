package edu.udo.cs.ls14.jf.bpmn.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.registry.Registries;

public class ResourceSetTest {

	private Bpmn2ResourceSet resSet;

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testResourceSet() throws Exception {
		resSet = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/utils/test").getPath());

		String filename1 = "PM1-mit-Fragment1.bpmn";
		Definitions def1 = resSet.loadDefinitions(filename1);

		String filename2 = "PM2-mit-Fragment1.bpmn";
		Definitions def2 = resSet.loadDefinitions(filename2);

		System.out.println(resSet.toContentString());

		assertEquals(2, resSet.getResources().size());
		assertEquals(def1, resSet.getResource(filename1).getContents().get(0)
				.eContents().get(0));
		assertEquals(def2, resSet.getResource(filename2).getContents().get(0)
				.eContents().get(0));

		assertNull(resSet.getResource("not-existent.bpmn"));
	}
}
