package edu.udo.cs.ls14.jf.bpmn.ws.adapter.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.registry.Registries;
import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlProcessMatchingAdapter;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

public class XmlProcessMatchingTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testPM1() throws Exception {
		ProcessMatching matching = BpmnMatchingFactory.eINSTANCE
				.createProcessMatching();
		XmlProcessMatchingAdapter adapter = new XmlProcessMatchingAdapter();
		assertNotNull(adapter.unmarshal(adapter.marshal(matching)));

	}
}
