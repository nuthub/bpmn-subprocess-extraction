package edu.udo.cs.ls14.jf.bpmn.ws.adapter.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlProcessTransformationAdapter;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationFactory;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

public class XmlProcessTransformationAdapterTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testPM1() throws Exception {
		ProcessTransformation transformation = BpmnTransformationFactory.eINSTANCE
				.createProcessTransformation();
		XmlProcessTransformationAdapter adapter = new XmlProcessTransformationAdapter();
		assertNotNull(adapter.unmarshal(adapter.marshal(transformation)));

	}
}
