package edu.udo.cs.ls14.jf.bpmn.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.EObject;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.registry.Registries;

public class EObjectXmlConverterTest {

	@Before
	public void setUp() {
		Registries.registerBpmn();
		Registries.registerBpmnAnalysis();
	}

	@Test
	public void testConditionSequence() throws Exception {
		String filename = "/edu/udo/cs/ls14/jf/bpmn/utils/test/conditionSequence.bpmn";
		runTest(filename);
	}

	@Test
	public void testPM1() throws Exception {
		String filename = "/edu/udo/cs/ls14/jf/bpmn/utils/test/PM1-mit-Fragment1.bpmn";
		runTest(filename);
	}

	@Test
	public void testProcessAnalysis() throws Exception {
		ProcessAnalysis analysis = BpmnAnalysisFactory.eINSTANCE
				.createProcessAnalysis();
		String xml = EObjectXmlConverter.eObject2Xml("bpmnanalysis", analysis);
		ProcessAnalysis analysis2 = (ProcessAnalysis) EObjectXmlConverter
				.xml2EObject("bpmnanalysis", xml);
		assertNotNull(analysis2);
	}

	private void runTest(String fileName) throws Exception {
		String xml1 = readFile(fileName);
		// convert XML to EObject
		EObject eObj1 = EObjectXmlConverter.xml2EObject("bpmn", xml1);
		System.out.println(EObjectXmlConverter.xml2EObject("bpmn", xml1));

		// convert EObject to XML
		String xml2 = EObjectXmlConverter.eObject2Xml("bpmn", eObj1);

		// convert XML to EObject
		EObject eObj2 = EObjectXmlConverter.xml2EObject("bpmn", xml2);

		assertEquals(((Definitions) eObj1).getId(),
				((Definitions) eObj2).getId());

	}

	private String readFile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(getClass().getResource(
				path).getPath())), "UTF-8");
	}

}
