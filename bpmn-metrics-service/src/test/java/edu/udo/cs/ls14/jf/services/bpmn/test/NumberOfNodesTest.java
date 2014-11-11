package edu.udo.cs.ls14.jf.services.bpmn.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.BpmnXmlConverter;
import edu.udo.cs.ls14.jf.services.bpmn.NumberOfNodes;

public class NumberOfNodesTest {

	private NumberOfNodes service;

	@Before
	public void setUp() {
		service = new NumberOfNodes();
	}

	@Test
	public void testPM1() throws Exception {
		String definitionsXml = getDefinitionsXml("PM1-mit-Fragment1");
		assertEquals(5, service.getNumOfActivities(definitionsXml));
		assertEquals(7, service.getNumOfActivitiesAndEvents(definitionsXml));
		assertEquals(2, service.getNumOfGateways(definitionsXml));

	}

	@Test
	public void testConditionSequence() throws Exception {
		String definitionsXml = getDefinitionsXml("conditionSequence");
		System.out.println(definitionsXml);
	}

	private String getDefinitionsXml(String basename) throws Exception {
		Resource resource = new Bpmn2ResourceSet(
				"src/test/resources/edu/udo/cs/ls14/jf/services/bpmn/test")
				.loadResource(basename + ".bpmn");
		Definitions definitions = (Definitions) resource.getContents().get(0);
		return BpmnXmlConverter.bpmn2Xml(definitions, Definitions.class);
	}

}
