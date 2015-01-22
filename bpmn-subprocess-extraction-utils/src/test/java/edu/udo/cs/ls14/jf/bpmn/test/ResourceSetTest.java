package edu.udo.cs.ls14.jf.bpmn.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.registry.Registries;

public class ResourceSetTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testResourceSet() throws Exception {
		Bpmn2ResourceSet resSet = Bpmn2ResourceSet.getInstance();
		int resourcesBefore = resSet.getResources().size();

		String pathname = "/bpmn/parallelGateway/";
		String filename1 = "parallelism1.bpmn";
		Definitions def1 = resSet.loadDefinitions(getClass().getResource(
				pathname + filename1).getPath());

		String filename2 = "parallelism2.bpmn";
		Definitions def2 = resSet.loadDefinitions(getClass().getResource(
				pathname + filename2).getPath());

		System.out.println(resSet.toContentString());

		assertEquals(resourcesBefore + 2, resSet.getResources()
				.size());
		assertEquals(
				def1,
				resSet.getResource(
						getClass().getResource(pathname + filename1).getPath())
						.getContents().get(0).eContents().get(0));
		assertEquals(
				def2,
				resSet.getResource(
						getClass().getResource(pathname + filename2).getPath())
						.getContents().get(0).eContents().get(0));

		assertNull(resSet.getResource("not-existent.bpmn"));
	}
}
