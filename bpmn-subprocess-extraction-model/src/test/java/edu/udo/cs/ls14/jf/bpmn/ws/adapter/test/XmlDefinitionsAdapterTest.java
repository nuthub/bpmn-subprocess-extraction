package edu.udo.cs.ls14.jf.bpmn.ws.adapter.test;

import static org.junit.Assert.assertNotNull;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlDefinitionsAdapter;

public class XmlDefinitionsAdapterTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testPM1() throws Exception {
		Definitions definitions = Bpmn2Factory.eINSTANCE.createDefinitions();
		XmlDefinitionsAdapter adapter = new XmlDefinitionsAdapter();
		assertNotNull(adapter.unmarshal(adapter.marshal(definitions)));

	}
}
