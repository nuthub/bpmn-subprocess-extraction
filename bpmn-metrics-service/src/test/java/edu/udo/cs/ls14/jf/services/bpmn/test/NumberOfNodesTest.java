package edu.udo.cs.ls14.jf.services.bpmn.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.services.bpmn.NumberOfNodes;

public class NumberOfNodesTest {

	private NumberOfNodes service;

	@Before
	public void setUp() {
		service = new NumberOfNodes();
	}

	@Test
	public void testNumberOfActivities() throws Exception {
		String definitionsXml = getDefinitionsXml("PM1-mit-Fragment1.bpmn");
		assertEquals(5, service.getNumOfActivities(definitionsXml));
		assertEquals(7, service.getNumOfActivitiesAndEvents(definitionsXml));
		assertEquals(2, service.getNumOfGateways(definitionsXml));
	}

	private String getDefinitionsXml(String basename) throws Exception {
		Resource resource = ProcessLoader.getBpmnResource(getClass()
				.getResource(basename));
		Definitions definitions = ProcessLoader
				.getDefinitionsFromResource(resource);
		return EObjectXmlConverter.eObject2Xml(definitions);
	}
	
	
}
