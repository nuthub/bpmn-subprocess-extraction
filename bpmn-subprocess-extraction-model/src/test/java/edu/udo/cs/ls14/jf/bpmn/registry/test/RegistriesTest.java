package edu.udo.cs.ls14.jf.bpmn.registry.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.registry.Registries;

public class RegistriesTest {

	@Test
	public void testRegistry() throws Exception {
		Registries.registerAll();
		assertNotNull(Registries.getResourceFactory("bpmn"));
		assertNotNull(new Registries());
	}
	
	@Test(expected=Exception.class)
	public void testNonExistentFactory() throws Exception {
		Registries.getResourceFactory("notexistent");
	}

}
